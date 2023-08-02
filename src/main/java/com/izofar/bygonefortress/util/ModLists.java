package com.izofar.bygonefortress.util;

import com.google.common.collect.ImmutableList;
import com.izofar.bygonefortress.init.ModStructures;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.List;
import java.util.function.Supplier;

public abstract class ModLists {

	public static final List<Supplier<Structure<?>>> DELTALESS_STRUCTURES = ImmutableList.of(ModStructures.NETHER_FORTRESS::get);

}
