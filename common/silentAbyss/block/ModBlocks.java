
package silentAbyss.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.BlockIds;
import silentAbyss.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModBlocks {

	// Mod block instances
	public static Block oreAbyssGem;
	public static Block oreAbyssite;
	public static Block blockAbyssGem;
	public static Block blockAbyssite;
	public static Block abyssTeleporter;
	public static Block sigilInfuser;
	public static Block brick;
	public static Block brickSlab;
	public static Block blockTest;
	
	public static void init() {
		
		/*
		 * Initialize blocks
		 */
		oreAbyssGem = new AbyssOre(BlockIds.ABYSS_GEM_ORE);
		oreAbyssite = new AbyssiteOre(BlockIds.ABYSSITE_ORE);
		blockAbyssGem = new AbyssGemBlock(BlockIds.ABYSS_GEM_BLOCK);
		blockAbyssite = new AbyssiteBlock(BlockIds.ABYSSITE_BLOCK);
		abyssTeleporter = new BlockAbyssTeleporter(BlockIds.ABYSS_TELEPORTER);
		sigilInfuser = new BlockSigilInfuser(BlockIds.SIGIL_INFUSER);
		brick = new Brick(BlockIds.BRICK);
		brickSlab = new BrickSlab(BlockIds.BRICK_SLAB, false);
		blockTest = new BlockTest(BlockIds.TEST_BLOCK);
		
		/*
		 * Register blocks
		 */
		GameRegistry.registerBlock(oreAbyssGem, AbyssOreItemBlock.class, Strings.ABYSS_GEM_ORE_NAME);
		GameRegistry.registerBlock(oreAbyssite, Strings.ABYSSITE_ORE_NAME);
		GameRegistry.registerBlock(blockAbyssGem, AbyssGemItemBlock.class, Strings.ABYSS_GEM_BLOCK_NAME);
		GameRegistry.registerBlock(blockAbyssite, Strings.ABYSSITE_BLOCK_NAME);
		GameRegistry.registerBlock(abyssTeleporter, Strings.ABYSS_TELEPORTER_NAME);
		GameRegistry.registerBlock(sigilInfuser, Strings.SIGIL_INFUSER_NAME);
		GameRegistry.registerBlock(brick, BrickItemBlock.class, Strings.BRICK_NAME);
		GameRegistry.registerBlock(brickSlab, BrickSlabItemBlock.class, Strings.BRICK_SLAB_NAME);
		GameRegistry.registerBlock(blockTest, Strings.TEST_BLOCK_NAME);
		
		/*
		 * Set harvest levels
		 */
		MinecraftForge.setBlockHarvestLevel(oreAbyssGem, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(oreAbyssite, "pickaxe", 2);
	}
	
	public static void initBlockRecipes() {
		
		AbyssGemBlock.addRecipes();
		AbyssiteBlock.addRecipes();
		BlockAbyssTeleporter.addRecipes();
		BlockSigilInfuser.addRecipes();
		Brick.addRecipes();
		BrickSlab.addRecipes();
	}
}
