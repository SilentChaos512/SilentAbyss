package silentAbyss.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import silentAbyss.item.tool.AbyssSword;
import silentAbyss.lib.Strings;

public class EnchantmentIceAspect extends Enchantment {

	protected EnchantmentIceAspect(int par1, int par2, EnumEnchantmentType par3EnumEnchantmentType) {
		
		super(par1, par2, par3EnumEnchantmentType);
		this.setName(Strings.ICE_ASPECT_NAME);
	}

	@Override
	public int getMinEnchantability(int par1) {
		
        return 10 + 20 * (par1 - 1);
    }

	@Override
    public int getMaxEnchantability(int par1) {
    	
        return super.getMinEnchantability(par1) + 50;
    }
    
	@Override
    public int getMaxLevel() {
    	
        return 2;
    }
	
	@Override
	public boolean canApply(ItemStack stack) {
		
		if (stack.getItem() instanceof AbyssSword || stack.getItem() instanceof ItemBook) {
			return stack.isItemStackDamageable() ? true : super.canApply(stack);
		}
		
		return false;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment enchant) {
		
		return super.canApplyTogether(enchant) && enchant.effectId != Enchantment.fireAspect.effectId;
	}
	
	@Override
    public String getTranslatedName(int par1) {
        //String s = StatCollector.translateToLocal(this.getName());
    	String s = Strings.ICE_ASPECT_NAME;
        return s + " " + StatCollector.translateToLocal("enchantment.level." + par1);
    }
}
