package silentAbyss.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import silentAbyss.core.handlers.tick.ServerTickHandler;
import silentAbyss.lib.BlockIds;
import silentAbyss.lib.EnchantmentIds;
import silentAbyss.lib.ItemIds;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.FMLLog;

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

        // Some random variables for validating and getting config options.
        int k;
        try {
            c.load();

            /*
             * Misc configs.
             */
            Config.SHARDS_PER_GEM.loadValue(c, CATEGORY_ITEM_PROPERTIES).validate();

            k = Config.PED_CREATIVE_FLIGHT_DEFAULT == true ? 1 : 0;
            k = getGeneralInt(CATEGORY_ITEM_PROPERTIES, Config.PED_CREATIVE_FLIGHT_CONFIGNAME, k,
                    "1=PED flies like creative; 0=PED flies like jetpack. Creative flight may interfere with other mods. Default 0.");
            Config.PED_CREATIVE_FLIGHT = k == 1;

            /*
             * Graphic configs.
             */
            // nothing... yet

            /*
             * Audio configs.
             */
            Config.ENABLE_SOUNDS.loadValue(c, CATEGORY_AUDIO).validate();

            /*
             * Sigil configs.
             */
            Config.SIGIL_BREAK_CHANCE.loadValue(c, CATEGORY_ITEM_PROPERTIES);
            Config.SIGIL_USE_DURATION.loadValue(c, CATEGORY_ITEM_PROPERTIES);
            Config.SIGIL_PROJECTILE_DAMAGE.loadValue(c, CATEGORY_ITEM_PROPERTIES);
            Config.SIGIL_SUPPORT_DURATION.loadValue(c, CATEGORY_ITEM_PROPERTIES);

            /*
             * World generation configs
             */
            Config.WORLD_GEM_CLUSTER_COUNT.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_GEM_CLUSTER_SIZE.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_GEM_MAX_HEIGHT.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_ABYSSITE_CLUSTER_COUNT.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_ABYSSITE_CLUSTER_SIZE.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_ABYSSITE_MAX_HEIGHT.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_PURITE_CLUSTER_COUNT.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_PURITE_CLUSTER_SIZE.loadValue(c, CATEGORY_WORLD_GEN);
            Config.WORLD_PURITE_MAX_HEIGHT.loadValue(c, CATEGORY_WORLD_GEN);

            /*
             * World structure configs.
             */
            Config.STRUCTURE_SHRINE_RARITY.loadValue(c, CATEGORY_WORLD_STRUCTURE, Strings.CONFIG_STRUCTURE_RARITY_COMMENT);

            /*
             * Chaos configs.
             */
            Config.CHAOS_PER_WORLD_TICK.loadValue(c, CATEGORY_WORLD_CHAOS, "Amount of chaos added/subtracted every "
                    + ServerTickHandler.baseWorldTickRate + " server ticks");
            Config.CHAOS_MAX_AMBIENT.loadValue(c, CATEGORY_WORLD_CHAOS, "Maximum chaos that can accumulate from world ticks alone.");

            /*
             * Chaos cost configs
             */
            Config.CHAOS_COST_ABYSS_TELEPORTER.loadValue(c, CATEGORY_WORLD_CHAOS);
            Config.CHAOS_COST_SIGIL.loadValue(c, CATEGORY_WORLD_CHAOS);
            Config.CHAOS_COST_SIGIL_SCEPTER.loadValue(c, CATEGORY_WORLD_CHAOS);
            Config.CHAOS_COST_PERSONAL_ELEVATION_DEVICE.loadValue(c, CATEGORY_WORLD_CHAOS);
            Config.CHAOS_COST_MENDING.loadValue(c, CATEGORY_WORLD_CHAOS);
            Config.CHAOS_COST_NIHIL.loadValue(c, CATEGORY_WORLD_CHAOS);

            /*
             * Chaos event configs
             */
            Config.METEOR_SHOWER_RARITY.loadValue(c, CATEGORY_WORLD_CHAOS_EVENT);
            Config.METEOR_SHOWER_COUNT.loadValue(c, CATEGORY_WORLD_CHAOS_EVENT);
            Config.METEOR_SHOWER_RADIUS.loadValue(c, CATEGORY_WORLD_CHAOS_EVENT);
            Config.METEOR_SHOWER_DURATION.loadValue(c, CATEGORY_WORLD_CHAOS_EVENT);
            Config.METEOR_SHOWER_WARNING_DURATION.loadValue(c, CATEGORY_WORLD_CHAOS_EVENT);

            /*
             * Block property configs.
             */
            BlockIds.ABYSS_GEM_ORE = getBlockId(Strings.ABYSS_GEM_ORE_NAME, BlockIds.ABYSS_GEM_ORE_DEFAULT);
            BlockIds.ABYSS_GEM_BLOCK = getBlockId(Strings.ABYSS_GEM_BLOCK_NAME, BlockIds.ABYSS_GEM_BLOCK_DEFAULT);
            BlockIds.ABYSS_TELEPORTER = getBlockId(Strings.ABYSS_TELEPORTER_NAME, BlockIds.ABYSS_TELEPORTER_DEFAULT);
            BlockIds.SIGIL_INFUSER = getBlockId(Strings.SIGIL_INFUSER_NAME, BlockIds.SIGIL_INFUSER_DEFAULT);
            BlockIds.BRICK = getBlockId(Strings.BRICK_NAME, BlockIds.BRICK_DEFAULT);
            BlockIds.BRICK_SLAB = getBlockId(Strings.BRICK_SLAB_NAME, BlockIds.BRICK_SLAB_DEFAULT);
            BlockIds.TEST_BLOCK = getBlockId(Strings.TEST_BLOCK_NAME, BlockIds.TEST_BLOCK_DEFAULT);

            /*
             * Item property configs.
             */
            ItemIds.ABYSS_GEM = getItemId(Strings.ABYSS_GEM_NAME, ItemIds.ABYSS_GEM_DEFAULT);
            ItemIds.ABYSS_SHARD = getItemId(Strings.ABYSS_SHARD_NAME, ItemIds.ABYSS_SHARD_DEFAULT);
            ItemIds.CRAFTING_MATERIAL = getItemId(Strings.CRAFTING_MATERIAL_NAME, ItemIds.CRAFTING_MATERIAL_DEFAULT,
                    "The ID for various crafting items, such as Ornate Sticks.");
            ItemIds.POTATO_STICK = getItemId(Strings.POTATO_STICK_NAME, ItemIds.POTATO_STICK_DEFAULT);
            ItemIds.TELEPORTER_LINKER = getItemId(Strings.TELEPORTER_LINKER_NAME, ItemIds.TELEPORTER_LINKER_DEFAULT);
            ItemIds.ABYSS_SIGIL = getItemId(Strings.ABYSS_SIGIL_NAME, ItemIds.ABYSS_SIGIL_DEFAULT);
            ItemIds.SIGIL_STONE = getItemId(Strings.SIGIL_STONE_NAME, ItemIds.SIGIL_STONE_DEFAULT);
            ItemIds.SIGIL_SCEPTER = getItemId(Strings.SIGIL_SCEPTER_NAME, ItemIds.SIGIL_SCEPTER_DEFAULT);
            ItemIds.PERSONAL_ELEVATION_DEVICE = getItemId(Strings.PERSONAL_ELEVATION_DEVICE_NAME, ItemIds.PERSONAL_ELEVATION_DEVICE_DEFAULT);
            ItemIds.MOD_BOOK = getItemId(Strings.MOD_BOOK_NAME, ItemIds.MOD_BOOK_DEFAULT);
            ItemIds.TOOL_START = getItemId("tool_start_id", ItemIds.TOOL_START_DEFAULT, "Abyss tools require a total of 40 IDs.");

            /*
             * Enchantment property configs.
             */
            EnchantmentIds.MENDING = getEnchantmentId(Strings.MENDING_NAME, EnchantmentIds.MENDING_DEFAULT);
            EnchantmentIds.NIHIL = getEnchantmentId(Strings.NIHIL_NAME, EnchantmentIds.NIHIL_DEFAULT);
            EnchantmentIds.ICE_ASPECT = getEnchantmentId(Strings.ICE_ASPECT_NAME, EnchantmentIds.ICE_ASPECT_DEFAULT);
        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        } finally {
            c.save();
        }
    }

    private static int getBlockId(String name, int default_id) {

        return c.getBlock(name, default_id).getInt(default_id);
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

    private static int getGeneralInt(String category, String name, int default_value, String comment) {

        return c.get(category, name, default_value, comment).getInt(default_value);
    }
}
