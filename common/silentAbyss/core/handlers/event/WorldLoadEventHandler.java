package silentAbyss.core.handlers.event;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import silentAbyss.core.handlers.ChaosHandler;

public class WorldLoadEventHandler {

    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event) {

        if (event.world.provider.dimensionId == 0 && !ChaosHandler.initialized) {
            ChaosHandler.init(event.world);
        }
    }
}
