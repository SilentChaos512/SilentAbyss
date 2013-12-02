package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import net.minecraft.world.World;
import silentAbyss.core.handlers.ChaosHandler;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class WorldScheduledTickHandler implements IScheduledTickHandler {

    public void onWorldTick(World world) {

        if (world.provider.dimensionId == 0) {
            ChaosHandler.doWorldTick(world);
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        onWorldTick((World) tickData[0]);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.WORLD);
    }

    @Override
    public String getLabel() {

        return "WorldScheduled";
    }

    @Override
    public int nextTickSpacing() {

        return 600;
    }

}
