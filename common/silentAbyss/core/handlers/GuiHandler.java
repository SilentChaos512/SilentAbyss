package silentAbyss.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

//        if (id == ID_SIGIL_INFUSER) {
//            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
//            if (tileEntity instanceof TileEntitySigilInfuser) {
//                return new ContainerSigilInfuser(player.inventory, (TileEntitySigilInfuser) tileEntity);
//            }
//        }
//        else {
//            LogHelper.warning("Unknown GUI ID: " + id);
//        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

//        if (id == ID_SIGIL_INFUSER) {
//            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
//            if (tileEntity instanceof TileEntitySigilInfuser) {
//                return new GuiSigilInfuser(player.inventory, (TileEntitySigilInfuser) tileEntity);
//            }
//        }
//        else {
//            LogHelper.warning("Unknown GUI ID: " + id);
//        }

        return null;
    }

}
