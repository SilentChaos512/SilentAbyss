package silentAbyss.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraftforge.client.MinecraftForgeClient;
import silentAbyss.block.ModBlocks;
import silentAbyss.client.model.ModelGrumbling;
import silentAbyss.client.renderer.entity.RenderGrumbling;
import silentAbyss.client.renderer.entity.RenderProjectileMagic;
import silentAbyss.client.renderer.item.ItemTestRenderer;
import silentAbyss.client.renderer.item.ToolRenderer;
import silentAbyss.client.renderer.tileentity.TileEntityTestRenderer;
import silentAbyss.entity.monster.EntityGrumbling;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.item.ModItems;
import silentAbyss.lib.RenderIds;
import silentAbyss.tileentity.TileTest;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		
		/*
		 * Blocks
		 */
		RenderIds.testRender = RenderingRegistry.getNextAvailableRenderId();

		/*
		 * Items
		 */
		MinecraftForgeClient.registerItemRenderer(ModBlocks.blockTest.blockID, new ItemTestRenderer());
		//MinecraftForgeClient.registerItemRenderer(ModItems.pickaxeAbyssRuby.itemID, new ToolRenderer());
		
		/*
		 * Mobs
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntityGrumbling.class, new RenderGrumbling(new ModelGrumbling(), 0.5F));
		
		/*
		 * Projectiles
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectileMagic.class, new RenderProjectileMagic());
		RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new RenderFireball(0));
	}
	
	@Override
	public void registerTileEntities() {
		
		super.registerTileEntities();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileTest.class, new TileEntityTestRenderer());
	}
}
