package silentAbyss.block;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import silentAbyss.lib.Reference;

public class Portal extends BlockBreakable {

    protected Portal(int id) {

        super(id, Reference.MOD_ID + ":Portal", Material.portal, false);
        // this.setTickRandomly(true);
        this.setHardness(-1.0f);
        this.setStepSound(soundGlassFootstep);
        this.setLightValue(0.75f);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {

        float fx, fz;
        if (blockAccess.getBlockId(x - 1, y, z) != this.blockID && blockAccess.getBlockId(x + 1, y, z) != this.blockID) {
            fx = 0.125f;
            fz = 0.5f;
            this.setBlockBounds(0.5f - fx, 0.0f, 0.5f - fz, 0.5f + fx, 1.0f, 0.5f + fz);
        }
        else {
            fx = 0.5f;
            fz = 0.125f;
            this.setBlockBounds(0.5f - fx, 0.0f, 0.5f - fz, 0.5f + fx, 1.0f, 0.5f + fz);
        }
    }
}
