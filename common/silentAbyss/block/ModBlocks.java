package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import silentAbyss.lib.BlockIds;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

    // Mod block instances
    public static Block oreAbyssGem;
    public static Block blockAbyssGem;
    public static Block abyssTeleporter;
    public static Block sigilInfuser;
    public static Block brick;
    public static Block brickSlab;
    public static Block blockTest;

    public static void init() {

        /*
         * Initialize blocks
         */
        oreAbyssGem = new Ore(BlockIds.ABYSS_GEM_ORE);
        blockAbyssGem = new GemBlock(BlockIds.ABYSS_GEM_BLOCK);
        abyssTeleporter = new Teleporter(BlockIds.ABYSS_TELEPORTER);
        sigilInfuser = new SigilInfuser(BlockIds.SIGIL_INFUSER);
        brick = new Brick(BlockIds.BRICK);
        brickSlab = new BrickSlab(BlockIds.BRICK_SLAB, false);
        blockTest = new BlockTest(BlockIds.TEST_BLOCK);

        /*
         * Register blocks
         */
        GameRegistry.registerBlock(oreAbyssGem, OreItemBlock.class, Strings.ABYSS_GEM_ORE_NAME);
        GameRegistry.registerBlock(blockAbyssGem, GemItemBlock.class, Strings.ABYSS_GEM_BLOCK_NAME);
        GameRegistry.registerBlock(abyssTeleporter, Strings.ABYSS_TELEPORTER_NAME);
        GameRegistry.registerBlock(sigilInfuser, Strings.SIGIL_INFUSER_NAME);
        GameRegistry.registerBlock(brick, BrickItemBlock.class, Strings.BRICK_NAME);
        GameRegistry.registerBlock(brickSlab, BrickSlabItemBlock.class, Strings.BRICK_SLAB_NAME);
        GameRegistry.registerBlock(blockTest, Strings.TEST_BLOCK_NAME);

        /*
         * Set harvest levels
         */
        MinecraftForge.setBlockHarvestLevel(oreAbyssGem, "pickaxe", 2);
    }

    public static void initBlockRecipes() {

        GemBlock.addRecipes();
        Teleporter.addRecipes();
        SigilInfuser.addRecipes();
        Brick.addRecipes();
        BrickSlab.addRecipes();
    }
}
