package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneNetherMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public abstract class ModConfiguredStructures {

	public static final StructureFeature<?, ?> CONFIGURED_NETHER_FORTRESS = ModStructures.NETHER_FORTRESS.get().configured(IFeatureConfig.NONE);

	public static void registerConfiguredStructures() {
		Registry<StructureFeature<?, ?>> CONFIGURED_STRUCTURES = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
		Registry.register(CONFIGURED_STRUCTURES, new ResourceLocation(BygoneNetherMod.MODID, "configured_fortress"), CONFIGURED_NETHER_FORTRESS);
	}
}
