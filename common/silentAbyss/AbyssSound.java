package silentAbyss;

import silentAbyss.core.util.LogHelper;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class AbyssSound {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		try {
			String[] soundFiles = {
					"test.wav"
			};
			
			for (int i = 0; i < soundFiles.length; ++i) {
				//event.manager.soundPoolSounds.addSound(soundFiles[i], Abyss.class.getResource("/mods/SilentAbyss/sounds/" + soundFiles[i]));
			}
		}
		catch (Exception e) {
			LogHelper.severe("[ERROR] Failed to register one or more sound effects!");
		}
	}
}
