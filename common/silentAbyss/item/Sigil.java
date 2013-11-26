package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import silentAbyss.configuration.Config;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.sigil.SigilEffect;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.NBTHelper;
import silentAbyss.core.util.RecipeHelper;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: AbyssSigil class needs major cleanup.
public class Sigil extends ItemSA {

    @SideOnly(Side.CLIENT)
    Icon iconIdle, iconCharged;

    public Sigil(int par1) {

        super(par1);
        setMaxDamage(64); // TODO: Make config option
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName(Strings.SIGIL_NAME);

        isGlowing = true;
        rarity = EnumRarity.rare;
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {

        return stack2.getItem() instanceof Gem && stack2.getItemDamage() == EnumGem.CONUNDRUMITE.id;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        // TODO Change this
        if (stack.stackTagCompound != null) {
            if (getEffectID(stack) == 0) {
                list.add(LocalizationHelper.getMessageText(Strings.SIGIL_NO_EFFECT, ""));
                list.add(LocalizationHelper.getMessageText(Strings.REPAIRABLE, ""));
                return;
            }
            String color = LocalizationHelper.getLocalizedString("color.silentabyss:" + ItemDye.dyeColorNames[getColor(stack)]);
            String effectName = SigilEffect.all.get(getEffectID(stack)).getLocalizedName();
            int q = getPowerLevel(stack);
            String level = q == 1 ? "I" : (q == 2 ? "II" : (q == 3 ? "III" : (q == 4 ? "IV" : Integer.toString(q))));

            // Display color, effect, and power level
            list.add(EnumChatFormatting.GOLD + String.format("%s %s %s", color, effectName, level));

            // Display teleport coords (if applicable)
            if (effectName.equals(SigilEffect.teleport.name)) {
                list.add(LogHelper.coordFromNBT(stack.stackTagCompound));
            }

            // Display speed
            q = (getSpeedLevel(stack) - 1) * 15;
            if (q != 0) {
                list.add(LocalizationHelper.getLocalizedString(SigilRune.LOCALIZATION_PREFIX + "Speed") + " -" + q + "%");
            }
        }
        else {
            list.add(LocalizationHelper.getMessageText(Strings.SIGIL_NO_EFFECT, ""));
            list.add(LocalizationHelper.getMessageText(Strings.REPAIRABLE, ""));
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int itemInUseCount) {

        if (!world.isRemote) {
            int timeUsed = this.getMaxItemUseDuration(stack) - itemInUseCount;
            int effectId = getEffectID(stack);

            // Did player use item long enough?
            if (timeUsed < getChargeTime(stack)) {
                return;
            }

            if (SigilEffect.all.get(effectId).execute(stack, world, player)) {
                if (!player.capabilities.isCreativeMode) {
                    stack.attemptDamageItem(1, world.rand);
                    ChaosHandler.addChaos(Config.CHAOS_COST_SIGIL.value);
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        int j = getEffectID(stack);

        if (stack.stackTagCompound == null) {
            return stack;
        }
        else if (j == SigilEffect.teleport.id && !NBTHelper.hasValidXYZD(stack.stackTagCompound)) {
            player.addChatMessage(LocalizationHelper.getMessageText(Strings.SIGIL_TELEPORT_NOT_LINKED));
            return stack;
        }
        else if (j != 0) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }

    private void createNBTTagCompound(ItemStack stack) {

        if (stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
        }

        if (!stack.stackTagCompound.hasKey(Strings.SIGIL_COLOR)) {
            stack.stackTagCompound.setInteger(Strings.SIGIL_COLOR, 15);
        }
        if (!stack.stackTagCompound.hasKey(Strings.SIGIL_EFFECT_ID)) {
            stack.stackTagCompound.setInteger(Strings.SIGIL_EFFECT_ID, 0);
        }
        if (!stack.stackTagCompound.hasKey(Strings.SIGIL_POWER)) {
            stack.stackTagCompound.setInteger(Strings.SIGIL_POWER, 1);
        }
        if (!stack.stackTagCompound.hasKey(Strings.SIGIL_SPEED)) {
            stack.stackTagCompound.setInteger(Strings.SIGIL_SPEED, 1);
        }
    }

    private int getTag(ItemStack stack, String key, int defaultValue) {

        if (stack.stackTagCompound == null) {
            createNBTTagCompound(stack);
        }

        return stack.stackTagCompound.hasKey(key) ? stack.stackTagCompound.getInteger(key) : defaultValue;
    }

    private void setTag(ItemStack stack, String key, int value) {

        if (stack.stackTagCompound == null) {
            createNBTTagCompound(stack);
        }

        stack.stackTagCompound.setInteger(key, value);
    }

    public int getEffectID(ItemStack stack) {

        return getTag(stack, Strings.SIGIL_EFFECT_ID, 0);
    }

    public void setEffectID(ItemStack stack, int value) {

        setTag(stack, Strings.SIGIL_EFFECT_ID, value);
    }

    public int getColor(ItemStack stack) {

        return getTag(stack, Strings.SIGIL_COLOR, 0);
    }

    public void setColor(ItemStack stack, int value) {

        setTag(stack, Strings.SIGIL_COLOR, value);
    }

    public int getPowerLevel(ItemStack stack) {

        return getTag(stack, Strings.SIGIL_POWER, 1);
    }

    public void setPowerLevel(ItemStack stack, int value) {

        setTag(stack, Strings.SIGIL_POWER, value);
    }

    public int getSpeedLevel(ItemStack stack) {

        return getTag(stack, Strings.SIGIL_SPEED, 1);
    }

    public void setSpeedLevel(ItemStack stack, int value) {

        setTag(stack, Strings.SIGIL_SPEED, value);
    }

    public int getChargeTime(ItemStack stack) {

        int speed = getSpeedLevel(stack) - 1;

        return (int) (Config.SIGIL_USE_DURATION.value * (1.0 - 0.15 * speed));
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {

        return 133700;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {

        return EnumAction.bow;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        iconIdle = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "Sigil");
        iconCharged = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "SigilCharged");
        itemIcon = iconIdle;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {

        if (usingItem == null || usingItem.itemID != this.itemID) {
            return iconIdle;
        }

        int timeUsed = this.getMaxItemUseDuration(stack) - useRemaining;

        if (timeUsed > getChargeTime(stack)) {
            return iconCharged;
        }

        return iconIdle;
    }

    @Override
    public void addRecipes() {

        RecipeHelper.addSurround(new ItemStack(this), EnumGem.CONUNDRUMITE.getItem(), new Object[] { Item.redstone, Item.ingotGold });
    }
}
