package silentAbyss.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Brick extends Block {
	
	public static Icon[] icons = new Icon[5];

	public Brick(int id) {
		
		super(id, Material.rock);

		this.setHardness(3.0f);
		this.setResistance(10.0f);
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		icons[0] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickRuby");
		icons[1] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickEmerald");
		icons[2] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickSapphire");
		icons[3] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickTopaz");
		icons[4] = iconRegister.registerIcon(Reference.MOD_ID + ":BrickDark");
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
		
		for (int i = 0; i < icons.length; ++i)  {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int par1) {
		
		return par1;
	}
	
	public static void addRecipes() {
		
		for (int i = 0; i < icons.length; ++i) {
			GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.brick, 8, i), "sss", "sgs", "sss",
					's', Block.stoneBrick, 'g', new ItemStack(ModItems.abyssShard, 1, i));
		}
	}
	
	public static void registerLocalizedNames() {
		for (int i = 0; i < Brick.icons.length; ++i) {
			if (i == 4)
				LanguageRegistry.addName(new ItemStack(ModBlocks.brick, 1, i), "Dark Abyss Bricks");
			else
				LanguageRegistry.addName(new ItemStack(ModBlocks.brick, 1, i), AbyssGem.names[i] + "-Speckled Bricks");
		}
	}

}
