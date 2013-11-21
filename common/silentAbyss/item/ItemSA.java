package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSA extends Item {

    public Icon[] icons;

    public ItemSA(int id) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta) {

        if (hasSubtypes && icons != null && meta < icons.length) {
            return icons[meta];
        }
        else {
            return super.getIconFromDamage(meta);
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {

        if (hasSubtypes) {
            for (int i = 0; i < icons.length; ++i) {
                list.add(new ItemStack(this, 1, i));
            }
        }
        else {
            list.add(new ItemStack(this, 1, 0));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return (new StringBuilder()).append("item.").append(Strings.RESOURCE_PREFIX).append(this.itemID).append("-")
                .append(stack.getItemDamage()).toString();
    }

    public String getUnlocalizedName(String itemName) {

        return getUnlocalizedName(itemName, "item");
    }

    public String getUnlocalizedName(String itemName, String prefix) {

        return (new StringBuilder()).append(prefix).append(".").append(Strings.RESOURCE_PREFIX).append(itemName).toString();
    }

    /**
     * Adds all recipes to make this item to the GameRegistry. Used to separate
     * out recipe code so that ModItems is easier to read.
     */
    public void addRecipes() {

    };
}
