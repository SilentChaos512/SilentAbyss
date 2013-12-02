package silentAbyss.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.oredict.OreDictionary;
import silentAbyss.configuration.Config;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Gem extends ItemSA {

    /**
     * The names of the four primary gems.
     */
    public static final String[] names = { "Ruby", "Emerald", "Sapphire", "Topaz" };

    public Gem(int id) {

        super(id);
        icons = new Icon[12];
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
        setUnlocalizedName(Names.GEM_ITEM);
    }

    @Override
    public boolean hasEffect(ItemStack itemStack, int pass) {

        return itemStack.getItemDamage() > 3;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {

        return itemStack.getItemDamage() > 3 ? EnumRarity.rare : EnumRarity.common;
    }

    /**
     * Gets an ItemStack with one of the specified gem.
     * 
     * @param meta
     *            The damage value
     * @return
     */
    public static ItemStack getGem(int meta) {

        return new ItemStack(SARegistry.getItem(Names.GEM_ITEM), 1, meta);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        int d = stack.getItemDamage();

        if (d < EnumGem.all().length) {
            return getUnlocalizedName("Gem" + EnumGem.all()[d].name);
        }
        else {
            return super.getUnlocalizedName(stack);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        String s;
        for (int i = 0; i < EnumGem.all().length; ++i) {
            s = EnumGem.all()[i].name.replace("Plus", "");
            icons[i] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "Gem" + s);
        }
    }

    @Override
    public void addRecipes() {

        ItemStack rAbyssite = EnumGem.ABYSSITE.getItem();
        ItemStack rPurite = EnumGem.PURITE.getItem();

        for (int i = 0; i < 4; ++i) {
            // Supercharged gems
            GameRegistry.addRecipe(getGem(i + EnumGem.RUBY_PLUS.id), "rar", "rgr", "rpr", 'r', Item.redstone, 'a', rAbyssite, 'p', rPurite,
                    'g', getGem(i));
        }

        for (int i = 0; i < EnumGem.basic().length; ++i) {
            // Shards
            if (Config.SHARDS_PER_GEM.value == 9) {
                GameRegistry.addShapedRecipe(getGem(i), "ggg", "ggg", "ggg", 'g', EnumGem.values()[i].getShard());
            }
            else {
                GameRegistry.addShapedRecipe(getGem(i), "gg", "gg", 'g', EnumGem.values()[i].getShard());
            }
        }

        // Conundrumite
        GameRegistry.addRecipe(EnumGem.CONUNDRUMITE.getItem(), "qaq", "pdp", "qaq", 'a', rAbyssite, 'p', rPurite, 'd', Item.diamond, 'q',
                Item.netherQuartz);
        // Abyss diamond
        GameRegistry.addRecipe(EnumGem.ABYSS_DIAMOND.getItem(), "rar", "rgr", "rpr", 'r', Item.redstone, 'a', rAbyssite, 'p', rPurite, 'g',
                Item.diamond);
    }

    @Override
    public void addOreDict() {

        OreDictionary.registerOre("gemRuby", EnumGem.RUBY.getItem());
        OreDictionary.registerOre("gemEmerald", EnumGem.EMERALD.getItem());
        OreDictionary.registerOre("gemSapphire", EnumGem.SAPPHIRE.getItem());
        OreDictionary.registerOre("gemTopaz", EnumGem.TOPAZ.getItem());
        OreDictionary.registerOre("gemAbyssite", EnumGem.ABYSSITE.getItem());
        OreDictionary.registerOre("gemPurite", EnumGem.PURITE.getItem());

        for (int i = 0; i < 4; ++i) {
            OreDictionary.registerOre(Strings.ORE_DICT_GEM_BASIC, new ItemStack(this, 1, i));
        }
    }
}
