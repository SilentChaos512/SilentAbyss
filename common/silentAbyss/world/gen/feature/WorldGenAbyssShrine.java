package silentAbyss.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;
import silentAbyss.block.ModBlocks;
import silentAbyss.core.util.WorldGenHelper;
import silentAbyss.lib.Strings;

public class WorldGenAbyssShrine extends WorldGenerator {

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {

        while (world.isAirBlock(x, y, z)) {
            --y;
        }

        // Only spawn at/above sea level
        if (y < 64) { return true; }

        // Only spawn on ground blocks.
        if (!WorldGenHelper.isBlockGround(world.getBlockId(x, y, z))) { return true; }

        int i = 0, j = 0;

        // Check for a 5x5 foundation
        for (i = -2; i < 3; ++i) {
            for (j = -2; j < 3; ++j) {
                if (world.isAirBlock(x + i, y - 1, z + j)) { return true; }
            }
        }

        // Select either dark (4) or light (5) bricks.
        int m = random.nextInt(2) + 4;

        /*
         * 1
         */
        for (i = -3; i < 4; ++i) {
            for (j = -3; j < 4; ++j) {
                // Skip corners
                if (!((i == -3 || i == 3) && (j == -3 || j == 3))) {
                    setBrick(world, x + i, y, z + j, m);
                }
            }
        }
        // Gemstone bricks
        world.setBlock(x - 1, y, z - 2, ModBlocks.brick.blockID, 0, 2);
        world.setBlock(x + 0, y, z - 2, ModBlocks.brick.blockID, 0, 2);
        world.setBlock(x + 1, y, z - 2, ModBlocks.brick.blockID, 0, 2);
        world.setBlock(x + 2, y, z - 1, ModBlocks.brick.blockID, 1, 2);
        world.setBlock(x + 2, y, z + 0, ModBlocks.brick.blockID, 1, 2);
        world.setBlock(x + 2, y, z + 1, ModBlocks.brick.blockID, 1, 2);
        world.setBlock(x - 1, y, z + 2, ModBlocks.brick.blockID, 2, 2);
        world.setBlock(x + 0, y, z + 2, ModBlocks.brick.blockID, 2, 2);
        world.setBlock(x + 1, y, z + 2, ModBlocks.brick.blockID, 2, 2);
        world.setBlock(x - 2, y, z - 1, ModBlocks.brick.blockID, 3, 2);
        world.setBlock(x - 2, y, z + 0, ModBlocks.brick.blockID, 3, 2);
        world.setBlock(x - 2, y, z + 1, ModBlocks.brick.blockID, 3, 2);
        // Gemstone block in center
        world.setBlock(x, y, z, ModBlocks.gem.blockID, m, 2);

        /*
         * 2
         */
        ++y;
        setBrick(world, x - 2, y, z - 2, m);
        setBrick(world, x - 2, y, z + 2, m);
        setBrick(world, x + 2, y, z - 2, m);
        setBrick(world, x + 2, y, z + 2, m);
        world.setBlock(x, y, z, Block.chest.blockID);
        // Get loot for chest
        TileEntityChest tile = (TileEntityChest) world.getBlockTileEntity(x, y, z);
        if (tile != null) {
            // LogHelper.debug("lol");
            ChestGenHooks info = ChestGenHooks.getInfo(Strings.SHRINE_CHEST);
            info.setMin(8);
            info.setMax(12);
            WeightedRandomChestContent.generateChestContents(random, info.getItems(random), tile, info.getCount(random));
        }

        /*
         * 3
         */
        ++y;
        setBrick(world, x - 2, y, z - 2, m);
        setBrick(world, x - 2, y, z + 2, m);
        setBrick(world, x + 2, y, z - 2, m);
        setBrick(world, x + 2, y, z + 2, m);

        /*
         * 4
         */
        ++y;
        setBrick(world, x - 2, y, z - 2, m);
        setBrick(world, x - 2, y, z - 1, m);
        setBrick(world, x - 1, y, z - 2, m);
        setBrick(world, x - 2, y, z + 2, m);
        setBrick(world, x - 2, y, z + 1, m);
        setBrick(world, x - 1, y, z + 2, m);
        setBrick(world, x + 2, y, z - 2, m);
        setBrick(world, x + 2, y, z - 1, m);
        setBrick(world, x + 1, y, z - 2, m);
        setBrick(world, x + 2, y, z + 2, m);
        setBrick(world, x + 2, y, z + 1, m);
        setBrick(world, x + 1, y, z + 2, m);

        /*
         * 5
         */
        ++y;
        for (i = -3; i < 4; ++i) {
            for (j = -3; j < 4; ++j) {
                // Skip corners and center
                if (!((i == -3 || i == 3) && (j == -3 || j == 3)) && !(i == 0 && j == 0)) {
                    setBrick(world, x + i, y, z + j, m);
                }
            }
        }

        /*
         * 6 - diamond shape with glowstone in middle
         */
        ++y;
        for (i = -2; i < 3; ++i) {
            for (j = -2; j < 3; ++j) {
                if (i == 0 && j == 0) {
                    world.setBlock(x, y, z, Block.glowStone.blockID);
                } else if (Math.abs(i) + Math.abs(j) < 3) {
                    setBrick(world, x + i, y, z + j, m);
                }
            }
        }

        /*
         * 7 - smaller diamond
         */
        ++y;
        for (i = -1; i < 2; ++i) {
            for (j = -1; j < 2; ++j) {
                if (Math.abs(i) + Math.abs(j) < 2) {
                    setBrick(world, x + i, y, z + j, m);
                }
            }
        }

        /*
         * 8
         */
        ++y;
        setBrick(world, x, y, z, m);

        return true;
    }

    private void setBrick(World world, int x, int y, int z, int meta) {

        world.setBlock(x, y, z, ModBlocks.brick.blockID, meta, 2);
    }
}
