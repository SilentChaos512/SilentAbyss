package silentAbyss.item.armor;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.item.Gem;
import silentAbyss.item.ItemSA;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * My flying device.
 * 
 * FIXME: Generates chaos when flying by other means (ie Morph).
 * TODO: Ability to equip by shift-clicking.
 * 
 * @author SilentChaos512
 * 
 */
public class PersonalElevationDevice extends ItemSA {

    public PersonalElevationDevice(int id) {

        super(id);
        setCreativeTab(CreativeTabs.tabCombat);
        setMaxStackSize(1);
    }

    @Override
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {

        return armorType == 1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":PersonalElevationDevice");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        // Equip to chest if possible.
        // Copied and simplified from
        // net.minecraft.item.ItemArmor.onItemRightClick
        if (player.getCurrentArmor(2) == null) {
            player.setCurrentItemOrArmor(3, stack.copy());
            stack.stackSize = 0;
        }

        return stack;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add(LocalizationHelper.getMessageText(Strings.PERSONAL_ELEVATION_DEVICE_NAME));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return new StringBuilder().append("item.").append(Strings.RESOURCE_PREFIX).append(Strings.PERSONAL_ELEVATION_DEVICE_NAME)
                .toString();
    }

    @Override
    public void addRecipes() {

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.personalElevationDevice), "cac", "cgc", "cpc", 'c',
                Gem.getGem(Reference.INDEX_CONUNDRUMITE), 'a', Gem.getGem(Reference.INDEX_ABYSSITE), 'p',
                Gem.getGem(Reference.INDEX_PURITE), 'g', Item.ingotGold);
    }

    /**
     * The player has this item equipped (checks for null).
     * 
     * @param player
     * @return
     */
    public static boolean playerHasEquipped(EntityPlayer player) {

        return player.inventory.armorInventory[2] != null
                && player.inventory.armorInventory[2].itemID == ModItems.personalElevationDevice.itemID;
    }

    /**
     * The player has this item equipped, is flying, and is not in creative
     * mode.
     * 
     * @param player
     * @return
     */
    public static boolean playerIsFlyingWith(EntityPlayer player) {

        return playerHasEquipped(player) && player.capabilities.isFlying && !player.capabilities.isCreativeMode;
    }

}
