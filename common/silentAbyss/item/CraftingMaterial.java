package silentAbyss.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.core.util.RecipeHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CraftingMaterial extends ItemSA {

    public static final String[] names = { Strings.ORNATE_STICK_NAME, Strings.MYSTERY_GOO_NAME, Strings.YARN_BALL_NAME };

    public CraftingMaterial(int id) {

        super(id);
        icons = new Icon[names.length];
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public static ItemStack getStack(String name) {

        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(name)) {
                return new ItemStack(ModItems.craftingMaterial, 1, i);
            }
        }

        return null;
    }

    public static ItemStack getStack(String name, int count) {

        for (int i = 0; i < names.length; ++i) {
            if (names[i].equals(name)) {
                return new ItemStack(ModItems.craftingMaterial, count, i);
            }
        }

        return null;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add(LocalizationHelper.getMessageText(Strings.CRAFTING_MATERIAL_NAME, EnumChatFormatting.DARK_GRAY));
        list.add(LocalizationHelper.getMessageText(names[stack.getItemDamage()]));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return getUnlocalizedName(names[stack.getItemDamage()]);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        for (int i = 0; i < names.length; ++i) {
            icons[i] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + names[i]);
        }
    }

    @Override
    public void addRecipes() {

        ItemStack rAbyssite = Gem.getGem(Reference.INDEX_ABYSSITE);

        GameRegistry.addRecipe(getStack(Strings.ORNATE_STICK_NAME, 4), "gag", "gag", "gag", 'g', Item.ingotGold, 'a', rAbyssite);
        GameRegistry.addRecipe(getStack(Strings.MYSTERY_GOO_NAME, 1), "mmm", "mam", "mmm", 'm', Block.cobblestoneMossy, 'a', rAbyssite);
        RecipeHelper.addSurround(getStack(Strings.YARN_BALL_NAME), new ItemStack(Item.goldNugget), Item.silk);
    }
}
