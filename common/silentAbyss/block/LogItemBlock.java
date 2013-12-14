package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.Names;


public class LogItemBlock extends ItemBlockSA {

    public LogItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
        setUnlocalizedName(Names.LOG);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        
        if (stack.getItemDamage() < Log.names.length) {
            return getUnlocalizedName(itemName + Log.names[stack.getItemDamage()]);
        }
        else {
            return super.getUnlocalizedName(stack);
        }
    }
    
    @Override
    public Icon getIconFromDamage(int meta) {
        
        return Block.blocksList[SARegistry.getBlock(Names.LOG).blockID].getBlockTextureFromSide(1);
    }
}
