package silentAbyss.core.util;

import silentAbyss.item.tool.AbyssAxe;
import silentAbyss.item.tool.AbyssHoe;
import silentAbyss.item.tool.AbyssPickaxe;
import silentAbyss.item.tool.AbyssShovel;
import silentAbyss.item.tool.AbyssSword;
import silentAbyss.item.tool.TeleporterLinker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class PlayerHelper {

    /**
     * Checks that the player is holding the given item. Also checks for null.
     */
    public static boolean isPlayerHoldingItem(EntityPlayer player, Item item) {

        return player.inventory.getCurrentItem() != null && item != null
                && player.inventory.getCurrentItem().getItem().itemID == item.itemID;
    }

    /**
     * Checks that the player is holding the given item. Also checks for null.
     */
    public static boolean isPlayerHoldingItem(EntityPlayer player, ItemStack stack) {

        return player.inventory.getCurrentItem() != null && stack != null
                && player.inventory.getCurrentItem().getItem().itemID == stack.itemID;
    }

    public static boolean isPlayerHoldingToolToCreatePortal(EntityPlayer player) {

        if (player.inventory.getCurrentItem() != null) {
            Item item = player.inventory.getCurrentItem().getItem();
            return item instanceof TeleporterLinker || item instanceof AbyssSword || item instanceof AbyssPickaxe
                    || item instanceof AbyssShovel || item instanceof AbyssAxe || item instanceof AbyssHoe;
        }
        return false;
    }
    
    public static ItemStack getStackAfterEquipped(EntityPlayer player) {
        
        int i = player.inventory.currentItem + 1;
        
        if (i >= player.inventory.getHotbarSize()) {
            return null;
        }
        
        return player.inventory.getStackInSlot(i);
    }
    
    public static void addChatMessage(EntityPlayer player, String key, boolean fromLocalizationFile) {
        
        if (player.worldObj.isRemote) {
            if (fromLocalizationFile) {
                player.addChatMessage(LocalizationHelper.getMessageText(key, ""));
            }
            else {
                player.addChatMessage(key);
            }
        }
    }
    
    public static void addChatMessage(EntityPlayer player, String key, EnumChatFormatting format, boolean fromLocalizationFile) {
        
        if (player.worldObj.isRemote) {
            if (fromLocalizationFile) {
                player.addChatMessage(LocalizationHelper.getMessageText(key, format));
            }
            else {
                player.addChatMessage(key);
            }
        }
    }
}
