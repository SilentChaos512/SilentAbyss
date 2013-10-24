package silentAbyss.client.renderer.item;

import silentAbyss.item.ModItems;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

public class ToolRenderer implements IItemRenderer {
	
	private static RenderItem renderItem = new RenderItem();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		renderItem.renderIcon(0, 0, item.getItem().getIcon(item, 0), 16, 16);
		renderItem.renderIcon(0, 0, ModItems.potatoStick.getIconFromDamage(0), 16, 16);
	}

}
