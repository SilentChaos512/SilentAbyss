package silentAbyss.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import silentAbyss.enchantment.ModEnchantments;
import silentAbyss.item.tool.AbyssAxe;
import silentAbyss.item.tool.AbyssHoe;
import silentAbyss.item.tool.AbyssPickaxe;
import silentAbyss.item.tool.AbyssShovel;
import silentAbyss.item.tool.AbyssSword;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EnchantToken extends ItemSA {

    /**
     * Small class for storing enchantment data in hash map\
     */
    public static class EnchData {

        public byte validTools;
        public Enchantment enchantment;

        public String getName() {

            return enchantment.getName();
        }

        public int getMaxLevel() {

            return enchantment.getMaxLevel();
        }
    }

    // These constants are used to store which tools an enchantment is valid
    // for. See init().
    public final static byte T_SWORD = 16;
    public final static byte T_PICKAXE = 8;
    public final static byte T_SHOVEL = 4;
    public final static byte T_AXE = 2;
    public final static byte T_HOE = 1;

    /**
     * Stores the enchantments that there are tokens for.
     */
    public static HashMap<Integer, EnchData> enchants = new HashMap<Integer, EnchData>();

    public EnchantToken(int id) {

        super(id);

        setMaxStackSize(64);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Populates the enchants hash map.
     */
    public static void init() {

        if (!enchants.isEmpty()) {
            return;
        }

        addEnchantment(Enchantment.baneOfArthropods, T_SWORD | T_PICKAXE | T_SHOVEL);
        addEnchantment(Enchantment.efficiency, T_SWORD | T_PICKAXE | T_SHOVEL | T_AXE);
        addEnchantment(Enchantment.fireAspect, T_SWORD);
        addEnchantment(Enchantment.fortune, T_PICKAXE | T_SHOVEL | T_AXE | T_HOE);
        addEnchantment(Enchantment.knockback, T_SWORD | T_AXE | T_HOE);
        addEnchantment(Enchantment.looting, T_SWORD);
        addEnchantment(Enchantment.sharpness, T_SWORD | T_AXE);
        addEnchantment(Enchantment.silkTouch, T_SWORD | T_PICKAXE | T_SHOVEL | T_AXE);
        addEnchantment(Enchantment.smite, T_SWORD | T_AXE | T_HOE);
        addEnchantment(Enchantment.unbreaking, T_SWORD | T_PICKAXE | T_SHOVEL | T_AXE | T_HOE);

        addEnchantment(ModEnchantments.iceAspect, T_SWORD);
        addEnchantment(ModEnchantments.mending, T_SWORD | T_PICKAXE | T_SHOVEL | T_AXE | T_HOE);
        addEnchantment(ModEnchantments.nihil, T_SWORD);
    }

    /**
     * Adds an enchantment to the hash map. validTools is appended to the
     * enchantment name after VALID_TOOL_SEP.
     * 
     * @param e
     * @param validTools
     */
    private static void addEnchantment(Enchantment e, int validTools) {

        EnchData data = new EnchantToken.EnchData();
        data.enchantment = e;
        data.validTools = (byte) validTools;
        enchants.put(e.effectId, data);
    }

    /**
     * Gets the localized name of the enchantment by first stripping off the
     * valid tool data.
     * 
     * @param key
     * @return
     */
    public static String getEnchantmentName(int key) {

        String s = "";

        if (enchants.containsKey(key)) {
            s = enchants.get(key).getName();
            s = I18n.getString(s);
        }

        return s;
    }

    /**
     * Creates a String listing the tools this enchantment can be applied to.
     * 
     * @param key
     * @return
     */
    public static String validToolsFor(int key) {

        List l = new ArrayList<String>();
        int k = enchants.get(key).validTools;

        try {
            if ((k & T_SWORD) != 0)
                l.add("Sword");
            if ((k & T_PICKAXE) != 0)
                l.add("Pickaxe");
            if ((k & T_SHOVEL) != 0)
                l.add("Shovel");
            if ((k & T_AXE) != 0)
                l.add("Axe");
            if ((k & T_HOE) != 0)
                l.add("Hoe");

            StringBuilder sb = new StringBuilder();

            // Separate each item with commas
            for (Object o : l) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append((String) o);
            }

            return sb.toString();
        }
        catch (Exception ex) {
        }

        return "";
    }

    /**
     * Determine if token can be applied to the tool. Checks that the tool is
     * the right type, the enchantments don't conflict, and the enchantment can
     * be "leveled up".
     * 
     * @param token
     * @param tool
     * @return
     */
    public static boolean capApplyTokenToTool(ItemStack token, ItemStack tool) {

        int k = token.getItemDamage();
        if (!enchants.containsKey(k)) {
            return false;
        }

        EnchData e = enchants.get(k);
        k = enchants.get(k).validTools;

        if ((tool.getItem() instanceof AbyssSword && (k & T_SWORD) != 0)
                || (tool.getItem() instanceof AbyssPickaxe && (k & T_PICKAXE) != 0)
                || (tool.getItem() instanceof AbyssShovel && (k & T_SHOVEL) != 0)
                || (tool.getItem() instanceof AbyssAxe && (k & T_AXE) != 0) || (tool.getItem() instanceof AbyssHoe && (k & T_HOE) != 0)) {
            // Token and tool type match.
            // Does tool have any enchantments?
            if (tool.hasTagCompound()) {
                if (!tool.stackTagCompound.hasKey("ench")) {
                    return true;
                }
            }
            else if (!tool.hasTagCompound()) {
                return true;
            }

            // Does tool already have this enchantment? If so, can it be
            // upgraded?
            k = EnchantmentHelper.getEnchantmentLevel(e.enchantment.effectId, tool);
            if (k == 0) {
                // Tool does not have this enchantment. Does it conflict with
                // existing enchants?
                for (int i = 0; i < tool.getEnchantmentTagList().tagCount(); ++i) {
                    k = ((NBTTagCompound) tool.getEnchantmentTagList().tagAt(i)).getShort("id");
                    if (!e.enchantment.canApplyTogether(Enchantment.enchantmentsList[k])
                            || !Enchantment.enchantmentsList[k].canApplyTogether(e.enchantment)) {
                        return false;
                    }
                }
                return true;
            }
            else if (k < e.getMaxLevel()) {
                // Tool has enchantment, but it can be leveled up.
                return true;
            }
        }

        return false;
    }

    /**
     * Applies the token's enchantment to the tool. Need to check
     * canApplyTokenToTool before calling.
     * 
     * @param token
     * @param tool
     */
    public static void enchantTool(ItemStack token, ItemStack tool) {

        int k = token.getItemDamage();
        EnchData e = enchants.get(k);
        k = EnchantmentHelper.getEnchantmentLevel(e.enchantment.effectId, tool);

        // Adding enchantment is easy, leveling it up is a bit harder.
        if (k == 0) {
            tool.addEnchantment(e.enchantment, 0);
        }

        if (tool.stackTagCompound == null) {
            tool.setTagCompound(new NBTTagCompound());
        }
        if (!tool.stackTagCompound.hasKey("ench")) {
            tool.stackTagCompound.setTag("ench", new NBTTagList("ench"));
        }

        NBTTagCompound t;
        for (int i = 0; i < tool.getEnchantmentTagList().tagCount(); ++i) {
            t = (NBTTagCompound) tool.getEnchantmentTagList().tagAt(i);
            k = t.getShort("id");
            if (k == e.enchantment.effectId) {
                k = t.getShort("lvl");
                t.setShort("lvl", (short) (k + 1));
            }
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        int k = stack.getItemDamage();

        if (k == 0) {
            list.add("\u00a76Empty");
            list.add("Used to enchant Abyss tools.");
        }
        else if (enchants.containsKey(k)) {
            list.add("\u00a76" + getEnchantmentName(k));
            list.add("Can apply to:");
            list.add(validToolsFor(k));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {

        return stack.getItemDamage() != 0;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return stack.getItemDamage() == 0 ? EnumRarity.common : EnumRarity.rare;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {

        list.add(new ItemStack(this, 1, 0));

        for (int k : enchants.keySet()) {
            list.add(new ItemStack(this, 1, k));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.ENCHANT_TOKEN_NAME);
        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister reg) {

        itemIcon = reg.registerIcon(Reference.MOD_ID + ":EnchantmentToken");
    }

    @Override
    public void addRecipes() {

        ItemStack baseToken = new ItemStack(this, 1, 0), ruby = Gem.getGem(Reference.INDEX_RUBY), emerald = Gem
                .getGem(Reference.INDEX_EMERALD), sapphire = Gem.getGem(Reference.INDEX_SAPPHIRE), topaz = Gem
                .getGem(Reference.INDEX_TOPAZ), abyssite = Gem.getGem(Reference.INDEX_ABYSSITE), purite = Gem
                .getGem(Reference.INDEX_PURITE);

        GameRegistry.addShapedRecipe(new ItemStack(this, 8, 0), "ggg", "rdr", "ggg", 'g', Item.ingotGold, 'r', Item.redstone, 'd',
                Gem.getGem(Reference.INDEX_ABYSS_DIAMOND));
        
        addTokenRecipe(Enchantment.baneOfArthropods.effectId, ruby, Item.spiderEye, baseToken);
        addTokenRecipe(Enchantment.efficiency.effectId, emerald, Item.goldNugget, baseToken);
        addTokenRecipe(Enchantment.fireAspect.effectId, ruby, Item.blazePowder, baseToken);
        addTokenRecipe(Enchantment.fortune.effectId, topaz, Item.diamond, baseToken);
        addTokenRecipe(Enchantment.knockback.effectId, emerald, Item.feather, baseToken);
        addTokenRecipe(Enchantment.looting.effectId, topaz, Item.emerald, baseToken);
        addTokenRecipe(Enchantment.sharpness.effectId, ruby, Item.flint, baseToken);
        addTokenRecipe(Enchantment.silkTouch.effectId, abyssite, Item.emerald, baseToken);
        addTokenRecipe(Enchantment.smite.effectId, ruby, Item.rottenFlesh, baseToken);
        addTokenRecipe(Enchantment.unbreaking.effectId, sapphire, Item.ingotIron, baseToken);
        
        addTokenRecipe(ModEnchantments.iceAspect.effectId, sapphire, Item.snowball, baseToken);
        addTokenRecipe(ModEnchantments.mending.effectId, purite, CraftingMaterial.getStack(Strings.MYSTERY_GOO_NAME), baseToken);
        addTokenRecipe(ModEnchantments.nihil.effectId, purite, Item.potato, baseToken);
    }

    private void addTokenRecipe(int key, ItemStack gem, ItemStack otherMaterial, ItemStack baseToken) {

        GameRegistry.addShapedRecipe(new ItemStack(this, 1, key), "ggg", "mtm", " m ", 'g', gem, 'm', otherMaterial, 't', baseToken);
    }
    
    private void addTokenRecipe(int key, ItemStack gem, Block otherMaterial, ItemStack baseToken) {
        
        addTokenRecipe(key, gem, new ItemStack(otherMaterial), baseToken);
    }
    
    private void addTokenRecipe(int key, ItemStack gem, Item otherMaterial, ItemStack baseToken) {
        
        addTokenRecipe(key, gem, new ItemStack(otherMaterial), baseToken);
    }
}
