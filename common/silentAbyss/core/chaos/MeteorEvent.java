package silentAbyss.core.chaos;

import net.minecraft.entity.player.EntityPlayer;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.entity.projectile.EntityMeteor;

public class MeteorEvent extends ChaosEvent {

    private int timer = 10;

    public MeteorEvent(EntityPlayer player, int x, int y, int z) {

        super(player, x, y, z, new Object[0]);
        // shower time + warning time
        timer = Config.METEOR_SHOWER_DURATION.value + Config.METEOR_SHOWER_WARNING_DURATION.value;
    }

    @Override
    public void doTick() {

        if (!player.worldObj.isRemote) {

            --timer;
            if (timer <= 0) {
                active = false;
                return;
            } else if (timer > Config.METEOR_SHOWER_DURATION.value) { return; }

            EntityMeteor meteor;
            for (int i = 0; i < Config.METEOR_SHOWER_COUNT.value; ++i) {
                meteor = new EntityMeteor(player.worldObj, posX
                        + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS.value - Config.METEOR_SHOWER_RADIUS.value), posY
                        + Abyss.rng.nextInt(250) + 50, posZ
                        + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS.value - Config.METEOR_SHOWER_RADIUS.value));
                player.worldObj.spawnEntityInWorld(meteor);
            }
        }
    }

}
