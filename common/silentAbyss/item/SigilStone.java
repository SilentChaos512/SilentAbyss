package silentAbyss.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;
import silentAbyss.core.util.LogHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SigilStone extends ItemSA {

    public static final String[] names = { "Empty",

            "Black", // 1
            "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta",
            "Orange", "White", // 16

            "Fireball", // 17
            "Icebolt", "Lightning", "Earthquake", "Healing", "Resistance", "Remedy", "Cloak", "Teleport", // 25
            "Amplify", "Speed" };

    public SigilStone(int par1) {

        super(par1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon("SilentAbyss:Sigil");
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {

        for (int i = 0; i < names.length; ++i) {
            list.add(new ItemStack(itemID, 1, i));
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        int damage = stack.getItemDamage();
        if (damage < names.length) {
            list.add("\u00a73" + names[damage]);
        }

        if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("Y")) {
            NBTTagCompound tags = stack.getTagCompound();
            list.add(LogHelper.coordFromNBT(tags));
        }
    }

    public static void addRecipes() {

        // Base sigil stone.
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 64), "sss", "sas", "sss", 's', Block.stone, 'a',
                AbyssGem.getGem(Reference.INDEX_ABYSSITE));

        // Colors
        for (int i = 0; i < ItemDye.dyeColorNames.length; ++i) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sigilStone, 8, i + 1), true, new Object[] { "sss", "sds",
                    "sss", 's', ModItems.sigilStone, 'd', "dye" + names[i + 1] }));
        }

        // Fireball - Abyss Ruby
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 17), "sss", "sgs", "sss", 's', ModItems.sigilStone, 'g',
                new ItemStack(ModItems.abyssGem, 1, 0));
        // Icebolt - Abyss Sapphire
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 18), "sss", "sgs", "sss", 's', ModItems.sigilStone, 'g',
                new ItemStack(ModItems.abyssGem, 1, 2));
        // Lightning - Abyss Emerald
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 19), "sss", "sgs", "sss", 's', ModItems.sigilStone, 'g',
                new ItemStack(ModItems.abyssGem, 1, 1));
        // Earthquake - Abyss Topaz
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 20), "sss", "sgs", "sss", 's', ModItems.sigilStone, 'g',
                new ItemStack(ModItems.abyssGem, 1, 3));
        // Healing - Glister melon
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 21), "sss", "sms", "sss", 's', ModItems.sigilStone, 'm',
                Item.speckledMelon);
        // Resistance - Diamond
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 22), "sss", "sds", "sss", 's', ModItems.sigilStone, 'd',
                Item.diamond);
        // Remedy - Spider eye
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 23), "sss", "ses", "sss", 's', ModItems.sigilStone, 'e',
                Item.spiderEye);
        // Cloak - Golden carrot
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 24), "sss", "scs", "sss", 's', ModItems.sigilStone, 'c',
                Item.goldenCarrot);
        // Teleport - Ender pearl
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 25), "sss", "ses", "sss", 's', ModItems.sigilStone, 'e',
                Item.enderPearl);
        // Amplify - Purite
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 26), "sss", "sas", "sss", 's', ModItems.sigilStone, 'a',
                AbyssGem.getGem(Reference.INDEX_PURITE));
        // Speed - Gold
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 27), "sss", "sgs", "sss", 's', ModItems.sigilStone, 'g',
                Item.ingotGold);
    }

    public static boolean isColorSigil(String name) {

        for (int i = 1; i < 17; ++i) {
            if (names[i].equals(name)) { return true; }
        }

        return false;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.SIGIL_STONE_NAME);

        return s.toString();
    }
}
