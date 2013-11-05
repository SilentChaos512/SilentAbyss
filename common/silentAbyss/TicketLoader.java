package silentAbyss;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager.OrderedLoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class TicketLoader implements OrderedLoadingCallback {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount) {

        ArrayList list = new ArrayList<Ticket>();
        return list;
    }

    @Override
    public void ticketsLoaded(List<Ticket> tickets, World world) {

        // TODO Auto-generated method stub

    }
}
