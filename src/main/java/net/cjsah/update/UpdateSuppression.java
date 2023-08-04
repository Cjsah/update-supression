package net.cjsah.update;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UpdateSuppression {
    public static final Set<SuppressionBlock> suppressionBlocks = new HashSet<>();

    public void init() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, access, env) -> CommandRegistry.register(dispatcher)));
    }

    public static void suppression(World world, BlockPos pos) throws Exception {
        Optional<SuppressionBlock> optional = suppressionBlocks.stream().filter(it -> it.equals(world, pos)).findFirst();
        if (optional.isPresent()) {
            suppressionBlocks.remove(optional.get());
            throw new Exception("this block is suppressed");
        }
    }
}
