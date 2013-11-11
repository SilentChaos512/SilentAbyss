package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.configuration.Config;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Gem extends ItemSA {

    /**
     * The names of the four primary gems.
     */
    public static final String[] names = { "Ruby", "Emerald", "Sapphire", "Topaz" };

    /**
     * All icons by damage value, includes compound gems and duplicates.
     */
    public static Icon[] icons = new Icon[12];

    public Gem(int par1) {

        super(par1);

        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {

        return itemStack.getItemDamage() > 3;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {

        return itemStack.getItemDamage() > 3 ? EnumRarity.rare : EnumRarity.common;
    }

    @Override
    public Icon getIconFromDamage(int i) {

        return icons[i];
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {

        for (int i = 0; i < icons.length; ++i) {
            list.add(getGem(i));
        }
    }

    /**
     * Gets an ItemStack with one of the specified gem.
     * 
     * @param meta
     *            The damage value
     * @return
     */
    public static ItemStack getGem(int meta) {

        return new ItemStack(ModItems.abyssGem, 1, meta);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        int d = itemStack.getItemDamage();

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        if (d == Reference.INDEX_ABYSSITE) {
            s.append("abyssite");
            return s.toString();
        } else if (d == Reference.INDEX_PURITE) {
            s.append("purite");
            return s.toString();
        } else if (d == Reference.INDEX_CONUNDRUMITE) {
            s.append("conundrumite");
            return s.toString();
        }

        s.append("abyss");

        if (d == Reference.INDEX_ABYSS_DIAMOND) {
            // Abyss diamond
            s.append("Diamond");
        } else if (d < 4) {
            // Regular gems.
            s.append(names[d]);
        } else {
            // Energized gems.
            s.append(names[d - Reference.INDEX_RUBY_PLUS]);
            s.append("Plus");
        }

        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        icons[Reference.INDEX_RUBY] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssRuby");
        icons[Reference.INDEX_EMERALD] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssEmerald");
        icons[Reference.INDEX_SAPPHIRE] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssSapphire");
        icons[Reference.INDEX_TOPAZ] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssTopaz");

        icons[Reference.INDEX_ABYSSITE] = iconRegister.registerIcon(Reference.MOD_ID + ":Abyssite");
        icons[Reference.INDEX_PURITE] = iconRegister.registerIcon(Reference.MOD_ID + ":Purite");

        icons[Reference.INDEX_RUBY_PLUS] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssRuby");
        icons[Reference.INDEX_EMERALD_PLUS] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssEmerald");
        icons[Reference.INDEX_SAPPHIRE_PLUS] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssSapphire");
        icons[Reference.INDEX_TOPAZ_PLUS] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssTopaz");

        icons[Reference.INDEX_CONUNDRUMITE] = iconRegister.registerIcon(Reference.MOD_ID + ":Conundrumite");
        icons[Reference.INDEX_ABYSS_DIAMOND] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssDiamond");
    }

    @Override
    public void addRecipes() {

        ItemStack rAbyssite = Gem.getGem(Reference.INDEX_ABYSSITE);
        ItemStack rPurite = Gem.getGem(Reference.INDEX_PURITE);

        for (int i = 0; i < 4; ++i) {
            // Supercharged gems
            GameRegistry.addRecipe(getGem(i + Reference.INDEX_RUBY_PLUS), "rar", "rgr", "rpr", 'r', Item.redstone, 'a', rAbyssite, 'p',
                    rPurite, 'g', getGem(i));
        }

        for (int i = 0; i < Reference.GEM_TYPE_COUNT; ++i) {
            // Shards
            if (Config.SHARDS_PER_GEM.value == 9) {
                GameRegistry.addShapedRecipe(getGem(i), "ggg", "ggg", "ggg", 'g', new ItemStack(ModItems.abyssShard, 1, i));
            } else {
                GameRegistry.addShapedRecipe(getGem(i), "gg", "gg", 'g', new ItemStack(ModItems.abyssShard, 1, i));
            }
        }

        // Conundrumite
        GameRegistry.addRecipe(new ItemStack(ModItems.abyssGem, 1, Reference.INDEX_CONUNDRUMITE), " a ", "pdp", " a ", 'a', rAbyssite, 'p',
                rPurite, 'd', Item.diamond);
        // Abyss diamond
        GameRegistry.addRecipe(getGem(Reference.INDEX_ABYSS_DIAMOND), "rar", "rgr", "rpr", 'r', Item.redstone, 'a', rAbyssite, 'p',
                rPurite, 'g', Item.diamond);
    }
}
