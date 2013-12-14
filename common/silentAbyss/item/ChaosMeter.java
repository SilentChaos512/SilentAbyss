package silentAbyss.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ChaosMeter extends ItemSA {

    public ChaosMeter(int id) {

        super(id);
        setUnlocalizedName(Names.CHAOS_METER);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        String s = String.format("%d (%.3f)", ChaosHandler.getChaos(), ChaosHandler.getChaosFactor());
        PlayerHelper.addChatMessage(player, s, false);
        return stack;
    }

    @Override
    public void addRecipes() {

        GameRegistry.addShapedRecipe(new ItemStack(this), " i ", "rar", " i ", 'i', Item.ingotIron, 'r', Item.redstone, 'a',
                EnumGem.ABYSSITE.getItem());
    }
}
