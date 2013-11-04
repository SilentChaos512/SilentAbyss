package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import silentAbyss.configuration.Config;
import silentAbyss.item.ModItems;
import silentAbyss.item.armor.PersonalElevationDevice;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class PlayerClientServerTickHandler implements ITickHandler {
    
    public void onPlayerTick(EntityPlayer player) {
        
        personalElevationDeviceTick(player);
    }
    
    private void personalElevationDeviceTick(EntityPlayer player) {
        
        // Personal Elevation Device
        if (Config.PED_CREATIVE_FLIGHT) {
            if (PersonalElevationDevice.playerHasEquipped(player) && player.ridingEntity == null) {
                player.capabilities.allowFlying = true;
            }
            else if (!player.capabilities.isCreativeMode) {
                player.capabilities.allowFlying = false;
            }
        }
        else {
            if (PersonalElevationDevice.playerHasEquipped(player) && Minecraft.getMinecraft().gameSettings.keyBindJump.pressed) {
                if (player.motionY < 0.4) {
                    player.motionY += 0.15;
                }
            }
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        
        //LogHelper.debug("player cs tick");
        onPlayerTick((EntityPlayer)tickData[0]);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {

        return "PlayerClientServer";
    }

}
