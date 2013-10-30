package silentAbyss.core.handlers.event;

import silentAbyss.core.handlers.ChaosHandler;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;


public class WorldLoadEventHandler {

    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event) {
        
        if (event.world.provider.dimensionId == 0 && !ChaosHandler.initialized) {
            ChaosHandler.init(event.world);
        }
    }
}
