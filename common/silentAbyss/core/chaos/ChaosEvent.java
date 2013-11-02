package silentAbyss.core.chaos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class ChaosEvent {
	
	public EntityPlayer player;
	protected int posX, posY, posZ;
	protected boolean active = true;

	public ChaosEvent(EntityPlayer player, int x, int y, int z, Object... params) {
		
		this.player = player;
		posX = x;
		posY = y;
		posZ = z;
	}
	
	public boolean isActive() {
		
		return active;
	}
	
	public void updatePosition(int x, int y, int z) {
	    posX = x;
	    posY = y;
	    posZ = z;
	}
	
	public abstract void doTick();
}
