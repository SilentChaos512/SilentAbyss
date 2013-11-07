package silentAbyss;

import net.minecraft.util.ChunkCoordinates;

public class AbyssPortalPosition extends ChunkCoordinates {

    public long field_85087_d;
    final AbyssDimensionTeleporter field_85088_e;

    public AbyssPortalPosition(AbyssDimensionTeleporter teleporter, int x, int y, int z, long par5) {

        super(x, y, z);
        this.field_85088_e = teleporter;
        this.field_85087_d = par5;
    }
}