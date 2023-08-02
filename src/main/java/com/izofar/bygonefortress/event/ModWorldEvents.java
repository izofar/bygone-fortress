package com.izofar.bygonefortress.event;

import com.izofar.bygonefortress.init.ModConfiguredStructures;
import com.izofar.bygonefortress.init.ModStructures;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public abstract class ModWorldEvents {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void addFeaturesToBiomes(final BiomeLoadingEvent event){
		if(event.getCategory() == Biome.Category.NETHER){
			event.getGeneration().getStructures().add(() -> ModConfiguredStructures.CONFIGURED_NETHER_FORTRESS);
			event.getGeneration().getStructures().removeIf((supplier) -> supplier.get().feature == StructureFeatures.NETHER_BRIDGE.feature);
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void addDimensionSpacing(final WorldEvent.Load event) {
		if (event.getWorld() instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld) event.getWorld();
			ChunkGenerator chunkGenerator = serverWorld.getChunkSource().getGenerator();

			if (chunkGenerator instanceof FlatChunkGenerator && serverWorld.dimension().equals(World.OVERWORLD)) return;

			Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
			tempMap.putIfAbsent(ModStructures.NETHER_FORTRESS.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.NETHER_FORTRESS.get()));
			chunkGenerator.getSettings().structureConfig = tempMap;
		}
	}
}
