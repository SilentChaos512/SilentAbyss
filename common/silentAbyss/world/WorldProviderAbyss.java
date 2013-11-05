package silentAbyss.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import silentAbyss.Abyss;
import silentAbyss.world.gen.ChunkProviderAbyss;

public class WorldProviderAbyss extends WorldProvider {

    @Override
    public void registerWorldChunkManager() {

        worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.extremeHills, 0.8F, 0.1F);
        dimensionId = Abyss.dimension;
    }

    @Override
    public String getDimensionName() {

        return "Abyss";
    }

    @Override
    public boolean canRespawnHere() {

        return true;
    }

    @Override
    public IChunkProvider createChunkGenerator() {

        return new ChunkProviderAbyss(worldObj, worldObj.getSeed(), true);
    }
}