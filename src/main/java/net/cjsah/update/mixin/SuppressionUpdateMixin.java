package net.cjsah.update.mixin;

import net.cjsah.update.SuppressionData;
import net.cjsah.update.SuppressionException;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static net.cjsah.update.UpdateSuppression.suppressionBlocks;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class SuppressionUpdateMixin {
    @Inject(method = "neighborUpdate", at = @At(value = "HEAD"))
    public void injectException(World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify, CallbackInfo ci) throws Exception {
        Optional<SuppressionData> optional = suppressionBlocks.stream().filter(it -> it.equals(world, pos)).findFirst();
        if (optional.isPresent()) {
            SuppressionData data = optional.get();
            suppressionBlocks.remove(data);
            throw new SuppressionException(data);
        }
    }
}
