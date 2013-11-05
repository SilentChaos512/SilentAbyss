package silentAbyss.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;

public class EnchantmentMending extends Enchantment {

    // The chance of the nth enchantment level repairing the tool each tick.
    private final static int[] rates = { 200, 100, 50, 25, 12, 6, 3, 2, 1, 1 };

    public EnchantmentMending(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {

        super(par1, par2, par3EnumEnchantmentType);
        setName(Strings.MENDING_NAME);
    }

    public static void tryActivate(ItemStack itemStack) {

        if (itemStack.getItemDamage() == 0 || !itemStack.isItemStackDamageable()) { return; }

        // Get enchantment level. Only evaluate if level is between 1 and 10,
        // inclusive.
        int lvl = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.mending.effectId, itemStack);
        if (lvl < 1) { return; }
        if (lvl > 10) {
            lvl = 10;
        }

        if (Abyss.rng.nextInt(rates[lvl - 1]) == 0) {
            itemStack.setItemDamage(itemStack.getItemDamage() - 1);
            // Chaos cost
            ChaosHandler.addChaos(Config.CHAOS_COST_MENDING.value);
        }
    }

    @Override
    public int getMinEnchantability(int par1) {

        return 5 + (par1 - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int par1) {

        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public int getMaxLevel() {

        return 3;
    }

    @Override
    public boolean canApply(ItemStack par1ItemStack) {

        // This enchantment is for abyss tools and books.
        if (par1ItemStack.itemID >= ModItems.swordAbyssRuby.itemID && par1ItemStack.itemID <= ModItems.hoeAbyssTopazPlus.itemID
                || par1ItemStack.itemID == Item.book.itemID) { return par1ItemStack.isItemStackDamageable() ? true : super
                .canApply(par1ItemStack); }

        return false;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchant) {

        return super.canApplyTogether(enchant) && enchant.effectId != Enchantment.unbreaking.effectId;
    }

    @Override
    public String getTranslatedName(int par1) {

        // String s = StatCollector.translateToLocal(this.getName());
        String s = Strings.MENDING_NAME;
        return s + " " + StatCollector.translateToLocal("enchantment.level." + par1);
    }
}
