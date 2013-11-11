package silentAbyss.lib;

import java.util.Arrays;

public class Reference {

    public static final boolean DEBUG_MODE = false;

    public static final String MOD_ID = "SilentAbyss";
    public static final String MOD_NAME = "Silent's Abyss";
    public static final String VERSION_NUMBER = "0.4.04"; // "@VERSION@ (build @BUILD_NUMBER@)";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = "required-after:Forge@[9.10.1.849,)";

    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;

    /*
     * Basic gems
     */
    public static final int INDEX_RUBY = 0;
    public static final int INDEX_EMERALD = 1;
    public static final int INDEX_SAPPHIRE = 2;
    public static final int INDEX_TOPAZ = 3;
    public static final int INDEX_ABYSSITE = 4;
    public static final int INDEX_PURITE = 5;
    public static final int GEM_TYPE_COUNT = 6;

    /*
     * Compound gems
     */
    public static final int INDEX_RUBY_PLUS = 6;
    public static final int INDEX_EMERALD_PLUS = 7;
    public static final int INDEX_SAPPHIRE_PLUS = 8;
    public static final int INDEX_TOPAZ_PLUS = 9;
    public static final int INDEX_CONUNDRUMITE = 10;
    public static final int INDEX_ABYSS_DIAMOND = 11;

    public static final String[] allGemNames = { "Ruby", "Emerald", "Sapphire", "Topaz", "Abyssite", "Purite", "RubyPlus", "EmeraldPlus",
            "SapphirePlus", "TopazPlus", "Conundrumite", "AbyssDiamond" };
    public static final String[] basicGemNames = Arrays.copyOfRange(allGemNames, 0, 6);

}
