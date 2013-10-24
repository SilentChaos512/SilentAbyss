package silentAbyss.client.renderer.entity;

import silentAbyss.lib.Textures;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGrumbling extends RenderLiving {
    
    public RenderGrumbling(ModelBase modelBase, float par2) {
        
        super(modelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        
        return Textures.ENTITY_GRUMBLING;
    }

}
