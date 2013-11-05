package silentAbyss.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import silentAbyss.client.model.ModelTest;
import silentAbyss.lib.Textures;
import silentAbyss.tileentity.TileTest;
import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityTestRenderer extends TileEntitySpecialRenderer {

    private ModelTest modelTest = new ModelTest();

    // private final RenderItem customRenderItem;

    public TileEntityTestRenderer() {

        // customRenderItem?
        // (ee3.client.renderer.tileentity.TileEntityAludelRender)
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        if (tileEntity instanceof TileTest) {
            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
            float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
            GL11.glRotatef(rotationAngle, 0f, 1f, 0f);
            GL11.glScalef(0.5f, 0.5f, 0.5f);

            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.TEST_BLOCK);

            modelTest.render();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

}
