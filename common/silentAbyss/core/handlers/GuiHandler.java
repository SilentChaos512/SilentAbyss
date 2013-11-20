package silentAbyss.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import silentAbyss.client.gui.GuiSigilInfuser;
import silentAbyss.core.util.LogHelper;
import silentAbyss.inventory.ContainerSigilInfuser;
import silentAbyss.item.TorchBandolier;
import silentAbyss.tileentity.TileEntitySigilInfuser;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    
    public final static int ID_SIGIL_INFUSER = 0;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        if (id == ID_SIGIL_INFUSER) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntitySigilInfuser) {
                return new ContainerSigilInfuser(player.inventory, (TileEntitySigilInfuser) tileEntity);
            }
        }
        else {
            LogHelper.warning("Unknown GUI ID: " + id);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        if (id == ID_SIGIL_INFUSER) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntitySigilInfuser) {
                return new GuiSigilInfuser(player.inventory, (TileEntitySigilInfuser) tileEntity);
            }
        }
        else {
            LogHelper.warning("Unknown GUI ID: " + id);
        }

        return null;
    }

}
