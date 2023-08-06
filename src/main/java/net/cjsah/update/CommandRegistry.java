package net.cjsah.update;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.cjsah.update.UpdateSuppression.suppressionBlocks;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("update").then(literal("suppress").then(argument("pos", BlockPosArgumentType.blockPos()).executes(context -> {
            World world = context.getSource().getWorld();
            BlockPos pos = BlockPosArgumentType.getBlockPos(context, "pos");
            suppressionBlocks.add(new SuppressionData(world, pos));
            return Command.SINGLE_SUCCESS;
        }))).then(literal("release").then(argument("pos", BlockPosArgumentType.blockPos()).executes(context -> {
            World world = context.getSource().getWorld();
            BlockPos pos = BlockPosArgumentType.getBlockPos(context, "pos");
            suppressionBlocks.removeIf(it -> it.equals(world, pos));
            return Command.SINGLE_SUCCESS;
        }))).then(literal("clear").executes(context -> {
            suppressionBlocks.clear();
            return Command.SINGLE_SUCCESS;
        })).then(literal("list").executes(context -> {
            ServerCommandSource source = context.getSource();
            if (suppressionBlocks.isEmpty()) {
                source.sendFeedback(() -> Text.of("No blocks are suppressed"), false);
                return Command.SINGLE_SUCCESS;
            }
            source.sendFeedback(() -> Text.of("Blocks:"), false);
            suppressionBlocks.stream().map(SuppressionData::toGameString).forEach(it ->
                    source.sendFeedback(() -> Text.of(it), false));
            return Command.SINGLE_SUCCESS;
        })));

        dispatcher.register(literal("block").then(argument("pos", BlockPosArgumentType.blockPos()).executes(context -> {
            ServerCommandSource source = context.getSource();
            BlockState blockState = source.getWorld().getBlockState(BlockPosArgumentType.getBlockPos(context, "pos"));
            System.out.println(blockState);

            return Command.SINGLE_SUCCESS;
        })));
    }
}
