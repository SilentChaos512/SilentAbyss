package silentAbyss.core.chaos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import silentAbyss.core.util.LogHelper;

public class ChaosEventCollection {
	
	private ArrayList<ChaosEvent> events = new ArrayList<ChaosEvent>();
	
	public void add(ChaosEvent e) {
		
		events.add(e);
	}
	
	public void doTick() {
		
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
			e.doTick();
		}
	}
}
