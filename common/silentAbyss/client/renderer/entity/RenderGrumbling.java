package silentAbyss.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import silentAbyss.lib.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGrumbling extends RenderLiving {

    public RenderGrumbling(ModelBase modelBase, float par2) {

        super(modelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        return Textures.ENTITY_GRUMBLING;
    }

}
