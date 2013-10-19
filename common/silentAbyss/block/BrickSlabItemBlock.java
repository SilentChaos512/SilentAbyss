package silentAbyss.block;

import silentAbyss.item.AbyssGem;
import silentAbyss.lib.Strings;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BrickSlabItemBlock extends ItemBlock {

	public BrickSlabItemBlock(int par1) {
		super(par1);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damageValue) {
		
		return damageValue;
	}
	
	@Override
	public Icon getIconFromDamage(int damageValue) {
		
		return Brick.icons[damageValue];
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {
        
        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("blockAbyssBrickSlab");
        
        if (stack.getItemDamage() == 4) {
            s.append("Dark");
        }
        else {
            s.append(AbyssGem.names[stack.getItemDamage()]);
        }
        
        return s.toString();
    }

}
