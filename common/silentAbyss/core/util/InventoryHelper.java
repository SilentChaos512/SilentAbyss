package silentAbyss.core.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import silentAbyss.item.tool.AbyssAxe;
import silentAbyss.item.tool.AbyssHoe;
import silentAbyss.item.tool.AbyssPickaxe;
import silentAbyss.item.tool.AbyssShovel;
import silentAbyss.item.tool.AbyssSword;

public class InventoryHelper {

    public static boolean isAbyssTool(ItemStack stack) {

        if (stack != null) {
            Item item = stack.getItem();
            return item instanceof AbyssSword || item instanceof AbyssPickaxe || item instanceof AbyssShovel || item instanceof AbyssAxe
                    || item instanceof AbyssHoe;
        }
        return false;
    }
}
