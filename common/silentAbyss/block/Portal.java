package silentAbyss.block;

import java.util.Random;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import silentAbyss.Abyss;
import silentAbyss.AbyssDimensionTeleporter;
import silentAbyss.configuration.Config;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.Names;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Shoutout to this tutorial:
 * http://www.minecraftforum.net/topic/1797143-152-16-
 * forge-dimension-tutorial-multi
 * -biome-dimension-ore-gen-basic-house-gen-tree-gen-skyrenderer-githubsrc-code/
 * which I mostly just copied... Will work on customizing abyss portals more in
 * the future TODO
 * 
 * @author SilentChaos512
 * 
 */
public class Portal extends BlockBreakable {

    public Portal(int par1) {

        super(par1, Reference.MOD_ID + ":Portal", Material.portal, false);
        this.setTickRandomly(true);
        this.setHardness(-1.0F);
        this.setStepSound(soundGlassFootstep);
        this.setLightValue(0.75F);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    // /**
    // * Ticks the block if it's been scheduled
    // */
    // public void updateTick(World par1World, int par2, int par3, int par4,
    // Random par5Random) {
    //
    // super.updateTick(par1World, par2, par3, par4, par5Random);
    // if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) <
    // par1World.difficultySetting) {
    // int l;
    // for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par2, l, par4) &&
    // l > 0; --l) {
    // ;
    // }
    // if (l > 0 && !par1World.isBlockNormalCube(par2, l + 1, par4)) {
    // Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)
    // par2 + 0.5D, (double) l + 1.1D,
    // (double) par4 + 0.5D);
    // if (entity != null) {
    // entity.timeUntilPortal = entity.getPortalCooldown();
    // }
    // }
    // }
    // }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this
     * box can change after the pool has been cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        return null;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y,
     * z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {

        float f;
        float f1;
        if (blockAccess.getBlockId(x - 1, y, z) != this.blockID && blockAccess.getBlockId(x + 1, y, z) != this.blockID) {
            f = 0.125F;
            f1 = 0.5F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        }
        else {
            f = 0.5F;
            f1 = 0.125F;
            this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
        }
    }

    /**
     * Is this block (a) opaque and (B) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {

        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False
     * (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    /**
     * Checks to see if this location is valid to create a portal and will
     * return True if it does. Args: world, x, y, z
     */
    public boolean tryToCreatePortal(World world, int x, int y, int z) {

        byte b0 = 0;
        byte b1 = 0;

        int portalFrameId = SARegistry.getBlock(Names.PORTAL_FRAME).blockID;

        if (world.getBlockId(x - 1, y, z) == portalFrameId || world.getBlockId(x + 1, y, z) == portalFrameId) {
            b0 = 1;
        }
        if (world.getBlockId(x, y, z - 1) == portalFrameId || world.getBlockId(x, y, z + 1) == portalFrameId) {
            b1 = 1;
        }
        if (b0 == b1) {
            return false;
        }
        else {
            if (world.getBlockId(x - b0, y, z - b1) == 0) {
                x -= b0;
                z -= b1;
            }
            int l;
            int i1;
            for (l = -1; l <= 2; ++l) {
                for (i1 = -1; i1 <= 3; ++i1) {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;
                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3) {
                        int j1 = world.getBlockId(x + b0 * l, y + i1, z + b1 * l);
                        if (flag) {
                            if (j1 != portalFrameId) {
                                return false;
                            }
                        }
                        // else if (j1 != 0 && j1 != Main.TutorialFire.blockID)
                        // {
                        // return false;
                        // }
                    }
                }
            }
            for (l = 0; l < 2; ++l) {
                for (i1 = 0; i1 < 3; ++i1) {
                    world.setBlock(x + b0 * l, y + i1, z + b1 * l, SARegistry.getBlock(Names.PORTAL).blockID, 0, 2);
                }
            }
            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {

        byte b0 = 0;
        byte b1 = 1;

        int portalFrameId = SARegistry.getBlock(Names.PORTAL_FRAME).blockID;

        if (world.getBlockId(x - 1, y, z) == this.blockID || world.getBlockId(x + 1, y, z) == this.blockID) {
            b0 = 1;
            b1 = 0;
        }
        int i1;
        for (i1 = y; world.getBlockId(x, i1 - 1, z) == this.blockID; --i1) {
            ;
        }
        if (world.getBlockId(x, i1 - 1, z) != portalFrameId) {
            world.setBlockToAir(x, y, z);
        }
        else {
            int j1;
            for (j1 = 1; j1 < 4 && world.getBlockId(x, i1 + j1, z) == this.blockID; ++j1) {
                ;
            }
            if (j1 == 3 && world.getBlockId(x, i1 + j1, z) == portalFrameId) {
                boolean flag = world.getBlockId(x - 1, y, z) == this.blockID || world.getBlockId(x + 1, y, z) == this.blockID;
                boolean flag1 = world.getBlockId(x, y, z - 1) == this.blockID || world.getBlockId(x, y, z + 1) == this.blockID;
                if (flag && flag1) {
                    world.setBlockToAir(x, y, z);
                }
                else {
                    if ((world.getBlockId(x + b0, y, z + b1) != portalFrameId || world.getBlockId(x - b0, y, z - b1) != this.blockID)
                            && (world.getBlockId(x - b0, y, z - b1) != portalFrameId || world.getBlockId(x + b0, y, z + b1) != this.blockID)) {
                        world.setBlockToAir(x, y, z);
                    }
                }
            }
            else {
                world.setBlockToAir(x, y, z);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates. Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {

        if (blockAccess.getBlockId(x, y, z) == this.blockID) {
            return false;
        }
        else {
            boolean flag = blockAccess.getBlockId(x - 1, y, z) == this.blockID && blockAccess.getBlockId(x - 2, y, z) != this.blockID;
            boolean flag1 = blockAccess.getBlockId(x + 1, y, z) == this.blockID && blockAccess.getBlockId(x + 2, y, z) != this.blockID;
            boolean flag2 = blockAccess.getBlockId(x, y, z - 1) == this.blockID && blockAccess.getBlockId(x, y, z - 2) != this.blockID;
            boolean flag3 = blockAccess.getBlockId(x, y, z + 1) == this.blockID && blockAccess.getBlockId(x, y, z + 2) != this.blockID;
            boolean flag4 = flag || flag1;
            boolean flag5 = flag2 || flag3;
            return flag4 && side == 4 ? true : (flag4 && side == 5 ? true : (flag5 && side == 2 ? true : flag5 && side == 3));
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random) {

        return 0;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {

        if ((par5Entity.ridingEntity == null) && (par5Entity.riddenByEntity == null) && ((par5Entity instanceof EntityPlayerMP))) {
            EntityPlayerMP thePlayer = (EntityPlayerMP) par5Entity;
            if (thePlayer.timeUntilPortal > 0) {
                thePlayer.timeUntilPortal = 10;
            }
            else if (thePlayer.dimension != Config.DIMENSION.value) {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, Config.DIMENSION.value,
                        new AbyssDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(Config.DIMENSION.value)));
            }
            else {
                thePlayer.timeUntilPortal = 10;
                thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, 0,
                        new AbyssDimensionTeleporter(thePlayer.mcServer.worldServerForDimension(0)));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass() {

        return 1;
    }

    // @SideOnly(Side.CLIENT)
    // /**
    // * A randomly called display update to be able to add particles or other
    // items for display
    // */
    // public void randomDisplayTick(World par1World, int par2, int par3, int
    // par4, Random par5Random) {
    //
    // if (par5Random.nextInt(100) == 0) {
    // par1World.playSound((double) par2 + 0.5D, (double) par3 + 0.5D, (double)
    // par4 + 0.5D, "portal.portal", 0.5F,
    // par5Random.nextFloat() * 0.4F + 0.8F, false);
    // }
    // for (int l = 0; l < 4; ++l) {
    // double d0 = (double) ((float) par2 + par5Random.nextFloat());
    // double d1 = (double) ((float) par3 + par5Random.nextFloat());
    // double d2 = (double) ((float) par4 + par5Random.nextFloat());
    // double d3 = 0.0D;
    // double d4 = 0.0D;
    // double d5 = 0.0D;
    // int i1 = par5Random.nextInt(2) * 2 - 1;
    // d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
    // d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
    // d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.5D;
    // if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID &&
    // par1World.getBlockId(par2 + 1, par3, par4) != this.blockID) {
    // d0 = (double) par2 + 0.5D + 0.25D * (double) i1;
    // d3 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
    // }
    // else {
    // d2 = (double) par4 + 0.5D + 0.25D * (double) i1;
    // d5 = (double) (par5Random.nextFloat() * 2.0F * (float) i1);
    // }
    // par1World.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
    // }
    // }

    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4) {

        return 0;
    }
}
