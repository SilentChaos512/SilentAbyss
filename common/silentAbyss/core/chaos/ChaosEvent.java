package silentAbyss.core.chaos;

import net.minecraft.world.World;

public abstract class ChaosEvent {
	
	protected World worldObj;
	protected int posX, posY, posZ;
	protected boolean active = true;

	public ChaosEvent(World world, int x, int y, int z, Object... params) {
		
		worldObj = world;
		posX = x;
		posY = y;
		posZ = z;
	}
	
	public boolean isActive() {
		
		return active;
	}
	
	public abstract void doTick();
}
