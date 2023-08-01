package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneFortressMod;
import com.izofar.bygonefortress.world.processors.DataBlockProcessor;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModProcessors {

    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(Registry.STRUCTURE_PROCESSOR_REGISTRY, BygoneFortressMod.MODID);

    public static final RegistryObject<StructureProcessorType<DataBlockProcessor>> DATA_BLOCK_PROCESSOR = STRUCTURE_PROCESSOR.register("data_block_processor", () -> () -> DataBlockProcessor.CODEC);

    public static void register(IEventBus eventBus){
        STRUCTURE_PROCESSOR.register(eventBus);
    }
}
