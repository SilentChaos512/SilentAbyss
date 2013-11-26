package silentAbyss.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import silentAbyss.core.sigil.SigilEffect;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SigilRune extends ItemSA {

    public static final int AMPLIFY_META = 128;
    public static final int SPEED_META = 129;
    public static final String LOCALIZATION_PREFIX = "sigil.silentabyss:";

    @SideOnly(Side.CLIENT)
    Icon iconBlank, iconD, iconM, iconO, iconS, iconT;

    public SigilRune(int id) {

        super(id);
        setMaxStackSize(16);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        int d = stack.getItemDamage();

        EnumChatFormatting format = EnumChatFormatting.AQUA;

        if (d == AMPLIFY_META) {
            list.add(format + LocalizationHelper.getLocalizedString(LOCALIZATION_PREFIX + "Amplify"));
        }
        else if (d == SPEED_META) {
            list.add(format + LocalizationHelper.getLocalizedString(LOCALIZATION_PREFIX + "Speed"));
        }
        else if (d < SigilEffect.all.size()) {
            list.add(format + LocalizationHelper.getLocalizedString(LOCALIZATION_PREFIX + SigilEffect.all.get(d).name));
        }
        else {
            list.add(format + "Invalid meta value!");
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {

        for (SigilEffect effect : SigilEffect.all) {
            if (effect.id != 0) {
                list.add(new ItemStack(this, 1, effect.id));
            }
        }

        list.add(new ItemStack(this, 1, AMPLIFY_META));
        list.add(new ItemStack(this, 1, SPEED_META));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return getUnlocalizedName(Strings.SIGIL_RUNE_NAME);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister reg) {

        String s = Strings.RESOURCE_PREFIX + Strings.SIGIL_RUNE_NAME;
        iconBlank = reg.registerIcon(s);
        iconD = reg.registerIcon(s + "_D");
        iconM = reg.registerIcon(s + "_M");
        iconO = reg.registerIcon(s + "_O");
        iconS = reg.registerIcon(s + "_S");
        iconT = reg.registerIcon(s + "_T");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIconFromDamage(int meta) {

        if (meta == AMPLIFY_META || meta == SPEED_META) {
            return iconM;
        }
        else if (meta > SigilEffect.all.size()) {
            return iconBlank;
        }

        switch (SigilEffect.all.get(meta).rune) {
            case 'D':
                return iconD;
            case 'O':
                return iconO;
            case 'S':
                return iconS;
            case 'T':
                return iconT;
            default:
                return iconBlank;
        }
    }

    @Override
    public void addRecipes() {

        ItemStack shardRuby = EnumGem.RUBY.getShard();
        ItemStack shardEmerald = EnumGem.EMERALD.getShard();
        ItemStack shardSapphire = EnumGem.SAPPHIRE.getShard();
        ItemStack shardTopaz = EnumGem.TOPAZ.getShard();
        ItemStack shardAbyssite = EnumGem.ABYSSITE.getShard();
        ItemStack shardPurite = EnumGem.PURITE.getShard();
        
        addRuneRecipe(SigilEffect.fireball.id, EnumGem.RUBY.getItem(), shardAbyssite);
        addRuneRecipe(SigilEffect.icebolt.id, EnumGem.SAPPHIRE.getItem(), shardAbyssite);
        addRuneRecipe(SigilEffect.lightning.id, EnumGem.EMERALD.getItem(), shardAbyssite);
        addRuneRecipe(SigilEffect.earthquake.id, EnumGem.TOPAZ.getItem(), shardAbyssite);
        addRuneRecipe(SigilEffect.teleport.id, Item.enderPearl, shardAbyssite);
        addRuneRecipe(SigilEffect.healing.id, Item.speckledMelon, shardRuby);
        addRuneRecipe(SigilEffect.regen.id, Item.ghastTear, shardSapphire);
        addRuneRecipe(SigilEffect.remedy.id, Item.spiderEye, shardTopaz);
        addRuneRecipe(SigilEffect.swiftness.id, Item.sugar, shardEmerald);
        addRuneRecipe(SigilEffect.jump.id, Item.feather, shardEmerald);
        addRuneRecipe(SigilEffect.haste.id, Item.pickaxeIron, shardTopaz);
        addRuneRecipe(SigilEffect.strength.id, Item.swordIron, shardRuby);
        addRuneRecipe(SigilEffect.resistance.id, Item.helmetIron, shardSapphire);
        addRuneRecipe(SigilEffect.fireResist.id, Item.blazeRod, shardSapphire);
        addRuneRecipe(SigilEffect.cloak.id, Item.eyeOfEnder, shardPurite);
        addRuneRecipe(AMPLIFY_META, Item.ingotGold, shardPurite);
        addRuneRecipe(SPEED_META, Block.blockRedstone, shardAbyssite);
    }

    private void addRuneRecipe(int meta, Object top, Object other) {

        GameRegistry.addShapedRecipe(new ItemStack(this, 1, meta), " t ", "opo", " o ", 't', top, 'o', other, 'p', Item.paper);
    }
}
