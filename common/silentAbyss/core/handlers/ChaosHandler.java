package silentAbyss.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.lib.Strings;
import silentAbyss.world.AbyssWorldData;

public class ChaosHandler {
	
	private static final double baseChaos = 10000;

	private static AbyssWorldData worldData = null;
	
	public static void init(World world) {
		
		worldData = AbyssWorldData.forWorld(world);
	}
	
	public static void doWorldTick(World world) {
		
		if (worldData == null) {
			init(world);
		}
		
		// Add chaos if it is below ambient levels, otherwise subtract.
		if (worldData.chaos < Config.CHAOS_MAX_AMBIENT) {
			worldData.addChaos(Config.CHAOS_PER_WORLD_TICK);
		}
		else {
			worldData.addChaos(-Config.CHAOS_PER_WORLD_TICK);
		}
	}
	
	public static void addChaos(int change) {
		
		if (worldData == null) {
			return;
		}
		worldData.chaos += change;
	}
	
	public static int getChaos() {
		
		if (worldData == null) {
			return 0;
		}
		return worldData.chaos;
	}
	
	/**
	 * The multiplier for chaos-related events. Rises exponentially.
	 * @return
	 */
	public static double getChaosFactor() {
		
		double d = (double)getChaos() / baseChaos;
		return d * d;
	}
}
