package silentAbyss.configuration;

public class Config {

    /*
     * Misc. config settings
     */
    public static ConfigOptionInt DIMENSION = new ConfigOptionInt("Dimension ID", 8);
    public static ConfigOptionBoolean PED_CREATIVE_FLIGHT = new ConfigOptionBoolean("PersonalElevationDevice.CreativeFlight", false);
    public static ConfigOptionInt SHARDS_PER_GEM = new ConfigOptionInt("ShardsPerGem", 9);

    /*
     * Graphic config settings
     */
    // nothing... yet

    /*
     * Audio config settings
     */
    public static ConfigOptionString ENABLE_SOUNDS = new ConfigOptionString("Sounds.Enabled", "all");
    public static final String[] ENABLE_SOUNDS_VALID_OPTIONS = { "all", "none" };

    /*
     * Sigil config settings
     */

    public static ConfigOptionInt SIGIL_USE_DURATION = new ConfigOptionInt("Sigil.BaseUseDuration", 30);
    public static ConfigOptionInt SIGIL_PROJECTILE_DAMAGE = new ConfigOptionInt("Sigil.BaseProjectileDamage", 8);
    public static ConfigOptionInt SIGIL_SUPPORT_DURATION = new ConfigOptionInt("Sigil.BaseSupportDuration", 2400);

    /*
     * World chaos config settings
     */

    public static ConfigOptionInt CHAOS_PER_WORLD_TICK = new ConfigOptionInt("Chaos.ChaosPerWorldTick", 50);
    public static ConfigOptionInt CHAOS_MAX_AMBIENT = new ConfigOptionInt("Chaos.MaxAmbientChaos", 10000);
    public static ConfigOptionInt CHAOS_COST_ABYSS_TELEPORTER = new ConfigOptionInt("Chaos.Cost.AbyssTeleporter", 100);
    public static ConfigOptionInt CHAOS_COST_SIGIL = new ConfigOptionInt("Chaos.Cost.Sigil", 25);
    public static ConfigOptionInt CHAOS_COST_SIGIL_SCEPTER = new ConfigOptionInt("Chaos.Cost.SigilScepter", 50);
    public static ConfigOptionInt CHAOS_COST_PERSONAL_ELEVATION_DEVICE = new ConfigOptionInt("Chaos.Cost.PersonalElevationDevice", 5);
    public static ConfigOptionInt CHAOS_COST_MENDING = new ConfigOptionInt("Chaos.Cost.Mending", 100);
    public static ConfigOptionInt CHAOS_COST_NIHIL = new ConfigOptionInt("Chaos.Cost.Nihil", 100);
    public static ConfigOptionInt CHAOS_COST_ICE_ASPECT = new ConfigOptionInt("Chaos.Cost.IceAspect", 100);

    /*
     * World chaos event config settings
     */

    public static ConfigOptionInt METEOR_SHOWER_RARITY = new ConfigOptionInt("Chaos.Event.Meteor.Rarity", 0);
    public static ConfigOptionInt METEOR_SHOWER_COUNT = new ConfigOptionInt("Chaos.Event.Meteor.Count", 64);
    public static ConfigOptionInt METEOR_SHOWER_RADIUS = new ConfigOptionInt("Chaos.Event.Meteor.Radius", 32);
    public static ConfigOptionInt METEOR_SHOWER_DURATION = new ConfigOptionInt("Chaos.Event.Meteor.Duration", 20);
    public static ConfigOptionInt METEOR_SHOWER_WARNING_DURATION = new ConfigOptionInt("Chaos.Event.Meteor.WarningDuration", 10);

    /*
     * World generation config settings
     */

    public static ConfigOptionInt WORLD_GEM_CLUSTER_COUNT = new ConfigOptionInt("World.AbyssGemClusterCount", 4);
    public static ConfigOptionInt WORLD_GEM_CLUSTER_SIZE = new ConfigOptionInt("World.AbyssGemClusterSize", 8);
    public static ConfigOptionInt WORLD_GEM_MAX_HEIGHT = new ConfigOptionInt("World.AbyssGemMaxHeight", 40);
    public static ConfigOptionInt WORLD_ABYSSITE_CLUSTER_COUNT = new ConfigOptionInt("World.AbyssiteClusterCount", 1);
    public static ConfigOptionInt WORLD_ABYSSITE_CLUSTER_SIZE = new ConfigOptionInt("World.AbyssiteClusterSize", 8);
    public static ConfigOptionInt WORLD_ABYSSITE_MAX_HEIGHT = new ConfigOptionInt("World.AbyssiteMaxHeight", 20);
    public static ConfigOptionInt WORLD_PURITE_CLUSTER_COUNT = new ConfigOptionInt("World.PuriteClusterCount", 1);
    public static ConfigOptionInt WORLD_PURITE_CLUSTER_SIZE = new ConfigOptionInt("World.PuriteClusterSize", 8);
    public static ConfigOptionInt WORLD_PURITE_MAX_HEIGHT = new ConfigOptionInt("World.PuriteMaxHeight", 20);

    /*
     * World structure config settings
     */
    public static ConfigOptionInt STRUCTURE_SHRINE_RARITY = new ConfigOptionInt("World.ShrineRarity", 256);

}
