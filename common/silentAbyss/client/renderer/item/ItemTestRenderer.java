package silentAbyss.client.renderer.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import silentAbyss.client.model.ModelTest;
import silentAbyss.lib.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemTestRenderer implements IItemRenderer {
	
	private ModelTest modelTest;
	
	public ItemTestRenderer() {
		modelTest = new ModelTest();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		switch (type) {
		case ENTITY: {
			renderModel(-0.5f, -0.38f, 0.5f, 1.0f);
			return;
		}
		case EQUIPPED: {
			renderModel(0.0f, 0.0f, 1.0f, 1.0f);
			return;
		}
		case EQUIPPED_FIRST_PERSON: {
			renderModel(0.0f, 0.0f, 1.0f, 1.0f);
			return;
		}
		case INVENTORY: {
			renderModel(-1.0f, -0.9f, 0.0f, 1.0f);
			return;
		}
		default:
			return;
		}
	}

	private void renderModel(float x, float y, float z, float scale) {
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glScalef(scale * 0.75f, scale * 0.75f, scale * 0.75f);
		GL11.glTranslatef(x + 0.5f, y + 0.5f, z - 0.5f);
		GL11.glRotatef(-90f, 1f, 0, 0);
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.TEST_BLOCK);
		
		modelTest.render();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
