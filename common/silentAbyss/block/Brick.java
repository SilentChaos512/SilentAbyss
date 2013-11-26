package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.ModItems;
import silentAbyss.lib.EnumGem;
import cpw.mods.fml.common.registry.GameRegistry;

public class Brick extends BlockSA {

    public Brick(int id) {

        super(id, Material.rock);
        icons = new Icon[EnumGem.basic().length];
        setHardness(3.0f);
        setResistance(10.0f);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName("Brick");
    }

    @Override
    public void addRecipes() {

        for (int i = 0; i < icons.length; ++i) {
            GameRegistry.addShapedRecipe(new ItemStack(this, 8, i), "sss", "sgs", "sss", 's', Block.stoneBrick, 'g', new ItemStack(
                    ModItems.abyssShard, 1, i));
        }
    }
}
