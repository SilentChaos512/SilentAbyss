package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CraftingMaterial extends ItemSA {

    public static final String[] names = { "ornateStick" };
    public static Icon[] icons = new Icon[names.length];

    public CraftingMaterial(int id) {

        super(id);
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public static ItemStack getStack(String name) {

        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(name)) { return new ItemStack(ModItems.craftingMaterial, 1, i); }
        }

        return null;
    }

    public static ItemStack getStack(String name, int count) {

        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(name)) { return new ItemStack(ModItems.craftingMaterial, count, i); }
        }

        return null;
    }

    @Override
    public Icon getIconFromDamage(int i) {

        return icons[i];
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list) {

        for (int i = 0; i < icons.length; ++i) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(names[itemStack.getItemDamage()]);
        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        for (int i = 0; i < names.length; ++i) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + names[i]);
        }
    }

    public static void addRecipes() {

        ItemStack rAbyssite = AbyssGem.getGem(Reference.INDEX_ABYSSITE);

        GameRegistry.addRecipe(getStack("ornateStick", 4), "gag", "gag", "gag", 'g', Item.ingotGold, 'a', rAbyssite);
    }
}
