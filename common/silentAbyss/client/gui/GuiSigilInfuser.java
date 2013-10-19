package silentAbyss.client.gui;

import org.lwjgl.opengl.GL11;

import silentAbyss.inventory.ContainerSigilInfuser;
import silentAbyss.lib.Textures;
import silentAbyss.tileentity.TileEntitySigilInfuser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

public class GuiSigilInfuser extends GuiContainer {
	
	private TileEntitySigilInfuser infuser;

	public GuiSigilInfuser(InventoryPlayer inventoryPlayer, TileEntitySigilInfuser tileEntity) {
		super(new ContainerSigilInfuser(inventoryPlayer, tileEntity));
		infuser = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Sigil Infuser", 8, 6, 4210752);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		//int texture = mc.renderEngine.getTexture("/gui/SigilInfuserGui.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(Textures.GUI_SIGIL_INFUSER);
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		int i1 = infuser.getCookProgessScaled(48);
		this.drawTexturedModalRect(x + 95, y + 34, 176, 14, i1 + 1, 16);
	}

}
