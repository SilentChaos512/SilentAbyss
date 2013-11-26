package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.configuration.Config;
import silentAbyss.lib.EnumGem;
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

    public Gem(int id) {

        super(id);
        icons = new Icon[12];
        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
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

        return new ItemStack(ModItems.abyssGem, 1, meta);
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
            GameRegistry.addRecipe(getGem(i + EnumGem.RUBY_PLUS.id), "rar", "rgr", "rpr", 'r', Item.redstone, 'a', rAbyssite, 'p',
                    rPurite, 'g', getGem(i));
        }

        for (int i = 0; i < EnumGem.basic().length; ++i) {
            // Shards
            if (Config.SHARDS_PER_GEM.value == 9) {
                GameRegistry.addShapedRecipe(getGem(i), "ggg", "ggg", "ggg", 'g', new ItemStack(ModItems.abyssShard, 1, i));
            } else {
                GameRegistry.addShapedRecipe(getGem(i), "gg", "gg", 'g', new ItemStack(ModItems.abyssShard, 1, i));
            }
        }

        // Conundrumite
        GameRegistry.addRecipe(EnumGem.CONUNDRUMITE.getItem(), " a ", "pdp", " a ", 'a', rAbyssite, 'p',
                rPurite, 'd', Item.diamond);
        // Abyss diamond
        GameRegistry.addRecipe(EnumGem.ABYSS_DIAMOND.getItem(), "rar", "rgr", "rpr", 'r', Item.redstone, 'a', rAbyssite, 'p',
                rPurite, 'g', Item.diamond);
    }
}
