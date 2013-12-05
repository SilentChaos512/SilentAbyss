package silentAbyss.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;


public class GenLayerAbyss extends GenLayer {

    public GenLayerAbyss(long seed) {

        super(seed);
    }
    
    public static GenLayer[] makeWorld(long seed) {
        
        GenLayer biomes = new GenLayerBiomesAbyss(1L);
        
        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerZoom(1002L, biomes);
        biomes = new GenLayerZoom(1003L, biomes);
        biomes = new GenLayerZoom(1004L, biomes);
        biomes = new GenLayerZoom(1005L, biomes);
        
        GenLayer genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, biomes);
        
        biomes.initWorldGenSeed(seed);
        genLayerVoronoiZoom.initWorldGenSeed(seed);
        
        return new GenLayer[] { biomes, genLayerVoronoiZoom };
    }

    @Override
    public int[] getInts(int i, int j, int k, int l) {

        // TODO Auto-generated method stub
        return null;
    }

}
