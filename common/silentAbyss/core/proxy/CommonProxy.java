package silentAbyss.core.proxy;

import silentAbyss.core.handlers.ServerTickHandler;
import silentAbyss.tileentity.TileEntityAbyssTeleporter;
import silentAbyss.tileentity.TileEntitySigilInfuser;
import silentAbyss.tileentity.TileTest;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {

	public CommonProxy() {}
	
	public void initRenderingAndTextures() {}

	public void registerRenderers() {}
	
	public void registerServerTickHandler() {
		
		TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}
	
	public void registerTileEntities() {
		
		GameRegistry.registerTileEntity(TileEntityAbyssTeleporter.class, "tileEntityAbyssTeleporter");
		GameRegistry.registerTileEntity(TileEntitySigilInfuser.class, "tileEntitySigilInfuser");
		GameRegistry.registerTileEntity(TileTest.class, "tileTest");
	}
}
