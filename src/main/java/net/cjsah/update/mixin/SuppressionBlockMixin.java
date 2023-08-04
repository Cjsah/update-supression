package net.cjsah.update.mixin;

import net.cjsah.update.UpdateSuppression;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class SuppressionBlockMixin {
    @Inject(method = "onStateReplaced", at = @At(value = "HEAD"))
    public void injectException(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) throws Exception {
        UpdateSuppression.suppression(world, pos);
    }
}
