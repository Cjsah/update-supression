package net.cjsah.update.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.cjsah.update.UpdateSuppression.suppressionBlocks;

@Mixin(LecternBlock.class)
public class LecternBlockMixin{
    @Inject(method = "onStateReplaced", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;", ordinal = 1))
    public void injectException(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) throws Exception {
        if (suppressionBlocks.contains(pos)) {
            suppressionBlocks.remove(pos);
            throw new Exception("test");
        }
    }
}
