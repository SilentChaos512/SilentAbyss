package silentAbyss;

import java.util.Random;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import silentAbyss.block.ModBlocks;
import silentAbyss.block.Teleporter;
import silentAbyss.command.CommandHandler;
import silentAbyss.configuration.ConfigHandler;
import silentAbyss.core.handlers.GuiHandler;
import silentAbyss.core.handlers.event.LivingFallEventHandler;
import silentAbyss.core.handlers.event.WorldLoadEventHandler;
import silentAbyss.core.proxy.CommonProxy;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.core.util.LogHelper;
import silentAbyss.enchantment.ModEnchantments;
import silentAbyss.entity.ModEntities;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.network.PacketHandler;
import silentAbyss.world.AbyssWorldGenerator;
import silentAbyss.world.WorldProviderAbyss;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { Reference.CHANNEL_NAME }, packetHandler = PacketHandler.class)
public class Abyss {

    @Instance("SilentAbyss")
    public static Abyss instance;

    public static Random rng = new Random();

    // Abyss dimension id.
    public static int dimension = 8;

    static int modEntityID = 0;
    static int startEntityId = 300;

    // Enums for abyss materials.
    public static EnumArmorMaterial materialFabricArmor = EnumHelper.addArmorMaterial("Abyss Fabric", 5, new int[] { 1, 3, 2, 1 }, 15);
    public static EnumToolMaterial materialRegularAbyssGem = EnumHelper.addToolMaterial("Abyss Gem", 2, 512, 8.0F, 3, 10);
    public static EnumToolMaterial materialEnergizedAbyssGem = EnumHelper.addToolMaterial("Energized Abyss Gem", 3, 2048, 12.0F, 4, 12);

    @SidedProxy(clientSide = "silentAbyss.core.proxy.ClientProxy", serverSide = "silentAbyss.core.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {

        CommandHandler.initCommands(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        LogHelper.init();

        ConfigHandler.init(event.getSuggestedConfigurationFile());

        if (FMLCommonHandler.instance().getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register(new AbyssSound());
        }

        ModBlocks.init();
        ModItems.init();
        ModEnchantments.init();
        ModEntities.init();

        ModBlocks.initBlockRecipes();
        ModItems.initItemRecipes();
        ModItems.addRandomChestGenLoot();

        LocalizationHelper.init();
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {

        proxy.registerTickHandlers();
        proxy.registerTileEntities();
        proxy.registerRenderers();
        proxy.registerKeyHandlers();

        ForgeChunkManager.setForcedChunkLoadingCallback(this, new TicketLoader());
        Teleporter.mod = this;

        // Register gui handlers
        NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());

        // Register world generator
        GameRegistry.registerWorldGenerator(new AbyssWorldGenerator());

        // Abyss dimension. WIP.
        DimensionManager.registerProviderType(dimension, WorldProviderAbyss.class, false);
        DimensionManager.registerDimension(dimension, dimension);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new LivingFallEventHandler());
        MinecraftForge.EVENT_BUS.register(new WorldLoadEventHandler());
    }
}
