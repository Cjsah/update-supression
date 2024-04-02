package net.cjsah.update.mixin;

import net.cjsah.update.SuppressionException;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = NetworkThreadUtils.class, remap = false)
public class SuppressPacketExceptionMixin {

    @Shadow
    @Final
    private static Logger LOGGER;
    @SuppressWarnings("rawtypes")
    @Inject(
            method = "method_11072",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"
            ),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private static void suppress(PacketListener packetListener, Packet packet, CallbackInfo ci, Exception ex) {
        if (ex instanceof SuppressionException e) {
            LOGGER.warn("Packet update suppression: {{}}", e.getDataMsg());
            ci.cancel();
        }
    }
}
