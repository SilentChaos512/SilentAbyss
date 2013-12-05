package silentAbyss.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import silentAbyss.Abyss;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.enchantment.ModEnchantments;
import silentAbyss.item.armor.PersonalElevationDevice;
import silentAbyss.item.tool.AbyssAxe;
import silentAbyss.item.tool.AbyssHoe;
import silentAbyss.item.tool.AbyssPickaxe;
import silentAbyss.item.tool.AbyssShovel;
import silentAbyss.item.tool.AbyssSword;
import silentAbyss.item.tool.TeleporterLinker;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Strings;
import silentAbyss.recipe.EnchantToolRecipe;
import silentAbyss.recipe.SigilRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public final static int CHAOS_METER_ID = 6017;
    public final static int CRAFTING_MATERIAL_ID = 6009;
    public final static int ENCHANT_TOKEN_ID = 6024;
    public final static int GEM_ID = 6000;
    public final static int KITTY_SUMMON_ID = 6018;
    public final static int MOD_BOOK_ID = 6040;
    public final static int PERSONAL_ELEVATION_DEVICE_ID = 6030;
    public final static int POTATO_STICK_ID = 6010;
    public final static int SHARD_ID = 6001;
    public final static int SIGIL_ID = 6022;
    public final static int SIGIL_RUNE_ID = 6021;
    public final static int SUGAR_COOKIE_ID = 6011;
    public final static int TELEPORTER_LINKER_ID = 6020;
    public final static int TOOL_START_ID = 6060;
    public final static int TORCH_BANDOLIER_ID = 6019;

    public static void init() {

        SARegistry.registerItem(ChaosMeter.class, Names.CHAOS_METER, CHAOS_METER_ID);
        SARegistry.registerItem(CraftingMaterial.class, Names.CRAFTING_MATERIALS, CRAFTING_MATERIAL_ID,
                "The ID for various crafting items, such as Ornate Sticks.");
        SARegistry.registerItem(EnchantToken.class, Names.ENCHANT_TOKEN, ENCHANT_TOKEN_ID);
        SARegistry.registerItem(Food.class, Names.POTATO_STICK, POTATO_STICK_ID, "", new Object[] { 8, 0.8f, false, Names.POTATO_STICK });
        SARegistry.registerItem(Food.class, Names.SUGAR_COOKIE, SUGAR_COOKIE_ID, "", new Object[] { 2, 0.4f, false, Names.SUGAR_COOKIE });
        SARegistry.registerItem(Gem.class, Names.GEM_ITEM, GEM_ID);
        SARegistry.registerItem(GemShard.class, Names.SHARD, SHARD_ID);
        SARegistry.registerItem(KittySummon.class, Names.KITTY_SUMMON, KITTY_SUMMON_ID);
        SARegistry.registerItem(ModBook.class, Names.MOD_BOOK, MOD_BOOK_ID);
        SARegistry.registerItem(PersonalElevationDevice.class, Names.PERSONAL_ELEVATION_DEVICE, PERSONAL_ELEVATION_DEVICE_ID);
        SARegistry.registerItem(Sigil.class, Names.SIGIL, SIGIL_ID);
        SARegistry.registerItem(SigilRune.class, Names.SIGIL_RUNE, SIGIL_RUNE_ID);
        SARegistry.registerItem(TeleporterLinker.class, Names.TELEPORTER_LINKER, TELEPORTER_LINKER_ID);
        SARegistry.registerItem(TorchBandolier.class, Names.TORCH_BANDOLIER, TORCH_BANDOLIER_ID);

        // Register tools.
        int id = TOOL_START_ID - 1;
        int gemType; // 0-3
        Object[] params = new Object[] { null, 0 }; // Constructor parameters
        for (int i = 0; i < 8; ++i) {
            // 8 loops, first regular gems, then supercharged.
            boolean supercharged = i > 3;
            gemType = supercharged ? i - 4 : i;
            params[0] = supercharged ? Abyss.materialEnergizedAbyssGem : Abyss.materialRegularAbyssGem;
            params[1] = gemType;
            String s = EnumGem.values()[gemType].name + (supercharged ? " Plus" : "");
            SARegistry.registerItem(AbyssSword.class, "Sword " + s, ++id, "", params);
            SARegistry.registerItem(AbyssPickaxe.class, "Pickaxe " + s, ++id, "", params);
            SARegistry.registerItem(AbyssShovel.class, "Shovel " + s, ++id, "", params);
            SARegistry.registerItem(AbyssAxe.class, "Axe " + s, ++id, "", params);
            SARegistry.registerItem(AbyssHoe.class, "Hoe " + s, ++id, "", params);
        }
    }

    public static void initItemRecipes() {

        Food.addRecipes();
        GameRegistry.addRecipe(new EnchantToolRecipe());
        GameRegistry.addRecipe(new SigilRecipe());
    }

    public static void addRandomChestGenLoot() {

        ItemStack potatoStick = new ItemStack(SARegistry.getItem(Names.POTATO_STICK));
        Item enchantToken = SARegistry.getItem(Names.ENCHANT_TOKEN);

        // Abyssite - can spawn in most chests.
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(
                new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(
                new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(
                new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 20));

        // Purite - can spawn in most chests.
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR)
                .addItem(new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 20));
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING)
                .addItem(new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 20));

        // Abyss gems - rarely in bonus chest, infrequently in pyramids
        ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(EnumGem.RUBY.getItem(), 1, 4, 5));
        ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(EnumGem.EMERALD.getItem(), 1, 4, 5));
        ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(EnumGem.SAPPHIRE.getItem(), 1, 4, 5));
        ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(EnumGem.TOPAZ.getItem(), 1, 4, 5));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(EnumGem.RUBY.getItem(), 1, 4, 50));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.EMERALD.getItem(), 1, 4, 50));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.SAPPHIRE.getItem(), 1, 4, 50));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST)
                .addItem(new WeightedRandomChestContent(EnumGem.TOPAZ.getItem(), 1, 4, 50));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.RUBY.getItem(), 1, 4, 35));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.EMERALD.getItem(), 1, 4, 35));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(
                new WeightedRandomChestContent(EnumGem.SAPPHIRE.getItem(), 1, 4, 35));
        ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST)
                .addItem(new WeightedRandomChestContent(EnumGem.TOPAZ.getItem(), 1, 4, 35));

        // Taters on sticks! - fairly common
        ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST).addItem(new WeightedRandomChestContent(potatoStick, 1, 4, 17));
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(potatoStick, 1, 4, 44));
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(potatoStick, 1, 4, 44));
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(potatoStick, 1, 4, 33));
        ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(potatoStick, 1, 4, 33));

        // Shrine chests
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.ABYSSITE.getItem(), 1, 4, 25));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.PURITE.getItem(), 1, 4, 25));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.RUBY.getItem(), 1, 4, 100));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.EMERALD.getItem(), 1, 4, 100));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.SAPPHIRE.getItem(), 1, 4, 100));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(EnumGem.TOPAZ.getItem(), 1, 4, 100));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(
                new WeightedRandomChestContent(new ItemStack(SARegistry.getItem(Names.CRAFTING_MATERIALS), 1, 0), 2, 4, 10));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Item.pickaxeGold), 1, 1, 1));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Item.enderPearl), 1, 4, 70));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Item.ingotIron), 4, 10, 40));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Item.ingotGold), 2, 6, 20));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Item.diamond), 1, 4, 7));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Item.book), 4, 8, 16));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(
                new WeightedRandomChestContent(new ItemStack(enchantToken, 1, ModEnchantments.mending.effectId), 1, 1, 7));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(
                new WeightedRandomChestContent(new ItemStack(enchantToken, 1, Enchantment.fortune.effectId), 1, 1, 7));
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).addItem(
                new WeightedRandomChestContent(new ItemStack(enchantToken, 1, Enchantment.silkTouch.effectId), 1, 1, 7));

        // Some configuring...
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).setMin(8);
        ChestGenHooks.getInfo(Strings.SHRINE_CHEST).setMax(12);
    }

}
