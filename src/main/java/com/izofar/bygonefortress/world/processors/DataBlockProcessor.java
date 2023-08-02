package com.izofar.bygonefortress.world.processors;

import com.izofar.bygonefortress.init.ModProcessors;
import com.izofar.bygonefortress.util.ModStructureUtils;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Locale;

import static java.lang.Integer.parseInt;

public class DataBlockProcessor extends StructureProcessor {

    private enum DATA_PROCESSOR_MODE {
        PILLARS("-");

        private final String symbol;
        DATA_PROCESSOR_MODE(String symbol) {
            this.symbol = symbol;
        }
    }

    public static final Codec<DataBlockProcessor> CODEC = Codec.unit(DataBlockProcessor::new);
    private DataBlockProcessor() { }


    @Override
    public Template.BlockInfo processBlock(IWorldReader worldView, BlockPos pos, BlockPos blockPos, Template.BlockInfo structureBlockInfoLocal, Template.BlockInfo structureBlockInfoWorld, PlacementSettings structurePlacementData) {
        BlockState blockState = structureBlockInfoWorld.state;
        if (blockState.is(Blocks.STRUCTURE_BLOCK)) {
            String metadata = structureBlockInfoWorld.nbt.getString("metadata");
            BlockPos worldPos = structureBlockInfoWorld.pos;

            try {
                // Pillar mode activated
                if(metadata.contains(DATA_PROCESSOR_MODE.PILLARS.symbol)){
                    String[] splitString = metadata.split(DATA_PROCESSOR_MODE.PILLARS.symbol);

                    // Parses the data block's name field to get direction, blockstate, and depth
                    Direction direction = Direction.valueOf(splitString[0].toUpperCase(Locale.ROOT));
                    BlockStateParser blockArgumentParser = new BlockStateParser(new StringReader(splitString[1]), false);
                    blockArgumentParser.parse(true);
                    BlockState replacementState = blockArgumentParser.getState();
                    BlockState currentBlock = worldView.getBlockState(worldPos);
                    BlockPos.Mutable currentPos = new BlockPos.Mutable().set(worldPos);

                    int depth = 256;
                    if(splitString.length > 2){
                        String thirdArgument = splitString[2];
                        if(NumberUtils.isParsable(thirdArgument)){
                            depth = parseInt(thirdArgument) + 1;
                        }
                    }

                    int terrainY = Integer.MIN_VALUE;
                    if(direction == Direction.DOWN && depth == Integer.MAX_VALUE) {
                        terrainY = ModStructureUtils.getFirstLandYFromPos(worldView, worldPos);
                        if(terrainY <= 0) {
                            // Replaces the data block itself
                            return replacementState == null || replacementState.is(Blocks.STRUCTURE_VOID) ? null : new Template.BlockInfo(worldPos, replacementState, null);
                        }
                    }

                    // Creates the pillars in the world that replaces air and liquids
                    while(!currentBlock.canOcclude() &&
                            currentPos.getY() <= worldView.dimensionType().logicalHeight() &&
                            currentPos.getY() >= terrainY &&
                            currentPos.closerThan(worldPos, depth)
                    ){
                        Template.BlockInfo newPillarState1 = new Template.BlockInfo(structureBlockInfoLocal.pos, replacementState, null);
                        Template.BlockInfo newPillarState2 = new Template.BlockInfo(currentPos.immutable(), replacementState, null);

                        for(StructureProcessor processor : structurePlacementData.getProcessors()){
                            if(newPillarState2 == null){
                                break;
                            }
                            newPillarState2 = processor.processBlock(worldView, pos, blockPos, newPillarState1, newPillarState2, structurePlacementData);
                        }

                        if(newPillarState2 != null){
                            worldView.getChunk(currentPos).setBlockState(currentPos, newPillarState2.state, false);
                        }

                        currentPos.move(direction);
                        currentBlock = worldView.getBlockState(currentPos);
                    }

                    // Replaces the data block itself
                    return replacementState == null || replacementState.is(Blocks.STRUCTURE_VOID) ? null : new Template.BlockInfo(worldPos, replacementState, null);
                }
            }
            catch (CommandSyntaxException var11) {
                throw new RuntimeException(var11);
            }
        }
        return structureBlockInfoWorld;
    }


    @Override
    protected IStructureProcessorType<?> getType() {
        return ModProcessors.DATA_BLOCK_PROCESSOR;
    }
}
