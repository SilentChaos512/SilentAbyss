package silentAbyss.item;

import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OrnateStick extends ItemSA {

	public OrnateStick(int par1) {
		super(par1);
		this.maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName(Strings.ORNATE_STICK_NAME);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("SilentAbyss:OrnateStick");
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.ORNATE_STICK_NAME);
        
        return s.toString();
    }
	
	public static void addRecipes() {
	    
	    GameRegistry.addRecipe(new ItemStack(ModItems.ornateStick, 4), "gag", "gag", "gag",
	            'g', Item.ingotGold, 'a', AbyssGem.getGem(Reference.INDEX_ABYSSITE));
	}
}
