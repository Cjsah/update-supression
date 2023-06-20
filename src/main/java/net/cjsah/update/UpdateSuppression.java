package net.cjsah.update;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UpdateSuppression implements ModInitializer {
    public static final Set<BlockPos> suppressionBlocks = new HashSet<>();

    @Override
    public void onInitialize() {

    }
}
