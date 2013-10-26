package silentAbyss.core.chaos;

import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.entity.projectile.EntityMeteor;
import net.minecraft.world.World;

public class MeteorEvent extends ChaosEvent {
	
	private int timer = 10;

	public MeteorEvent(World world, int x, int y, int z) {
		super(world, x, y, z, new Object[0]);
		// shower time + warning time
		timer = Config.METEOR_SHOWER_DURATION + Config.METEOR_SHOWER_WARNING_DURATION;
	}

	@Override
	public void doTick() {
		
		// TODO: Do I need to check that area is loaded?
		
		if (!worldObj.isRemote) {
			
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
				meteor = new EntityMeteor(worldObj,
						posX + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS - Config.METEOR_SHOWER_RADIUS),
						posY + Abyss.rng.nextInt(250) + 50,
						posZ + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS - Config.METEOR_SHOWER_RADIUS));
				worldObj.spawnEntityInWorld(meteor);
			}
		}
	}

}
