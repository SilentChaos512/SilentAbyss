package silentAbyss.block;

import java.util.List;

import silentAbyss.item.AbyssGem;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOreStorage;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class AbyssGemBlock extends BlockOreStorage {

	public static Icon[] icons = new Icon[4];
	
	public AbyssGemBlock(int par1) {
		
		super(par1);
		
		this.setHardness(3.0f);
		this.setResistance(10.0f);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		icons[0] = iconRegister.registerIcon("SilentAbyss:BlockAbyssRuby");
		icons[1] = iconRegister.registerIcon("SilentAbyss:BlockAbyssEmerald");
		icons[2] = iconRegister.registerIcon("SilentAbyss:BlockAbyssSapphire");
		icons[3] = iconRegister.registerIcon("SilentAbyss:BlockAbyssTopaz");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		
		return icons[MathHelper.clamp_int(meta, 0, icons.length - 1)];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		
		for (int i = 0; i < AbyssGem.names.length; ++i) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int par1) {
		
		return par1;
	}
	
	public static void registerLocalizedNames() {
		for (int i = 0; i < AbyssGem.names.length; ++i) {
			LanguageRegistry.addName(new ItemStack(ModBlocks.blockAbyssGem, 1, i), "Abyss " + AbyssGem.names[i] + " Block");
		}
	}
}
