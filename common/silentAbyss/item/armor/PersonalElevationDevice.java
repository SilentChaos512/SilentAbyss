package silentAbyss.item.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.item.ItemSA;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * My flying device.
 * 
 * FIXME: Generates chaos when flying by other means (ie Morph). TODO: Ability to equip by shift-clicking.
 * 
 * @author SilentChaos512
 * 
 */
public class PersonalElevationDevice extends ItemSA {

    public PersonalElevationDevice(int id) {

        super(id);
        setCreativeTab(CreativeTabs.tabCombat);
        setMaxStackSize(1);
        setUnlocalizedName(Names.PERSONAL_ELEVATION_DEVICE);
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
        // Copied and simplified from net.minecraft.item.ItemArmor.onItemRightClick
        if (player.getCurrentArmor(2) == null) {
            player.setCurrentItemOrArmor(3, stack.copy());
            stack.stackSize = 0;
        }

        return stack;
    }

    @Override
    public void addRecipes() {

        GameRegistry.addShapedRecipe(new ItemStack(SARegistry.getItem(Names.PERSONAL_ELEVATION_DEVICE)), "cac", "cgc", "cpc", 'c',
                EnumGem.CONUNDRUMITE.getItem(), 'a', EnumGem.ABYSSITE.getItem(), 'p', EnumGem.PURITE.getItem(), 'g', Item.ingotGold);
    }

    /**
     * The player has this item equipped (checks for null).
     * 
     * @param player
     * @return
     */
    public static boolean playerHasEquipped(EntityPlayer player) {

        return player.inventory.armorInventory[2] != null
                && player.inventory.armorInventory[2].itemID == SARegistry.getItem(Names.PERSONAL_ELEVATION_DEVICE).itemID;
    }

    /**
     * The player has this item equipped, is flying, and is not in creative mode.
     * 
     * @param player
     * @return
     */
    public static boolean playerIsFlyingWith(EntityPlayer player) {

        return playerHasEquipped(player) && player.capabilities.isFlying && !player.capabilities.isCreativeMode;
    }

}
