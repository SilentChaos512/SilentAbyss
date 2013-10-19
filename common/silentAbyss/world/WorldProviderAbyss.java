package silentAbyss.world;

import silentAbyss.Abyss;
import silentAbyss.world.gen.ChunkProviderAbyss;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderAbyss extends WorldProvider
{
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.extremeHills, 0.8F, 0.1F);
		this.dimensionId = Abyss.dimension;
	}
	
	public String getDimensionName() 
	{
		return "Abyss";
	}
	
	public boolean canRespawnHere()
	{
		return true;
	}
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderAbyss(worldObj, worldObj.getSeed(), true);
	}
}