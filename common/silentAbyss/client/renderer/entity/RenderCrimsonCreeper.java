package silentAbyss.client.renderer.entity;

import silentAbyss.lib.Textures;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;


public class RenderCrimsonCreeper extends RenderCreeper {

    @Override
    protected ResourceLocation getCreeperTextures(EntityCreeper entityCreeper) {
        
        return Textures.ENTITY_CRIMSON_CREEPER;
    }
}
