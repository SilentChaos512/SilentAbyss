package silentAbyss.core.proxy;

import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraftforge.client.MinecraftForgeClient;
import silentAbyss.block.ModBlocks;
import silentAbyss.client.model.ModelGrumbling;
import silentAbyss.client.renderer.entity.RenderCrimsonCreeper;
import silentAbyss.client.renderer.entity.RenderGrumbling;
import silentAbyss.client.renderer.entity.RenderProjectileMagic;
import silentAbyss.client.renderer.item.ItemTestRenderer;
import silentAbyss.client.renderer.item.RenderSigil;
import silentAbyss.client.renderer.tileentity.TileEntityTestRenderer;
import silentAbyss.core.handlers.tick.PlayerClientTickHandler;
import silentAbyss.core.handlers.tick.RenderTickHandler;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.core.util.KeyHelper;
import silentAbyss.entity.monster.CrimsonCreeper;
import silentAbyss.entity.monster.EntityGrumbling;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Names;
import silentAbyss.lib.RenderIds;
import silentAbyss.tileentity.TileTest;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {

        registerRenderersBlocks();
        registerRenderersItems();
        registerRenderersMobs();
        registerRenderersProjectiles();
    }

    private void registerRenderersBlocks() {

        RenderIds.testRender = RenderingRegistry.getNextAvailableRenderId();
    }

    private void registerRenderersItems() {

        MinecraftForgeClient.registerItemRenderer(SARegistry.getItem(Names.SIGIL).itemID, new RenderSigil());
//        MinecraftForgeClient.registerItemRenderer(ModBlocks.blockTest.blockID, new ItemTestRenderer());
    }

    private void registerRenderersMobs() {

        RenderingRegistry.registerEntityRenderingHandler(EntityGrumbling.class, new RenderGrumbling(new ModelGrumbling(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(CrimsonCreeper.class, new RenderCrimsonCreeper());
    }

    private void registerRenderersProjectiles() {

        RenderingRegistry.registerEntityRenderingHandler(EntityProjectileMagic.class, new RenderProjectileMagic());
        RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, new RenderFireball(0));
    }

    @Override
    public void registerTickHandlers() {

        super.registerTickHandlers();
        TickRegistry.registerTickHandler(new PlayerClientTickHandler(), Side.CLIENT);
        TickRegistry.registerTickHandler(new RenderTickHandler(), Side.CLIENT);
    }

    @Override
    public void registerTileEntities() {

        super.registerTileEntities();

        ClientRegistry.bindTileEntitySpecialRenderer(TileTest.class, new TileEntityTestRenderer());
    }

    @Override
    public void registerKeyHandlers() {

        KeyHelper.init();
    }
}
