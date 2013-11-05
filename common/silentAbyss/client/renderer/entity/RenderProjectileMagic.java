package silentAbyss.client.renderer.entity;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.lib.Textures;

public class RenderProjectileMagic extends Render {

    @Override
    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {

        int colorR = 255, colorG = 255, colorB = 255;

        if (entity instanceof EntityProjectileMagic) {
            EntityProjectileMagic p = (EntityProjectileMagic) entity;

            colorR = p.colorR;
            colorG = p.colorG;
            colorB = p.colorB;
            // LogHelper.debug(p.type + ", " + p.damage);
            // LogHelper.debug(AbyssLog.coord(colorR, colorG, colorB));
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) d0, (float) d1, (float) d2);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = 0.35f;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);

        // FIXME: Texture not loading!
        bindTexture(Textures.EFFECT_MAGIC_PROJECTILE);
        Tessellator tessellator = Tessellator.instance;

        float f3 = 0; // icon.getMinU();
        float f4 = 1; // icon.getMaxU();
        float f5 = 0; // icon.getMinV();
        float f6 = 1; // icon.getMaxV();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(colorR, colorG, colorB, 64);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
        tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
        tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
        tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
        tessellator.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        return Textures.EFFECT_MAGIC_PROJECTILE;
    }

}
