package silentAbyss.block;

import silentAbyss.lib.Strings;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSA extends ItemBlock {

    public ItemBlockSA(int id) {

        super(id);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {

        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return (new StringBuilder()).append("tile.").append(Strings.RESOURCE_PREFIX).append(stack.getItem().itemID).append("-")
                .append(stack.getItemDamage()).toString();
    }

    public String getUnlocalizedName(String tileName) {

        return (new StringBuilder()).append("tile.").append(Strings.RESOURCE_PREFIX).append(tileName).toString();
    }
}
