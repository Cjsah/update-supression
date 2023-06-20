package net.cjsah.update;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.stream.Collectors;

import static net.cjsah.update.UpdateSuppression.suppressionBlocks;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("update").then(literal("suppression").then(argument("pos", BlockPosArgumentType.blockPos()).executes(context -> {
            suppressionBlocks.add(BlockPosArgumentType.getBlockPos(context, "pos"));
            return Command.SINGLE_SUCCESS;
        }))).then(literal("release").then(argument("pos", BlockPosArgumentType.blockPos()).executes(context -> {
            suppressionBlocks.add(BlockPosArgumentType.getBlockPos(context, "pos"));
            return Command.SINGLE_SUCCESS;
        }))).then(literal("list").executes(context -> {
            String feedback = suppressionBlocks.stream().map(pos -> String.format("[%s, %s, %s]", pos.getX(), pos.getY(), pos.getZ())).collect(Collectors.joining());
            context.getSource().sendFeedback(() -> Text.of(feedback), false);
            return Command.SINGLE_SUCCESS;
        })));
    }
}
