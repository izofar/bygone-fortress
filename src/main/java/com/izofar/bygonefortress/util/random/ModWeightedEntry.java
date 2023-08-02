package com.izofar.bygonefortress.util.random;

import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;

public class ModWeightedEntry<T> implements WeightedEntry{

	private final T data;
    private final Weight weight;
	
    public ModWeightedEntry(T data, int weight) {
        this.data = data;
        this.weight = Weight.of(weight);
     }

     public T getData() {
        return this.data;
     }

     @Override
     public Weight getWeight() {
        return this.weight;
     }
     
}
