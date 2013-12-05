package silentAbyss.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import silentAbyss.core.registry.SARegistry;

public class ModBiomes {

    public static BiomeGenBase biome1 = new BiomeGen1(128);

    static {
        int id = 127;
        SARegistry.registerBiome(BiomeGen1.class, "Biome1", ++id);
    }
}
