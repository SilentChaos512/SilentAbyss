package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import net.minecraft.world.World;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

    public static final int baseWorldTickRate = 600;

    public void onServerTick() {

    }

    public void onWorldTick(World world) {

    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        if (type.equals(EnumSet.of(TickType.SERVER))) {
            onServerTick();
        } else if (type.equals(EnumSet.of(TickType.WORLD))) {
            onWorldTick((World) tickData[0]);
        }
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.SERVER, TickType.WORLD);
    }

    @Override
    public String getLabel() {

        return "Server";
    }

}
