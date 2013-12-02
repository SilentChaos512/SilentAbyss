package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.configuration.Config;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.core.util.LogHelper;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GemShard extends ItemSA {

    public GemShard(int id) {

        super(id);
        icons = new Icon[EnumGem.basic().length];
        setMaxStackSize(64);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.SHARD);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Gets an ItemStack with one of the specified gem.
     * 
     * @param meta
     *            The damage value
     * @return
     */
    public static ItemStack getGem(int meta) {

        return new ItemStack(SARegistry.getItem(Names.SHARD), 1, meta);
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {

        return stack.getItemDamage() > 3;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return stack.getItemDamage() > 3 ? EnumRarity.rare : EnumRarity.common;
    }

    @Override
    public void addRecipes() {

        for (int i = 0; i < icons.length; ++i) {
            if (Config.SHARDS_PER_GEM.value == 9) {
                GameRegistry.addShapelessRecipe(new ItemStack(SARegistry.getItem(Names.SHARD), 9, i), Gem.getGem(i));
            }
            else {
                GameRegistry.addShapelessRecipe(new ItemStack(SARegistry.getItem(Names.SHARD), 4, i), Gem.getGem(i));
            }
        }
    }

}
