package silentAbyss.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Brick extends BlockSA {

    public Brick(int id) {

        super(id, Material.rock);
        icons = new Icon[Reference.GEM_TYPE_COUNT];
        setHardness(3.0f);
        setResistance(10.0f);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        icons[Reference.INDEX_RUBY] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickRuby");
        icons[Reference.INDEX_EMERALD] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickEmerald");
        icons[Reference.INDEX_SAPPHIRE] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickSapphire");
        icons[Reference.INDEX_TOPAZ] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickTopaz");
        icons[Reference.INDEX_ABYSSITE] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickDark");
        icons[Reference.INDEX_PURITE] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickLight");
    }

    @Override
    public String getUnlocalizedName() {

        return getUnlocalizedName("brick");
    }

    @Override
    public void addRecipes() {

        for (int i = 0; i < icons.length; ++i) {
            GameRegistry.addShapedRecipe(new ItemStack(this, 8, i), "sss", "sgs", "sss", 's', Block.stoneBrick, 'g', new ItemStack(
                    ModItems.abyssShard, 1, i));
        }
    }
}
