package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;
import silentAbyss.core.util.RecipeHelper;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Strings;

public class ClearGlass extends BlockSA {

    public ClearGlass(int id) {

        super(id, Material.glass);
        icons = new Icon[EnumGem.basic().length];
        setHardness(0.5f);
        setResistance(4.0f);
        setStepSound(Block.soundGlassFootstep);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.CLEAR_GLASS);
    }

    @Override
    public void addRecipes() {

        for (int i = 0; i < icons.length; ++i) {
            RecipeHelper.addSurround(new ItemStack(this, 8, i), EnumGem.basic()[i].getShard(), Block.glass);
        }
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {

        int i = blockAccess.getBlockId(x, y, z);
        return i == this.blockID ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    @Override
    public void addOreDict() {

        for (int i = 0; i < EnumGem.basic().length; ++i) {
            OreDictionary.registerOre("glass", new ItemStack(this, 1, i));
        }
    }
}
