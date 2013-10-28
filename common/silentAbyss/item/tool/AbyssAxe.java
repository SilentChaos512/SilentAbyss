package silentAbyss.item.tool;

import silentAbyss.Abyss;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class AbyssAxe extends ItemAxe {
    
    private int gemType = 0;
	
	public AbyssAxe(int par1, EnumToolMaterial par2EnumToolMaterial, int gemType) {
		super(par1 - Reference.SHIFTED_ID_RANGE_CORRECTION, par2EnumToolMaterial);
		this.gemType = gemType;
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("tool.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("axeAbyss");
        s.append(AbyssGem.names[this.gemType]);
        if (this.toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s.append("Plus");
        }
        
        return s.toString();
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		String s = "SilentAbyss:AxeAbyss";
		
		switch (this.gemType){
    		case 0: s += "Ruby"; break;
    		case 1: s += "Emerald"; break;
    		case 2: s += "Sapphire"; break;
    		case 3: s += "Topaz"; break;
		}
		
		if (this.toolMaterial == Abyss.materialEnergizedAbyssGem) {
			s += "Plus";
		}
		
		itemIcon = iconRegister.registerIcon(s);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
	    
	    boolean isSupercharged = this.toolMaterial == Abyss.materialEnergizedAbyssGem;
	    ItemStack material = new ItemStack(ModItems.abyssGem, 1, this.gemType + (isSupercharged ? 4 : 0));
	    if (material.itemID == stack2.itemID && material.getItemDamage() == stack2.getItemDamage())
	        return true;
	    else
	        return super.getIsRepairable(stack1, stack2);
	}
	
	public static void addRecipe(ItemStack tool, ItemStack material, boolean energized) {
		if (energized) {
			GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] {"gg ", "gs ", " s ",
					'g', material, 's', new ItemStack(ModItems.craftingMaterial, 1, 0)}));
		}
		else {
			GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] {"gg ", "gs ", " s ",
					'g', material, 's', "stickWood"}));
		}
	}
}
