package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneNetherMod;
import com.izofar.bygonefortress.util.ModLists;
import com.izofar.bygonefortress.world.feature.MobFeature;
import com.izofar.bygonefortress.world.feature.MobPassengerFeature;
import net.minecraft.entity.EntityType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class ModFeatures {

	public static final DeferredRegister<Feature<?>> MODDED_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BygoneNetherMod.MODID);
	public static final RegistryObject<Feature<NoFeatureConfig>> MOB_FEATURE_WITHER_SKELETON = MODDED_FEATURES.register("mob_feature_wither_skeleton", () -> new MobFeature<>(() -> EntityType.WITHER_SKELETON));

	public static void register(IEventBus eventBus) { MODDED_FEATURES.register(eventBus); }
}
