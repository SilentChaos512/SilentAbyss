package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.chaos.ChaosEventCollection;
import silentAbyss.core.chaos.MeteorEvent;
import silentAbyss.core.util.LogHelper;
import silentAbyss.enchantment.EnchantmentMending;
import silentAbyss.item.armor.PersonalElevationDevice;
import silentAbyss.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;


public class PlayerServerTickHandler implements ITickHandler {
    
    public void onPlayerTick(EntityPlayer player) {
        
        mendingTick(player);
    }
    
    private void mendingTick(EntityPlayer player) {
        
        ItemStack stack;
        for (int i = 0; i < InventoryPlayer.getHotbarSize(); ++i) {
            stack = player.inventory.getStackInSlot(i);
            if (stack != null) {
                EnchantmentMending.tryActivate(stack);
            }
        }
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
        
        //LogHelper.debug("player s tick");
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

        return "PlayerServer";
    }

}
