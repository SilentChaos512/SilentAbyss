package silentAbyss.core.chaos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.entity.projectile.EntityMeteor;

public class MeteorEvent extends ChaosEvent {
	
	private int timer = 10;

	public MeteorEvent(EntityPlayer player, int x, int y, int z) {
		super(player, x, y, z, new Object[0]);
		// shower time + warning time
		timer = Config.METEOR_SHOWER_DURATION + Config.METEOR_SHOWER_WARNING_DURATION;
	}

	@Override
	public void doTick() {

		if (!player.worldObj.isRemote) {
			
			--timer;
			if (timer <= 0) {
				active = false;
				return;
			}
			else if (timer > Config.METEOR_SHOWER_DURATION) {
				return;
			}
			
			EntityMeteor meteor;
			for (int i = 0; i < Config.METEOR_SHOWER_COUNT; ++i) {
				meteor = new EntityMeteor(player.worldObj,
						posX + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS - Config.METEOR_SHOWER_RADIUS),
						posY + Abyss.rng.nextInt(250) + 50,
						posZ + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS - Config.METEOR_SHOWER_RADIUS));
				player.worldObj.spawnEntityInWorld(meteor);
			}
		}
	}

}
