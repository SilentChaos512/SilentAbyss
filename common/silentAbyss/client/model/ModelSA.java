package silentAbyss.client.model;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSA {

    protected IModelCustom model;
    
    public ModelSA(String modelName) {
        
        model = AdvancedModelLoader.loadModel(modelName);
    }
    
    public void render() {
        
        model.renderAll();
    }
}
