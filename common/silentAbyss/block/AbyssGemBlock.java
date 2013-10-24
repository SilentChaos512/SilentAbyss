package silentAbyss.block;

import java.sql.Ref;
import java.util.List;

import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
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
import net.minecraftforge.event.terraingen.BiomeEvent.GetGrassColor;

public class AbyssGemBlock extends BlockOreStorage {

	public static Icon[] icons = new Icon[Reference.GEM_TYPE_COUNT];
	
	public AbyssGemBlock(int par1) {
		
		super(par1);
		
		this.setHardness(3.0f);
		this.setResistance(10.0f);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	/**
	 * Gets an ItemStack with one of the specified gem.
	 * @param meta The damage value
	 * @return
	 */
	public static ItemStack getGem(int meta) {
		
		return new ItemStack(ModBlocks.blockAbyssGem, 1, meta);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		icons[Reference.INDEX_RUBY] = iconRegister.registerIcon(Reference.MOD_ID + ":BlockAbyssRuby");
		icons[Reference.INDEX_EMERALD] = iconRegister.registerIcon(Reference.MOD_ID + ":BlockAbyssEmerald");
		icons[Reference.INDEX_SAPPHIRE] = iconRegister.registerIcon(Reference.MOD_ID + ":BlockAbyssSapphire");
		icons[Reference.INDEX_TOPAZ] = iconRegister.registerIcon(Reference.MOD_ID + ":BlockAbyssTopaz");
		icons[Reference.INDEX_ABYSSITE] = iconRegister.registerIcon(Reference.MOD_ID + ":BlockAbyssite");
		icons[Reference.INDEX_PURITE] = iconRegister.registerIcon(Reference.MOD_ID + ":BlockPurite");
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
	public int damageDropped(int par1) {
		
		return par1;
	}
	
	public static void addRecipes() {
	    
	    ItemStack gem, block;
	    
	    for (int i = 0; i < icons.length; ++i) {
	        gem = new ItemStack(ModItems.abyssGem, 1, i);
	        block = new ItemStack(ModBlocks.blockAbyssGem, 1, i);
	        GameRegistry.addShapelessRecipe(block,
	                gem, gem, gem,
	                gem, gem, gem,
	                gem, gem, gem);
	        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssGem, 9, i), block);
	    }
	}
}
