package silentAbyss.core.handlers.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.util.LogHelper;
import silentAbyss.item.ModItems;


public class AbyssEventHandler {

    @ForgeSubscribe
    public void onLivingFallEvent(LivingFallEvent event) {

        if (event.entity != null && event.entity instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) event.entity;
            if (player.inventory.armorInventory[2] != null
                    && player.inventory.armorInventory[2].itemID == ModItems.personalElevationDevice.itemID) {
                // This cancels all fall damage while wearing this armor, which
                // is not what I want,
                // but the best I can do at the moment.
                event.setCanceled(true);
            }
        }
    }
    
    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event) {

        if (event.world.provider.dimensionId == 0 && !ChaosHandler.initialized) {
            ChaosHandler.init(event.world);
        }
    }
}
