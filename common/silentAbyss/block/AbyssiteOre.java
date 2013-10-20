package silentAbyss.block;

import java.util.Random;

import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AbyssiteOre extends BlockOre {

	public AbyssiteOre(int par1) {
		
		super(par1);
		
		this.setHardness(4.0f);
		this.setResistance(5.0f);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		blockIcon = iconRegister.registerIcon("SilentAbyss:OreAbyssite");
	}
	
	@Override
	public int idDropped(int par1, Random random, int par2) {
		
		return ModItems.abyssite.itemID;
	}
	
	@Override
	public int quantityDropped(Random random) {
		
		return 1;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.idDropped(par5, par1World.rand, par7) != this.blockID) {
            int j1 = 1 + par1World.rand.nextInt(5);
            this.dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }
	
	@Override
    public String getUnlocalizedName() {
        
        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.ABYSSITE_ORE_NAME);
        return s.toString();
    }
}
