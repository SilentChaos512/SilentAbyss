package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class GemBlock extends BlockSA {

    public GemBlock(int id) {

        super(id, Material.iron);
        icons = new Icon[EnumGem.basic().length];
        setHardness(3.0f);
        setResistance(10.0f);
        setStepSound(Block.soundMetalFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.GEM_BLOCK);
    }

    @Override
    public void addRecipes() {

        ItemStack gem, block;

        for (int i = 0; i < icons.length; ++i) {
            gem = EnumGem.values()[i].getItem();
            block = new ItemStack(this, 1, i);
            GameRegistry.addShapelessRecipe(block, gem, gem, gem, gem, gem, gem, gem, gem, gem);
            GameRegistry.addShapelessRecipe(new ItemStack(SARegistry.getItem(Names.GEM_ITEM), 9, i), block);
        }
    }
}
