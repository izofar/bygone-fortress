package com.izofar.bygonefortress.mixin;

import com.izofar.bygonefortress.util.ModLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.BasaltColumnFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(BasaltColumnFeature.class)
public class NoBasaltColumnsInStructuresMixin {

    @Inject(
            method = "canPlaceAt(Lnet/minecraft/world/IWorld;ILnet/minecraft/util/math/BlockPos$Mutable;)Z",
            at = @At(value = "HEAD"),
            cancellable = true
		)
    private static void bygonefortress_noBasaltColumnsInStructures(IWorld world, int seaLevel, BlockPos.Mutable mutable, CallbackInfoReturnable<Boolean> cir) {
        SectionPos sectionPos = SectionPos.of(mutable);
        for (Supplier<Structure<?>> structure : ModLists.DELTALESS_STRUCTURES) {
            Optional<? extends StructureStart<?>> structureStart = ((ISeedReader)world).startsForFeature(sectionPos, structure.get()).findAny();
            if (structureStart.isPresent() && structureStart.get().getPieces().stream().anyMatch(box -> box.getBoundingBox().isInside(mutable)))
            {
                cir.setReturnValue(false);
                break;
            }
        }
    }
}
