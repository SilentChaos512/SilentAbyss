package silentAbyss.item;

import silentAbyss.configuration.Config;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class Abyssite extends ItemSA {

	public Abyssite(int par1) {
		super(par1);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName(Strings.ABYSSITE_NAME);
	}

	@Override
	public boolean hasEffect(ItemStack itemStack) {
		return true;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return EnumRarity.epic;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("SilentAbyss:Abyssite");
	}
	
	public static void addRecipes() {
		
		if (Config.SHARDS_PER_GEM == 9) {
			GameRegistry.addShapedRecipe(new ItemStack(ModItems.abyssite), "ggg", "ggg", "ggg", 'g', new ItemStack(ModItems.abyssShard, 1, 4));
		}
		else {
			GameRegistry.addShapedRecipe(new ItemStack(ModItems.abyssite), "gg", "gg", 'g', new ItemStack(ModItems.abyssShard, 1, 4));
		}
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.ABYSSITE_NAME);
        
        return s.toString();
    }
}
