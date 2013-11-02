package silentAbyss.core.chaos;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class ChaosEventCollection {
	
	private ArrayList<ChaosEvent> events = new ArrayList<ChaosEvent>();
	
	public void add(ChaosEvent e) {
		
		events.add(e);
	}
	
	public void doTick(EntityPlayer player) {
		
		// Remove all inactive events
		ArrayList<ChaosEvent> deadEvents = new ArrayList<ChaosEvent>();
		for (ChaosEvent e : events) {
			if (!e.isActive()) {
				deadEvents.add(e);
			}
		}
		events.removeAll(deadEvents);
		//LogHelper.debug(events.size());
		
		// Tick each event.
		for (ChaosEvent e : events) {
		    if (player.equals(e.player)) {
		        e.updatePosition((int)player.posX, (int)player.posY, (int)player.posZ);
		        e.doTick();
		    }
		}
	}
}
