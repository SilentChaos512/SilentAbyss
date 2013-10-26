package silentAbyss.configuration;

import java.io.File;
import java.util.logging.Level;

import silentAbyss.core.handlers.ServerTickHandler;
import silentAbyss.lib.BlockIds;
import silentAbyss.lib.EnchantmentIds;
import silentAbyss.lib.ItemIds;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	
	private static Configuration c;

	// Config categories
	public static final String CATEGORY_KEYBIND = "keybindings";
	public static final String CATEGORY_GRAPHICS = "graphics";
	public static final String CATEGORY_AUDIO = "audio";
	public static final String CATEGORY_ENCHANTMENT = "enchantment";
	public static final String CATEGORY_WORLD = "world";
	public static final String CATEGORY_WORLD_CHAOS = CATEGORY_WORLD + Configuration.CATEGORY_SPLITTER + "chaos";
	public static final String CATEGORY_WORLD_CHAOS_EVENT = CATEGORY_WORLD_CHAOS + Configuration.CATEGORY_SPLITTER + "event";
	public static final String CATEGORY_WORLD_GEN = CATEGORY_WORLD + Configuration.CATEGORY_SPLITTER + "generation";
	public static final String CATEGORY_WORLD_STRUCTURE = CATEGORY_WORLD + Configuration.CATEGORY_SPLITTER + "structure";
	public static final String CATEGORY_BLOCK_PROPERTIES = Configuration.CATEGORY_BLOCK + Configuration.CATEGORY_SPLITTER + "properties";
	public static final String CATEGORY_ITEM_PROPERTIES = Configuration.CATEGORY_ITEM + Configuration.CATEGORY_SPLITTER + "properties";
    public static final String CATEGORY_DURABILITY = Configuration.CATEGORY_ITEM + Configuration.CATEGORY_SPLITTER + "durability";
    
    public static void init(File file) {
		c = new Configuration(file);
		
		try {
			c.load();
			
			// Misc configs
			Config.SHARDS_PER_GEM = getGeneralInt(CATEGORY_ITEM_PROPERTIES, Config.SHARDS_PER_GEM_CONFIGNAME, Config.SHARDS_PER_GEM_DEFAULT,
					"Can be 4 or 9. Default " + Config.SHARDS_PER_GEM_DEFAULT + ".");
			if (Config.SHARDS_PER_GEM != 9) Config.SHARDS_PER_GEM = 4;
			
			// Graphic configs
			// nothing... yet
			
			// Audio configs
			Config.ENABLE_SOUNDS = c.get(CATEGORY_AUDIO, Config.ENABLE_SOUNDS_CONFIGNAME, Config.ENABLE_SOUNDS_DEFAULT).getString();
			
			// Sigil configs
			Config.SIGIL_BASE_BREAK_CHANCE = c.get(CATEGORY_ITEM_PROPERTIES, Config.SIGIL_BASE_BREAK_CHANCE_CONFIGNAME, Config.SIGIL_BASE_BREAK_CHANCE_DEFAULT).getInt();
			Config.SIGIL_BASE_USE_DURATION = c.get(CATEGORY_ITEM_PROPERTIES, Config.SIGIL_BASE_USE_DURATION_CONFIGNAME, Config.SIGIL_BASE_USE_DURATION_DEFAULT).getInt();
			Config.SIGIL_BASE_PROJECTILE_DAMAGE = c.get(CATEGORY_ITEM_PROPERTIES, Config.SIGIL_BASE_PROJECTILE_DAMAGE_CONFIGNAME, Config.SIGIL_BASE_PROJECTILE_DAMAGE_DEFAULT).getInt();
			Config.SIGIL_BASE_SUPPORT_DURATION = c.get(CATEGORY_ITEM_PROPERTIES, Config.SIGIL_BASE_SUPPORT_DURATION_CONFIGNAME, Config.SIGIL_BASE_SUPPORT_DURATION_DEFAULT).getInt();
			
			// World generation configs
			Config.WORLD_ABYSS_GEM_CLUSTER_COUNT = c.get(CATEGORY_WORLD_GEN, Config.WORLD_ABYSS_GEM_CLUSTER_COUNT_CONFIGNAME, Config.WORLD_ABYSS_GEM_CLUSTER_COUNT_DEFAULT).getInt();
			Config.WORLD_ABYSS_GEM_CLUSTER_SIZE = c.get(CATEGORY_WORLD_GEN, Config.WORLD_ABYSS_GEM_CLUSTER_SIZE_CONFIGNAME, Config.WORLD_ABYSS_GEM_CLUSTER_SIZE_DEFAULT).getInt();
			Config.WORLD_ABYSS_GEM_MAX_HEIGHT = c.get(CATEGORY_WORLD_GEN, Config.WORLD_ABYSS_GEM_MAX_HEIGHT_CONFIGNAME, Config.WORLD_ABYSS_GEM_MAX_HEIGHT_DEFAULT).getInt();
			Config.WORLD_ABYSSITE_CLUSTER_COUNT = c.get(CATEGORY_WORLD_GEN, Config.WORLD_ABYSSITE_CLUSTER_COUNT_CONFIGNAME, Config.WORLD_ABYSSITE_CLUSTER_COUNT_DEFAULT).getInt();
			Config.WORLD_ABYSSITE_CLUSTER_SIZE = c.get(CATEGORY_WORLD_GEN, Config.WORLD_ABYSSITE_CLUSTER_SIZE_CONFIGNAME, Config.WORLD_ABYSSITE_CLUSTER_SIZE_DEFAULT).getInt();
			Config.WORLD_ABYSSITE_MAX_HEIGHT = c.get(CATEGORY_WORLD_GEN, Config.WORLD_ABYSSITE_MAX_HEIGHT_CONFIGNAME, Config.WORLD_ABYSSITE_MAX_HEIGHT_DEFAULT).getInt();
			Config.WORLD_PURITE_CLUSTER_COUNT = getGeneralInt(CATEGORY_WORLD_GEN, Config.WORLD_PURITE_CLUSTER_COUNT_CONFIGNAME, Config.WORLD_PURITE_CLUSTER_COUNT_DEFAULT);
			Config.WORLD_PURITE_CLUSTER_SIZE = getGeneralInt(CATEGORY_WORLD_GEN, Config.WORLD_PURITE_CLUSTER_SIZE_CONFIGNAME, Config.WORLD_PURITE_CLUSTER_SIZE_DEFAULT);
			Config.WORLD_PURITE_MAX_HEIGHT = getGeneralInt(CATEGORY_WORLD_GEN, Config.WORLD_PURITE_MAX_HEIGHT_CONFIGNAME, Config.WORLD_PURITE_MAX_HEIGHT_DEFAULT);
			
			// World structure configs
			Config.STRUCTURE_SHRINE_RARITY = getGeneralInt(CATEGORY_WORLD_STRUCTURE, Config.STRUCTURE_SHRINE_RARITY_CONFIGNAME, Config.STRUCTURE_SHRINE_RARITY_DEFAULT,
					"The higher the number, the rarer the structure.");
			
			// Chaos configs
			Config.CHAOS_PER_WORLD_TICK = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_PER_WORLD_TICK_CONFIGNAME, Config.CHAOS_PER_WORLD_TICK_DEFAULT,
					"Amount of chaos added/subtracted every " + ServerTickHandler.baseWorldTickRate + " server ticks.");
			Config.CHAOS_MAX_AMBIENT = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_MAX_AMBIENT_CONFIGNAME, Config.CHAOS_MAX_AMBIENT_DEFAULT,
					"Maximum chaos that can accumulate from world ticks alone.");
			
			// Chaos cost configs
			Config.CHAOS_COST_ABYSS_TELEPORTER = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_COST_ABYSS_TELEPORTER_CONFIGNAME, Config.CHAOS_COST_ABYSS_TELEPORTER_DEFAULT);
			Config.CHAOS_COST_SIGIL = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_COST_SIGIL_CONFIGNAME, Config.CHAOS_COST_SIGIL_DEFAULT);
			Config.CHAOS_COST_SIGIL_SCEPTER = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_COST_SIGIL_SCEPTER_CONFIGNAME, Config.CHAOS_COST_SIGIL_SCEPTER_DEFAULT);
			Config.CHAOS_COST_MENDING = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_COST_MENDING_CONFIGNAME, Config.CHAOS_COST_MENDING_DEFAULT);
			Config.CHAOS_COST_NIHIL = getGeneralInt(CATEGORY_WORLD_CHAOS, Config.CHAOS_COST_NIHIL_CONFIGNAME, Config.CHAOS_COST_NIHIL_DEFAULT);
			
			// Chaos event configs
			Config.METEOR_SHOWER_RARITY = getGeneralInt(CATEGORY_WORLD_CHAOS_EVENT, Config.METEOR_SHOWER_RARITY_CONFIGNAME, Config.METEOR_SHOWER_RARITY_DEFAULT,
					"Average number of seconds between meteor showers. Higher number = less common.");
			Config.METEOR_SHOWER_COUNT = getGeneralInt(CATEGORY_WORLD_CHAOS_EVENT, Config.METEOR_SHOWER_COUNT_CONFIGNAME, Config.METEOR_SHOWER_COUNT_DEFAULT,
					"Number of meteors that fall each second.");
			Config.METEOR_SHOWER_RADIUS = getGeneralInt(CATEGORY_WORLD_CHAOS_EVENT, Config.METEOR_SHOWER_RADIUS_CONFIGNAME, Config.METEOR_SHOWER_RADIUS_DEFAULT,
					"How spread out the meteor shower is.");
			Config.METEOR_SHOWER_DURATION = getGeneralInt(CATEGORY_WORLD_CHAOS_EVENT, Config.METEOR_SHOWER_DURATION_CONFIGNAME, Config.METEOR_SHOWER_DURATION_DEFAULT,
					"The length of meteor showers in seconds.");
			Config.METEOR_SHOWER_WARNING_DURATION = getGeneralInt(CATEGORY_WORLD_CHAOS_EVENT, Config.METEOR_SHOWER_WARNING_DURATION_CONFIGNAME, Config.METEOR_SHOWER_WARNING_DURATION_DEFAULT,
					"The amount of time between the warning message and shower start.");
			
			// Block property configs
			BlockIds.ABYSS_GEM_ORE = c.getBlock(Strings.ABYSS_GEM_ORE_NAME, BlockIds.ABYSS_GEM_ORE_DEFAULT).getInt(BlockIds.ABYSS_GEM_ORE_DEFAULT);
			BlockIds.ABYSS_GEM_BLOCK = c.getBlock(Strings.ABYSS_GEM_BLOCK_NAME, BlockIds.ABYSS_GEM_BLOCK_DEFAULT).getInt(BlockIds.ABYSS_GEM_BLOCK_DEFAULT);
			BlockIds.ABYSS_TELEPORTER = c.getBlock(Strings.ABYSS_TELEPORTER_NAME, BlockIds.ABYSS_TELEPORTER_DEFAULT).getInt(BlockIds.ABYSS_TELEPORTER_DEFAULT);
			BlockIds.SIGIL_INFUSER = c.getBlock(Strings.SIGIL_INFUSER_NAME, BlockIds.SIGIL_INFUSER_DEFAULT).getInt(BlockIds.SIGIL_INFUSER_DEFAULT);
			BlockIds.BRICK = getBlockId(Strings.BRICK_NAME, BlockIds.BRICK_DEFAULT);
			BlockIds.BRICK_SLAB = getBlockId(Strings.BRICK_SLAB_NAME, BlockIds.BRICK_SLAB_DEFAULT);
			BlockIds.TEST_BLOCK = c.getBlock(Strings.TEST_BLOCK_NAME, BlockIds.TEST_BLOCK_DEFAULT).getInt(BlockIds.TEST_BLOCK_DEFAULT);
			
			// Item property configs
			ItemIds.ABYSS_GEM = c.getItem(Strings.ABYSS_GEM_NAME, ItemIds.ABYSS_GEM_DEFAULT).getInt(ItemIds.ABYSS_GEM_DEFAULT);
			ItemIds.ABYSS_SHARD = getItemId(Strings.ABYSS_SHARD_NAME, ItemIds.ABYSS_SHARD_DEFAULT);
			ItemIds.CRAFTING_MATERIAL = getItemId(Strings.CRAFTING_MATERIAL_NAME, ItemIds.CRAFTING_MATERIAL_DEFAULT,
					"The ID for various crafting items, such as Ornate Sticks.");
			ItemIds.POTATO_STICK = c.getItem(Strings.POTATO_STICK_NAME, ItemIds.POTATO_STICK_DEFAULT).getInt(ItemIds.POTATO_STICK_DEFAULT);
			ItemIds.TELEPORTER_LINKER = c.getItem(Strings.TELEPORTER_LINKER_NAME, ItemIds.TELEPORTER_LINKER_DEFAULT).getInt(ItemIds.TELEPORTER_LINKER_DEFAULT);
			ItemIds.ABYSS_SIGIL = c.getItem(Strings.ABYSS_SIGIL_NAME, ItemIds.ABYSS_SIGIL_DEFAULT).getInt(ItemIds.ABYSS_SIGIL_DEFAULT);
			ItemIds.SIGIL_STONE = c.getItem(Strings.SIGIL_STONE_NAME, ItemIds.SIGIL_STONE_DEFAULT).getInt(ItemIds.SIGIL_STONE_DEFAULT);
			ItemIds.SIGIL_SCEPTER = c.getItem(Strings.SIGIL_SCEPTER_NAME, ItemIds.SIGIL_SCEPTER_DEFAULT).getInt(ItemIds.SIGIL_SCEPTER_DEFAULT);
			ItemIds.TOOL_START = c.getItem("tool_start_id", ItemIds.TOOL_START_DEFAULT, "Abyss tools require a total of 40 ids.").getInt(ItemIds.TOOL_START_DEFAULT);
			
			// Enchantment property configs
			EnchantmentIds.MENDING = getEnchantmentId(Strings.MENDING_NAME, EnchantmentIds.MENDING_DEFAULT);
			EnchantmentIds.NIHIL = getEnchantmentId(Strings.NIHIL_NAME, EnchantmentIds.NIHIL_DEFAULT);
		}
		catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
		}
		finally {
			c.save();
		}
	}
    
    private static int getBlockId(String name, int default_id) {
    	return c.getBlock(name, default_id).getInt(default_id);
    }
    
    private static int getBlockId(String name, int default_id, String comment) {
    	return c.getBlock(name, default_id, comment).getInt(default_id);
    }
    
    private static int getItemId(String name, int default_id) {
    	return c.getItem(name, default_id).getInt(default_id);
    }
    
    private static int getItemId(String name, int default_id, String comment) {
    	return c.getItem(name, default_id, comment).getInt(default_id);
    }
    
    private static int getEnchantmentId(String name, int default_id) {
    	return c.get(CATEGORY_ENCHANTMENT, name, default_id).getInt(default_id);
    }
    
    private static int getEnchantmentId(String name, int default_id, String comment) {
    	return c.get(CATEGORY_ENCHANTMENT, name, default_id, comment).getInt(default_id);
    }
    
    private static int getGeneralInt(String category, String name, int default_value) {
    	return c.get(category, name, default_value).getInt(default_value);
    }
    
    private static int getGeneralInt(String category, String name, int default_value, String comment) {
    	return c.get(category, name, default_value, comment).getInt(default_value);
    }
}
