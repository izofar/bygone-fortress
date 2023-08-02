package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneFortressMod;
import com.izofar.bygonefortress.world.processors.DataBlockProcessor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public abstract class ModProcessors {

    public static final StructureProcessorType<DataBlockProcessor> DATA_BLOCK_PROCESSOR = () -> DataBlockProcessor.CODEC;

    public static void registerProcessors(){
        Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(BygoneFortressMod.MODID, "data_block_processor"), DATA_BLOCK_PROCESSOR);
    }
}
