package net.cjsah.update;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.util.HashSet;
import java.util.Set;

public class UpdateSuppression {
    public static final Set<SuppressionData> suppressionBlocks = new HashSet<>();

    public void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, access, env) -> CommandRegistry.register(dispatcher)));
    }
}
