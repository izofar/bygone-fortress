package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneFortressMod;
import com.izofar.bygonefortress.world.structure.NetherFortressStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModStructures {

	public static final DeferredRegister<StructureFeature<?>> MODDED_STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BygoneFortressMod.MODID);

	public static final RegistryObject<StructureFeature<JigsawConfiguration>> NETHER_FORTRESS = MODDED_STRUCTURES.register("fortress", () -> new NetherFortressStructure(JigsawConfiguration.CODEC));

	public static void register(IEventBus eventBus) { MODDED_STRUCTURES.register(eventBus); }
}
