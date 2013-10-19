package silentAbyss.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlayerHelper {

	/**
	 * Checks that the player is holding the given item. Also checks for null.
	 */
	public static boolean isPlayerHoldingItem(EntityPlayer player, Item item) {
		
		return player.inventory.getCurrentItem() != null &&
				item != null &&
				player.inventory.getCurrentItem().getItem().itemID == item.itemID;
	}
	
	/**
	 * Checks that the player is holding the given item. Also checks for null.
	 */
	public static boolean isPlayerHoldingItem(EntityPlayer player, ItemStack stack) {
		
		return player.inventory.getCurrentItem() != null &&
				stack != null &&
				player.inventory.getCurrentItem().getItem().itemID == stack.itemID;
	}
}
