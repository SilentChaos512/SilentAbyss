package silentAbyss.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import silentAbyss.block.ModBlocks;
import silentAbyss.client.model.ModelGrumbling;
import silentAbyss.client.renderer.entity.RenderGrumbling;
import silentAbyss.client.renderer.entity.RenderProjectileMagic;
import silentAbyss.client.renderer.item.ItemTestRenderer;
import silentAbyss.client.renderer.item.ToolRenderer;
import silentAbyss.client.renderer.tileentity.TileEntityTestRenderer;
import silentAbyss.entity.monster.EntityGrumbling;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.item.ModItems;
import silentAbyss.lib.RenderIds;
import silentAbyss.tileentity.TileTest;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileMagic.class, new RenderProjectileMagic());
	}
	
	@Override
	public void initRenderingAndTextures() {
		RenderIds.testRender = RenderingRegistry.getNextAvailableRenderId();
		
		MinecraftForgeClient.registerItemRenderer(ModBlocks.blockTest.blockID, new ItemTestRenderer());
		//MinecraftForgeClient.registerItemRenderer(ModItems.pickaxeAbyssRuby.itemID, new ToolRenderer());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGrumbling.class, new RenderGrumbling(new ModelGrumbling(), 0.5F));
	}
	
	@Override
	public void registerTileEntities() {
		
		super.registerTileEntities();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileTest.class, new TileEntityTestRenderer());
	}
}
