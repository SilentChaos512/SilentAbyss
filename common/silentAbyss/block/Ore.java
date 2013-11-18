package silentAbyss.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Ore extends BlockSA {

    public Ore(int id) {

        super(id, Material.rock);
        icons = new Icon[Reference.GEM_TYPE_COUNT];
        setHardness(3.0f);
        setResistance(5.0f);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        icons[Reference.INDEX_RUBY] = iconRegister.registerIcon(Reference.MOD_ID + ":OreAbyssRuby");
        icons[Reference.INDEX_EMERALD] = iconRegister.registerIcon(Reference.MOD_ID + ":OreAbyssEmerald");
        icons[Reference.INDEX_SAPPHIRE] = iconRegister.registerIcon(Reference.MOD_ID + ":OreAbyssSapphire");
        icons[Reference.INDEX_TOPAZ] = iconRegister.registerIcon(Reference.MOD_ID + ":OreAbyssTopaz");
        icons[Reference.INDEX_ABYSSITE] = iconRegister.registerIcon(Reference.MOD_ID + ":OreAbyssite");
        icons[Reference.INDEX_PURITE] = iconRegister.registerIcon(Reference.MOD_ID + ":OrePurite");
    }

    @Override
    public int idDropped(int par1, Random random, int par2) {

        return ModItems.abyssGem.itemID;
    }

    @Override
    public int damageDropped(int meta) {

        return meta;
    }

    @Override
    public int quantityDropped(Random random) {

        return 1;
    }

    public int quantityDroppedWithBonus(int par1, Random random) {
        
        if (par1 > 0) {
            int j = random.nextInt(par1 + 2) - 1;
            
            if (j < 0) {
                j = 0;
            }
            
            return quantityDropped(random) * (j + 1);
        }
        else {
            return quantityDropped(random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {

        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (idDropped(par5, par1World.rand, par7) != blockID) {
            int j1 = 1 + par1World.rand.nextInt(5);
            dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }

    @Override
    public String getUnlocalizedName() {

        return getUnlocalizedName("ore");
    }
}