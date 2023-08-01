package com.izofar.bygonefortress.init;

import com.izofar.bygonefortress.BygoneFortressMod;
import com.izofar.bygonefortress.world.structure.NetherFortressStructure;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public abstract class ModStructures {

	public static final DeferredRegister<StructureType<?>> MODDED_STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, BygoneFortressMod.MODID);

	public static final RegistryObject<StructureType<NetherFortressStructure>> NETHER_FORTRESS = MODDED_STRUCTURES.register("fortress", () -> () -> NetherFortressStructure.CODEC);
	
	public static void register(IEventBus eventBus) { MODDED_STRUCTURES.register(eventBus); }
}
