package silentAbyss.block;

import net.minecraftforge.common.MinecraftForge;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.Names;

public class ModBlocks {

    // Default ids
    private final static int BRICK_ID = 3205;
    private final static int GLASS_ID = 3204;
    private final static int GEM_ID = 3201;
    private final static int LAMP_ID = 3206;
    private final static int LOG_ID = 3207;
    private final static int ORE_ID = 3200;
    private final static int PORTAL_ID = 3216;
    private final static int PORTAL_FRAME_ID = 3215;
    private final static int TELEPORTER_ID = 3210;

    public static void init() {

        SARegistry.registerBlock(Brick.class, Names.BRICK, BRICK_ID, BrickItemBlock.class);
        SARegistry.registerBlock(ClearGlass.class, Names.CLEAR_GLASS, GLASS_ID, ClearGlassItemBlock.class);
        SARegistry.registerBlock(GemBlock.class, Names.GEM_BLOCK, GEM_ID, GemItemBlock.class);
        SARegistry.registerBlock(Lamp.class, Names.LAMP, LAMP_ID, LampItemBlock.class);
        SARegistry.registerBlock(Log.class, Names.LOG, LOG_ID, LogItemBlock.class);
        SARegistry.registerBlock(Ore.class, Names.ORE, ORE_ID, OreItemBlock.class);
        SARegistry.registerBlock(Portal.class, Names.PORTAL, PORTAL_ID);
        SARegistry.registerBlock(PortalFrame.class, Names.PORTAL_FRAME, PORTAL_FRAME_ID);
        SARegistry.registerBlock(Teleporter.class, Names.TELEPORTER, TELEPORTER_ID);
        
        /*
         * Set harvest levels
         */
        MinecraftForge.setBlockHarvestLevel(SARegistry.getBlock(Names.ORE), "pickaxe", 2);
    }

    public static void initBlockRecipes() {

        Teleporter.addRecipes();
    }
}
