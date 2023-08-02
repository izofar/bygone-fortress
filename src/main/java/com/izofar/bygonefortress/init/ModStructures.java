package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneNetherMod;
import com.izofar.bygonefortress.world.structure.NetherFortressStructure;
import com.izofar.bygonefortress.util.ModStructureUtils;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class ModStructures {

	public static final DeferredRegister<Structure<?>> MODDED_STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BygoneNetherMod.MODID);

	public static final RegistryObject<Structure<NoFeatureConfig>> NETHER_FORTRESS = MODDED_STRUCTURES.register("fortress", NetherFortressStructure::new);

	public static void setupStructures() {
		ModStructureUtils.setupMapSpacingAndLand(NETHER_FORTRESS.get(), new StructureSeparationSettings(27, 4, 1206458988), false);
	}
	
	public static void register(IEventBus eventBus) { MODDED_STRUCTURES.register(eventBus); }
}
