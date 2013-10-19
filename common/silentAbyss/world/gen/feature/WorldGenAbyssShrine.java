package silentAbyss.world.gen.feature;

import java.util.Random;

import silentAbyss.block.ModBlocks;
import silentAbyss.core.util.WorldGenHelper;
import silentAbyss.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

public class WorldGenAbyssShrine extends WorldGenerator {
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		
		while (world.isAirBlock(x, y, z)) {
			--y;
		}
		
		// Only spawn at/above sea level
		if (y < 64) {
			return true;
		}
		
		// Only spawn on ground blocks.
		if (!WorldGenHelper.isBlockGround(world.getBlockId(x, y, z))) {
			return true;
		}
		
		int i = 0, j = 0;
		
		// Check for a 5x5 foundation
		for (i = -2; i < 3; ++i) {
			for (j = -2; j < 3; ++j) {
				if (world.isAirBlock(x + i, y - 1, z + j)) {
					return true;
				}
			}
		}
		
		// Check the layer above for non-ground blocks (such as trees). Ignore tall grass and snow layers.
		for (int k = 1; k < 4; ++k) {
			for (i = -3; i < 4; ++i) {
				for (j = -3; j < 4; ++j) {
					if (!world.isAirBlock(x + i, y + k, z + j) && !WorldGenHelper.isBlockGround(world.getBlockId(x + i, y + k, z + j)) &&
							world.getBlockId(x + i, y + k, z + j) != Block.tallGrass.blockID && world.getBlockId(x + i, y + k, z + j) != Block.snow.blockID) {
						return true;
					}
				}
			}
		}
		
		// 1
		for (i = -3; i < 4; ++i) {
			for (j = -3; j < 4; ++j) {
				// Skip corners
				if (!((i == -3 || i == 3) && (j == -3 || j == 3))) {
					setDarkBrick(world, x + i, y, z + j);
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
		
		// 2
		++y;
		setDarkBrick(world, x - 2, y, z - 2);
		setDarkBrick(world, x - 2, y, z + 2);
		setDarkBrick(world, x + 2, y, z - 2);
		setDarkBrick(world, x + 2, y, z + 2);
		world.setBlock(x, y, z, Block.chest.blockID);
		// Get loot for chest
		TileEntityChest tile = (TileEntityChest)world.getBlockTileEntity(x, y, z);
		if (tile != null) {
			//LogHelper.debug("lol");
			ChestGenHooks info = ChestGenHooks.getInfo(Strings.SHRINE_CHEST);
			info.setMin(8);
			info.setMax(12);
			WeightedRandomChestContent.generateChestContents(random, info.getItems(random), tile, info.getCount(random));
		}
		
		// 3
		++y;
		setDarkBrick(world, x - 2, y, z - 2);
		setDarkBrick(world, x - 2, y, z + 2);
		setDarkBrick(world, x + 2, y, z - 2);
		setDarkBrick(world, x + 2, y, z + 2);
		
		// 4
		++y;
		setDarkBrick(world, x - 2, y, z - 2);
		setDarkBrick(world, x - 2, y, z - 1);
		setDarkBrick(world, x - 1, y, z - 2);
		setDarkBrick(world, x - 2, y, z + 2);
		setDarkBrick(world, x - 2, y, z + 1);
		setDarkBrick(world, x - 1, y, z + 2);
		setDarkBrick(world, x + 2, y, z - 2);
		setDarkBrick(world, x + 2, y, z - 1);
		setDarkBrick(world, x + 1, y, z - 2);
		setDarkBrick(world, x + 2, y, z + 2);
		setDarkBrick(world, x + 2, y, z + 1);
		setDarkBrick(world, x + 1, y, z + 2);
		
		// 5
		++y;
		for (i = -3; i < 4; ++i) {
			for (j = -3; j < 4; ++j) {
				// Skip corners
				if (!((i == -3 || i == 3) && (j == -3 || j == 3))) {
					setDarkBrick(world, x + i, y, z + j);
				}
			}
		}
		
		// 6 - diamond shape
		++y;
		for (i = -2; i < 3; ++i) {
			for (j = -2; j < 3; ++j) {
				if (Math.abs(i) + Math.abs(j) < 3) {
					setDarkBrick(world, x + i, y, z + j);
				}
			}
		}
		
		// 7 - smaller diamond
		++y;
		for (i = -1; i < 2; ++i) {
			for (j = -1; j < 2; ++j) {
				if (Math.abs(i) + Math.abs(j) < 2) {
					setDarkBrick(world, x + i, y, z + j);
				}
			}
		}
		
		// 8
		++y;
		setDarkBrick(world, x, y, z);
		
		return true;
	}
	
	private void setDarkBrick(World world, int x, int y, int z) {
		world.setBlock(x, y, z, ModBlocks.brick.blockID, 4, 2);
	}
}
