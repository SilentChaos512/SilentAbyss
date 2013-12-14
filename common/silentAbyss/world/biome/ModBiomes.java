package silentAbyss.world.biome;

import silentAbyss.core.registry.SARegistry;
import silentAbyss.core.util.LogHelper;
import silentAbyss.lib.Names;

public class ModBiomes {

    public static void init() {
        
        LogHelper.debug("derp");
        int id = 127;
        SARegistry.registerBiome(BiomeGen1.class, Names.BIOME_DEFAULT, ++id);
    }
}
