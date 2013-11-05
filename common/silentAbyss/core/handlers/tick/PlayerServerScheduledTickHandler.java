package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.chaos.ChaosEventCollection;
import silentAbyss.core.chaos.MeteorEvent;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.item.armor.PersonalElevationDevice;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerServerScheduledTickHandler implements IScheduledTickHandler {

    ChaosEventCollection chaosEvents = new ChaosEventCollection();

    public void onPlayerTick(EntityPlayer player) {

        chaosTick(player);
    }

    private void chaosTick(EntityPlayer player) {

        /*
         * Chaos costs
         */

        // Personal elevation device
        if (PersonalElevationDevice.playerIsFlyingWith(player)) {
            ChaosHandler.addChaos(Config.CHAOS_COST_PERSONAL_ELEVATION_DEVICE.value);
        }

        /*
         * Chaos events
         */

        // Meteor showers
        if (Abyss.rng.nextInt((int) (Config.METEOR_SHOWER_RARITY.value / ChaosHandler.getChaosFactor())) == 0) {
            player.addChatMessage(Strings.METEOR_SHOWER_INBOUND);
            chaosEvents.add(new MeteorEvent(player, (int) player.posX, (int) player.posY, (int) player.posZ));
        }

        chaosEvents.doTick(player);
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        onPlayerTick((EntityPlayer) tickData[0]);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {

        return "PlayerServerScheduled";
    }

    @Override
    public int nextTickSpacing() {

        return 20;
    }

}
