package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSA extends Item {

    public Icon[] icons = null;
    protected boolean basicGemSubtypes = false;
    protected String itemName = "";
    protected boolean isGlowing = false;
    protected EnumRarity rarity = EnumRarity.common;

    public ItemSA(int id) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
        setUnlocalizedName(Integer.toString(id));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        
        String s = LocalizationHelper.getMessageText(itemName);
        if (!s.equals(EnumChatFormatting.ITALIC + LocalizationHelper.MISC_PREFIX + itemName)) {
            list.add(s);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        if (basicGemSubtypes) {
            registerIconsForBasicGems(reg);
        }
        else {
            itemIcon = reg.registerIcon(Strings.RESOURCE_PREFIX + itemName);
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIconsForBasicGems(IconRegister reg) {

        if (icons == null || icons.length != EnumGem.basic().length) {
            icons = new Icon[EnumGem.basic().length];
        }

        for (int i = 0; i < EnumGem.basic().length; ++i) {
            icons[i] = reg.registerIcon(Strings.RESOURCE_PREFIX + this.itemName + EnumGem.basic()[i].name);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {

        return isGlowing;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {

        return rarity;
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

        int d = stack.getItemDamage();

        if (basicGemSubtypes && d < EnumGem.basic().length) {
            return getUnlocalizedName(itemName + EnumGem.basic()[d].name, "item");
        }
        else if (hasSubtypes) {
            return getUnlocalizedName(itemName + "-" + Integer.toString(d), "item");
        }
        else {
            return getUnlocalizedName(itemName, "item");
        }
    }

    public String getUnlocalizedName(String itemName) {

        return getUnlocalizedName(itemName, "item");
    }

    public String getUnlocalizedName(String itemName, String prefix) {

        return (new StringBuilder()).append(prefix).append(".").append(Strings.RESOURCE_PREFIX).append(itemName).toString();
    }

    @Override
    public Item setUnlocalizedName(String itemName) {

        this.itemName = itemName;
        return super.setUnlocalizedName(itemName);
    }

    public void setHasBasicGemSubtypes(boolean value) {

        basicGemSubtypes = value;
    }

    /**
     * Adds all recipes to make this item to the GameRegistry. Used to separate
     * out recipe code so that ModItems is easier to read.
     */
    public void addRecipes() {

    }
    
    /**
     * Should be overridden if the deriving class needs ore dictionary entries.
     */
    public void addOreDict() {

    }
}
