package silentAbyss.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import silentAbyss.core.util.LogHelper;
import silentAbyss.lib.Names;

/**
 * Because why not? Will have whatever function I temporarily need.
 * 
 * @author SilentChaos512
 * 
 */
public class DebugItem extends ItemSA {

    public DebugItem(int id) {

        super(id);
        setUnlocalizedName(Names.DEBUG_ITEM);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        // Make player hungry.
        int k = player.getFoodStats().getFoodLevel();
        player.getFoodStats().setFoodLevel(k > 2 ? k - 2 : k);
        
        return stack;
    }
}
