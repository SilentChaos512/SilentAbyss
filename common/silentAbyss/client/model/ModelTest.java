package silentAbyss.client.model;

import silentAbyss.lib.Models;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTest {
	private IModelCustom modelTest;
	
	public ModelTest() {
		modelTest = AdvancedModelLoader.loadModel(Models.TEST);
	}
	
	public void render() {
		modelTest.renderAll();
	}
}
