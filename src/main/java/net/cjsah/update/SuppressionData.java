package net.cjsah.update;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SuppressionData {
    private final Identifier dimension;
    private final BlockPos pos;

    public SuppressionData(World world, BlockPos pos) {
        this.dimension = world.getDimensionKey().getValue();
        this.pos = pos;
    }

    public boolean equals(World world, BlockPos pos) {
        return world.getDimensionKey().getValue().equals(this.dimension) && this.pos.equals(pos);
    }

    @Override
    public String toString() {
        return "SuppressionBlock{" +
                "dimension=" + this.dimension +
                ", pos=" + this.pos +
                '}';
    }

    public String toGameString() {
        return this.dimension +
                " [" + this.pos.getX() +
                "," + this.pos.getY() +
                "," + this.pos.getZ() +
                "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuppressionData that)) return false;

        if (!dimension.equals(that.dimension)) return false;
        return pos.equals(that.pos);
    }

    @Override
    public int hashCode() {
        int result = dimension.hashCode();
        result = 31 * result + pos.hashCode();
        return result;
    }
}
