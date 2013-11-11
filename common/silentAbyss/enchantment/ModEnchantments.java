package silentAbyss.enchantment;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import silentAbyss.item.EnchantToken;
import silentAbyss.lib.EnchantmentIds;
import silentAbyss.lib.Strings;

public class ModEnchantments {

    public static Enchantment mending;
    public static Enchantment nihil;
    public static Enchantment iceAspect;

    public static void init() {

        /*
         * Initialize variables
         */
        mending = new EnchantmentMending(EnchantmentIds.MENDING, 1, EnumEnchantmentType.all);
        nihil = new EnchantmentNihil(EnchantmentIds.NIHIL, 1, EnumEnchantmentType.weapon);
        iceAspect = new EnchantmentIceAspect(EnchantmentIds.ICE_ASPECT, 1, EnumEnchantmentType.weapon);

        /*
         * Add enchanted books
         */
        Enchantment.addToBookList(mending);
        Enchantment.addToBookList(nihil);
        Enchantment.addToBookList(iceAspect);
        
        /*
         * Localizations - definitely not the right way
         */
        LanguageRegistry.instance().addStringLocalization("enchantment.Ice Aspect", Strings.ICE_ASPECT_NAME);
        LanguageRegistry.instance().addStringLocalization("enchantment.Mending", Strings.MENDING_NAME);
        LanguageRegistry.instance().addStringLocalization("enchantment.Nihil", Strings.NIHIL_NAME);
        
        /*
         * Set up enchantment tokens.
         */
        EnchantToken.init();
    }
}
