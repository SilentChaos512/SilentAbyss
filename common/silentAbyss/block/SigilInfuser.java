package silentAbyss.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import silentAbyss.Abyss;
import silentAbyss.item.Gem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import silentAbyss.tileentity.TileEntitySigilInfuser;
import cpw.mods.fml.common.registry.GameRegistry;

public class SigilInfuser extends BlockContainer {

    public SigilInfuser(int par1) {

        super(par1, Material.rock);

        setHardness(10.0F);
        setResistance(20.0F);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float f1, float f2, float f3) {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) { return false; }

        player.openGui(Abyss.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {

        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z) {

        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) { return; }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.itemID, item.stackSize,
                        item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileEntitySigilInfuser();
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.SIGIL_INFUSER_NAME);
        return s.toString();
    }

    public static void addRecipes() {

        GameRegistry.addRecipe(new ItemStack(ModBlocks.sigilInfuser), "aaa", "aea", 'a', Gem.getGem(Reference.INDEX_ABYSSITE), 'e',
                Block.enchantmentTable);
    }

}
