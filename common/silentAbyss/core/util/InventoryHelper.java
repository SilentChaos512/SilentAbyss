package silentAbyss.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import silentAbyss.item.tool.AbyssAxe;
import silentAbyss.item.tool.AbyssHoe;
import silentAbyss.item.tool.AbyssPickaxe;
import silentAbyss.item.tool.AbyssShovel;
import silentAbyss.item.tool.AbyssSword;

public class InventoryHelper {

    public static boolean isAbyssTool(ItemStack stack) {

        if (stack != null) {
            Item item = stack.getItem();
            return item instanceof AbyssSword || item instanceof AbyssPickaxe || item instanceof AbyssShovel || item instanceof AbyssAxe
                    || item instanceof AbyssHoe;
        }
        return false;
    }
    
    public static boolean placeBlockInWorld(World world, int x, int y, int z, int side, int id, int meta) {
        
        if (Block.blocksList[id] != null) {
            ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[side];
            return world.setBlock(x + d.offsetX, y + d.offsetY, z + d.offsetZ, id, meta, 2);
        }
        else {
            return false;
        }
    }
    
    public static boolean placeTorchOnBlockAt(World world, int x, int y, int z, int side) {
        
        if (canPlaceTorchOn(world, x, y, z)) {
            ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[side];
            int meta = side == 0 ? 0 : 6 - side;
            world.setBlock(x + d.offsetX, y + d.offsetY, z + d.offsetZ, Block.torchWood.blockID, meta, 2);
            
            return true;
        }
        else {
            return false;
        }
    }
    
    private static boolean canPlaceTorchOn(World world, int x, int y, int z) {
        
        if (world.doesBlockHaveSolidTopSurface(x, y, z)) {
            return true;
        }
        else {
            int l = world.getBlockId(x, y, z);
            return (Block.blocksList[l] != null && Block.blocksList[l].canPlaceTorchOnTop(world, x, y, z));
        }
    }
}
