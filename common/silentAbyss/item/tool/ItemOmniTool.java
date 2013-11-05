package silentAbyss.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import silentAbyss.lib.Reference;

/**
 * This item is abandoned for now.
 * 
 */
public class ItemOmniTool extends net.minecraft.item.ItemTool {

    protected ItemOmniTool(int par1, int par2, EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) {

        super(par1 - Reference.SHIFTED_ID_RANGE_CORRECTION, par2, par3EnumToolMaterial, par4ArrayOfBlock);
    }

    @Override
    public boolean canHarvestBlock(Block par1Block) {

        return true;
    }

    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {

        int i;
        // Pickaxe blocks.
        for (i = 0; i < ItemPickaxe.blocksEffectiveAgainst.length; ++i) {
            if (ItemPickaxe.blocksEffectiveAgainst[i] == par2Block) { return efficiencyOnProperMaterial; }
        }
        // Shovel blocks.
        for (i = 0; i < ItemSpade.blocksEffectiveAgainst.length; ++i) {
            if (ItemSpade.blocksEffectiveAgainst[i] == par2Block) { return efficiencyOnProperMaterial; }
        }
        // Sword blocks.
        if (par2Block.blockID == Block.web.blockID) { return 15.0F; }

        return 1.0F;
    }
}
