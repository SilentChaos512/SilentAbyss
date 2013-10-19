package silentAbyss.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class AbyssWorldData extends WorldSavedData {
	
	final static String key = "AbyssWorldData";
	public int chaos = 5000;

	public AbyssWorldData() {
		super(key);
	}
	
	public AbyssWorldData(String key) {
		super(key);
	}
	
	public static AbyssWorldData forWorld(World world) {
		MapStorage storage = world.perWorldStorage;
		AbyssWorldData result = (AbyssWorldData)storage.loadData(AbyssWorldData.class, key);
		
		if (result == null) {
			result = new AbyssWorldData();
			storage.setData(key, result);
		}
		
		return result;
	}
	
	public int getChaos() {
		return chaos;
	}
	
	public void setChaos(int chaos) {
		this.chaos = chaos;
		if (this.chaos < 0) {
			this.chaos = 0;
		}
		this.markDirty();
	}
	
	/** Increments chaos by the given amount. May be negative. */
	public void addChaos(int change) {
		this.chaos += change;
		if (this.chaos < 0) {
			this.chaos = 0;
		}
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		this.chaos = nbttagcompound.getInteger("Chaos");

	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setInteger("Chaos", this.chaos);
	}

}
