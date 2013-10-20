package silentAbyss.block;

import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOreStorage;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AbyssiteBlock extends BlockOreStorage {

	public AbyssiteBlock(int par1) {
		
		super(par1);
		
		this.setHardness(4.0f);
		this.setResistance(10.0f);
		this.setStepSound(Block.soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		blockIcon = iconRegister.registerIcon("SilentAbyss:BlockAbyssite");
	}
	
	@Override
    public String getUnlocalizedName() {
        
        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.ABYSSITE_BLOCK_NAME);
        return s.toString();
    }

    public static void addRecipes() {

        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockAbyssite),
                ModItems.abyssite, ModItems.abyssite, ModItems.abyssite,
                ModItems.abyssite, ModItems.abyssite, ModItems.abyssite,
                ModItems.abyssite, ModItems.abyssite, ModItems.abyssite);
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssite, 9), ModBlocks.blockAbyssite);
    }
}
