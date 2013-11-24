package silentAbyss.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.ResourceLocation;
import silentAbyss.lib.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCrimsonCreeper extends RenderCreeper {

    @Override
    protected ResourceLocation getCreeperTextures(EntityCreeper entityCreeper) {
        
        return Textures.ENTITY_CRIMSON_CREEPER;
    }
}
