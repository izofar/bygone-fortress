package com.izofar.bygonefortress.init;


import com.izofar.bygonefortress.BygoneNetherMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;

public abstract class ModConfiguredFeatures {

	public static final ConfiguredFeature<?, ?> CONFIGURED_MOB_FEATURE_WITHER_SKELETON = ModFeatures.MOB_FEATURE_WITHER_SKELETON.get().configured(IFeatureConfig.NONE);

	public static void registerConfiguredFeatures() {
		Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

		Registry.register(registry, new ResourceLocation(BygoneNetherMod.MODID, "mob_feature_wither_skeleton"), CONFIGURED_MOB_FEATURE_WITHER_SKELETON);

	}
}
