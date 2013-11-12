package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Food extends ItemFood {

    @SideOnly(Side.CLIENT)
    private Icon[] icons = new Icon[1];

    public Food(int id, int healAmount, float saturationModifier, boolean isWolfFavoriteMeat) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION, healAmount, saturationModifier, isWolfFavoriteMeat);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add(LocalizationHelper.getMessageText(Strings.POTATO_STICK_NAME));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        icons[0] = iconRegister.registerIcon("SilentAbyss:PotatoStick");
        itemIcon = icons[0];
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {

        super.onEaten(stack, world, player);
        if (stack.getItemDamage() == 0) {
            // Potato on a stick.
            // TODO: Return a stick to player?
        }

        return stack;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.POTATO_STICK_NAME);

        return s.toString();
    }

    public static void addRecipes() {

        GameRegistry.addRecipe(new ItemStack(ModItems.potatoStick), " x", "y ", 'x', Item.bakedPotato, 'y', Item.stick);
    }
}
