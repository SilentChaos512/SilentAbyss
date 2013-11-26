package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.ModItems;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Strings;
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
        setUnlocalizedName(Strings.GEM_BLOCK_NAME);
    }

    /**
     * Gets an ItemStack with one of the specified gem.
     * 
     * @param meta
     *            The damage value
     * @return
     */
    public static ItemStack getGem(int meta) {

        return new ItemStack(ModBlocks.gem, 1, meta);
    }
    
    @Override
    public void addRecipes() {

        ItemStack gem, block;

        for (int i = 0; i < icons.length; ++i) {
            gem = new ItemStack(ModItems.abyssGem, 1, i);
            block = new ItemStack(ModBlocks.gem, 1, i);
            GameRegistry.addShapelessRecipe(block, gem, gem, gem, gem, gem, gem, gem, gem, gem);
            GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssGem, 9, i), block);
        }
    }
}
