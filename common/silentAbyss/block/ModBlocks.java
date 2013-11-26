package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import silentAbyss.lib.BlockIds;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

    // Mod block instances
    public static BlockSA ore;
    public static BlockSA gem;
    public static Block abyssTeleporter;
    public static BlockSA brick;
    public static BlockSA lamp;
    public static BlockSA portalFrame;
    public static Block portal;
    public static Block blockTest;

    public static void init() {

        /*
         * Initialize blocks
         */
        ore = new Ore(BlockIds.ABYSS_GEM_ORE);
        gem = new GemBlock(BlockIds.ABYSS_GEM_BLOCK);
        abyssTeleporter = new Teleporter(BlockIds.ABYSS_TELEPORTER);
        brick = new Brick(BlockIds.BRICK);
        lamp = new Lamp(BlockIds.LAMP);
        portalFrame = new PortalFrame(BlockIds.PORTAL_FRAME);
        portal = new Portal(BlockIds.PORTAL);
        blockTest = new BlockTest(BlockIds.TEST_BLOCK);

        /*
         * Register blocks
         */
        GameRegistry.registerBlock(ore, OreItemBlock.class, Strings.ORE_NAME);
        GameRegistry.registerBlock(gem, GemItemBlock.class, Strings.GEM_BLOCK_NAME);
        GameRegistry.registerBlock(abyssTeleporter, Strings.TELEPORTER_NAME);
        GameRegistry.registerBlock(brick, BrickItemBlock.class, Strings.BRICK_NAME);
        GameRegistry.registerBlock(lamp, LampItemBlock.class, Strings.LAMP_NAME);
        GameRegistry.registerBlock(portalFrame, Strings.PORTAL_FRAME_NAME);
        GameRegistry.registerBlock(portal, Strings.PORTAL_NAME);
        GameRegistry.registerBlock(blockTest, Strings.TEST_BLOCK_NAME);

        /*
         * Set harvest levels
         */
        MinecraftForge.setBlockHarvestLevel(ore, "pickaxe", 2);

        /*
         * Ore dictionary
         */
        OreDictionary.registerOre("oreRuby", new ItemStack(ore, 1, EnumGem.RUBY.id));
        OreDictionary.registerOre("oreEmerald", new ItemStack(ore, 1, EnumGem.EMERALD.id));
        OreDictionary.registerOre("oreSapphire", new ItemStack(ore, 1, EnumGem.SAPPHIRE.id));
        OreDictionary.registerOre("oreTopaz", new ItemStack(ore, 1, EnumGem.TOPAZ.id));
        OreDictionary.registerOre("oreAbyssite", new ItemStack(ore, 1, EnumGem.ABYSSITE.id));
        OreDictionary.registerOre("orePurite", new ItemStack(ore, 1, EnumGem.PURITE.id));
    }

    public static void initBlockRecipes() {

        gem.addRecipes();
        Teleporter.addRecipes();
        brick.addRecipes();
        lamp.addRecipes();
        portalFrame.addRecipes();
    }
}
