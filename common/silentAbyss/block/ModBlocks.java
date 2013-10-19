
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
	//public static Block brickSlabDouble;
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
		
		/*
		 * Language registry
		 * FIXME: In 1.6, use a localization file/resource pack instead.
		 */
//		AbyssOre.registerLocalizedNames();
//		LanguageRegistry.addName(oreAbyssite, "Abyssite Ore");
//		AbyssGemBlock.registerLocalizedNames();
//		LanguageRegistry.addName(blockAbyssite, "Abyssite Block");
//		LanguageRegistry.addName(abyssTeleporter, "Abyss Teleporter");
//		LanguageRegistry.addName(sigilInfuser, "Sigil Infuser");
//		Brick.registerLocalizedNames();
//		BrickSlab.registerLocalizedNames();
//		LanguageRegistry.addName(blockTest, "Test Block");
	}
	
	public static void initBlockRecipes() {
		
		ItemStack rAbyssite				= new ItemStack(ModItems.abyssite);
		
		// Gem blocks
		GameRegistry.addShapelessRecipe(new ItemStack(blockAbyssite),
				rAbyssite, rAbyssite, rAbyssite,
				rAbyssite, rAbyssite, rAbyssite,
				rAbyssite, rAbyssite, rAbyssite);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssite, 9), new ItemStack(blockAbyssite));
		for (int i = 0; i < AbyssGem.names.length; ++i) {
			ItemStack gem = new ItemStack(ModItems.abyssGem, 1, i);
			ItemStack block = new ItemStack(blockAbyssGem, 1, i);
			// Gems to block
			GameRegistry.addShapelessRecipe(block,
					gem, gem, gem,
					gem, gem, gem,
					gem, gem, gem);
			// Block to gems
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssGem, 9, i), block);
		}
		
		// Teleporters
		GameRegistry.addRecipe(new ItemStack(abyssTeleporter, 2), "gwg", "geg", "gag",
				'g', new ItemStack(Item.ingotGold), 'w', new ItemStack(Block.cloth),
				'e', new ItemStack(Item.enderPearl), 'a', new ItemStack(blockAbyssite));
		GameRegistry.addRecipe(new ItemStack(ModItems.teleporterLinker), "x", "y",
				'x', rAbyssite, 'y', new ItemStack(Item.ingotGold));
		
		// Sigil infuser
		GameRegistry.addRecipe(new ItemStack(sigilInfuser), "aaa", "aea",
				'a', rAbyssite, 'e', Block.enchantmentTable);
		
		Brick.addRecipes();
		BrickSlab.addRecipes();
	}
}
