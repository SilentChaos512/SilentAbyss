package silentAbyss.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import silentAbyss.world.biome.ModBiomes;

public class GenLayerBiomesAbyss extends GenLayer {

    protected BiomeGenBase[] allowedBiomes = { ModBiomes.biome1 };

    public GenLayerBiomesAbyss(long seed, GenLayer genLayer) {

        super(seed);
        this.parent = genLayer;
    }

    public GenLayerBiomesAbyss(long seed) {

        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {

        int[] dest = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
            }
        }
        return dest;

    }

}
