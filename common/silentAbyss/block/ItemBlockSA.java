package silentAbyss.block;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Strings;

public class ItemBlockSA extends ItemBlock {

    protected boolean basicGemSubtypes = false;
    protected String itemName = "";
    
    public ItemBlockSA(int id) {

        super(id);
        setHasSubtypes(true);
        setUnlocalizedName(Integer.toString(id));
    }

    @Override
    public int getMetadata(int meta) {

        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        int d = stack.getItemDamage();
        
        if (basicGemSubtypes && d < EnumGem.basic().length) {
            return getUnlocalizedName(itemName + EnumGem.basic()[d].name);
        }
        else if (hasSubtypes) {
            return getUnlocalizedName(itemName + "-" + Integer.toString(d));
        }
        else {
            return getUnlocalizedName(itemName);
        }
    }

    public String getUnlocalizedName(String tileName) {

        return (new StringBuilder()).append("tile.").append(Strings.RESOURCE_PREFIX).append(tileName).toString();
    }
    
    @Override
    public Item setUnlocalizedName(String itemName) {
        
        this.itemName = itemName;
        return super.setUnlocalizedName(itemName);
    }
    
    public void setHasBasicGemSubtypes(boolean value) {
        
        basicGemSubtypes = value;
    }
}
