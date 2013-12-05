package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.core.util.LogHelper;
import silentAbyss.lib.Names;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Food extends ItemFood {

    protected final String itemName;

    public Food(int id, int healAmount, float saturationModifier, boolean isWolfFavoriteMeat, String name) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION, healAmount, saturationModifier, isWolfFavoriteMeat);
        this.itemName = name;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add(LocalizationHelper.getMessageText(itemName));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        itemIcon = reg.registerIcon(Strings.RESOURCE_PREFIX + itemName);
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {

        super.onEaten(stack, world, player);

        if (itemName.equals(Names.POTATO_STICK)) {

        }
        else if (itemName.equals(Names.SUGAR_COOKIE)) {
            player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 600, 0, true));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 0, true));
        }

        return stack;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(itemName);

        return s.toString();
    }

    public static void addRecipes() {

        GameRegistry.addRecipe(new ItemStack(SARegistry.getItem(Names.POTATO_STICK)), " x", "y ", 'x', Item.bakedPotato, 'y', Item.stick);
        GameRegistry.addRecipe(new ItemStack(SARegistry.getItem(Names.SUGAR_COOKIE), 8, 0), " s ", "www", " s ", 's', Item.sugar, 'w', Item.wheat);
    }
}
