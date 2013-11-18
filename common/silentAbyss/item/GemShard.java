package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.configuration.Config;
import silentAbyss.core.util.LogHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GemShard extends ItemSA {

    public GemShard(int id) {

        super(id);
        icons = new Icon[Reference.GEM_TYPE_COUNT];
        setMaxStackSize(64);
        setHasSubtypes(true);
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

        return new ItemStack(ModItems.abyssShard, 1, meta);
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
    public String getUnlocalizedName(ItemStack stack) {

        int d = stack.getItemDamage();

        StringBuilder sb = new StringBuilder();
        sb.append("item.");
        sb.append(Strings.RESOURCE_PREFIX);

        if (d == Reference.INDEX_ABYSSITE) {
            sb.append("abyssiteShard");
            return sb.toString();
        } else if (d == Reference.INDEX_PURITE) {
            sb.append("puriteShard");
            return sb.toString();
        }

        sb.append("abyss");

        if (d < 4) {
            sb.append(Gem.names[d]);
        } else {
            LogHelper.severe("AbyssShard: tried to get unlocalized name for unknown shard type!");
        }

        sb.append("Shard");

        return sb.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        icons[Reference.INDEX_RUBY] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssRubyShard");
        icons[Reference.INDEX_EMERALD] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssEmeraldShard");
        icons[Reference.INDEX_SAPPHIRE] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssSapphireShard");
        icons[Reference.INDEX_TOPAZ] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssTopazShard");
        icons[Reference.INDEX_ABYSSITE] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssiteShard");
        icons[Reference.INDEX_PURITE] = iconRegister.registerIcon(Reference.MOD_ID + ":PuriteShard");
    }

    @Override
    public void addRecipes() {

        for (int i = 0; i < icons.length; ++i) {
            if (Config.SHARDS_PER_GEM.value == 9) {
                GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssShard, 9, i), Gem.getGem(i));
            } else {
                GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssShard, 4, i), Gem.getGem(i));
            }
        }
    }

}
