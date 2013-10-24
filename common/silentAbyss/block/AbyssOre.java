package silentAbyss.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbyssOre extends BlockOre {

	public static Icon[] icons = new Icon[Reference.GEM_TYPE_COUNT];
	
	public AbyssOre(int par1) {
		
		super(par1);
		
		this.setHardness(3.0f);
		this.setResistance(5.0f);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		
		return icons[meta];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		
		for (int i = 0; i < icons.length; ++i) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public int idDropped(int par1, Random random, int par2) {
		
		return ModItems.abyssGem.itemID;
	}
	
	@Override
	public int damageDropped(int par1) {
		
		return par1;
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
}