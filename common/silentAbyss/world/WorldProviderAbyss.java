package silentAbyss.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import silentAbyss.configuration.Config;
import silentAbyss.world.biome.WorldChunkManagerAbyss;
import silentAbyss.world.gen.ChunkProviderAbyss;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderAbyss extends WorldProvider {

    private float[] colorsSunriseSunset = new float[4];
    
    @Override
    public void registerWorldChunkManager() {

        worldChunkMgr = new WorldChunkManagerAbyss(worldObj.getSeed(), terrainType);
        dimensionId = Config.DIMENSION.value;
        hasNoSky = false;
    }
    
    @Override
    public int getAverageGroundLevel() {
        
        return 0;
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
    
    @SideOnly(Side.CLIENT)
    @Override
    public String getWelcomeMessage() {
        
        if (this instanceof WorldProviderAbyss) {
            return "Entering The Abyss";
        }
        return null;
    }
}