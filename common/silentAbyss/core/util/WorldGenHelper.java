package silentAbyss.core.util;

import net.minecraft.block.Block;

public class WorldGenHelper {

    public static final int[] groundBlocks = { Block.grass.blockID, Block.dirt.blockID, Block.stone.blockID, Block.sand.blockID,
            Block.sandStone.blockID, Block.hardenedClay.blockID };

    public static boolean isBlockGround(int id) {

        // Block b = Block.stone;
        // return !b.isBlockFoliage(world, x, y, z) && !b.isAirBlock(world, x,
        // y, z);
        for (int i = 0; i < groundBlocks.length; ++i) {
            if (id == groundBlocks[i]) { return true; }
        }
        return false;
    }
}
