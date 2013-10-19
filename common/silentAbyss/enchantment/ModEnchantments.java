package silentAbyss.enchantment;

import silentAbyss.lib.EnchantmentIds;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class ModEnchantments {

	public static Enchantment mending;
	public static Enchantment nihil;
	
	public static void init() {
		
		/*
		 * Initialize variables
		 */
		mending = new EnchantmentMending(EnchantmentIds.MENDING, 1, EnumEnchantmentType.all);
		nihil = new EnchantmentNihil(EnchantmentIds.NIHIL, 1, EnumEnchantmentType.weapon);
		
		/*
		 * Add enchanted books
		 */
		Enchantment.addToBookList(mending);
		Enchantment.addToBookList(nihil);
	}
}
