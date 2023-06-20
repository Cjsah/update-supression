package net.cjsah.update.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.cjsah.update.CommandRegistry;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public abstract class CommandRegisterMixin {

    @Final
    @Shadow
    private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/WorldBorderCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    public void register(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess commandRegistryAccess, CallbackInfo ci) {
        CommandRegistry.register(dispatcher);
    }
}
