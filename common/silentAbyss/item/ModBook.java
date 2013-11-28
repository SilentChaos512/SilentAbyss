package silentAbyss.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModBook extends ItemWritableBook {

    public static NBTTagCompound[] books = { tagForBook(0) };

    public ModBook(int id) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public boolean getShareTag() {

        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        // Error catcher, in case books were removed (which one has been)
        if (stack.getItemDamage() >= books.length) {
            PlayerHelper.addChatMessage(player, Strings.BAD_BOOK, true);
        }
        
        // Create a temporary written book with the desired NBT.
        // We don't want every instance of mod book to have lots of NBT data in
        // the world save.
        ItemStack bookWithNBT = new ItemStack(Item.writtenBook);
        bookWithNBT.stackTagCompound = books[stack.getItemDamage()];

        player.displayGUIBook(bookWithNBT);
        return stack;
    }

    public static boolean validBookTagPages(NBTTagCompound tags) {

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs tabs, List list) {

        for (int i = 0; i < books.length; ++i) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("book.");
        s.append(stack.getItemDamage());
        return s.toString();
    }

    @Override
    public boolean hasEffect(ItemStack stack) {

        return true;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        if (stack.getItemDamage() < books.length && books[stack.getItemDamage()].hasKey("author")) {
            list.add("by " + books[stack.getItemDamage()].getString("author"));
        } else {
            LogHelper.severe("ModBook with damage value " + stack.getItemDamage() + " is missing its NBT data!");
            list.add("by Faceless Man");
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon("book_written");
    }
    
//    @SideOnly(Side.CLIENT)
//    @Override
//    public boolean requiresMultipleRenderPasses() {
//        
//        return true;
//    }
//    
//    @SideOnly(Side.CLIENT)
//    @Override
//    public Icon getIconFromDamageForRenderPass(int meta, int pass) {
//        
//        // Just testing multi-pass rendering
//        if (pass == 1) {
//            return ModItems.sigilStone.getIconFromDamage(0);
//        }
//        else {
//            return this.getIconFromDamage(0);
//        }
//    }

    public static void addRecipes() {

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.book, 1, 0), new Object[] { Item.book, Strings.ORE_DICT_GEM_BASIC }));
    }

    private static NBTTagCompound tagForBook(int meta) {

        // It would be better to read from a file, but MC is not cooperating >_<

//        File path = new File(Strings.BOOK_LOCATION + "book0.txt");
//        try {
//            LogHelper.debug(Files.readFirstLine(path, StandardCharsets.UTF_8));
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        switch (meta) {
            case 0: {
                NBTTagCompound tags = new NBTTagCompound();
                tags.setString("title", "Chaos and You");
                tags.setString("author", "SilentChaos512");
                NBTTagList pages = new NBTTagList();
                pages.appendTag(new NBTTagString("1",
                        "\u00a7nChaos\u00a7r\n\nChaos is a mysterious force that governs unpredictable (and often dangerous) events (Chaos Events)."));
                pages.appendTag(new NBTTagString(
                        "2",
                        "\u00a7nChaos Level\u00a7r\n\nThe chaos level is the sum of chaos energy in the world. It will slowly rise if below a certain level, and slowly fall if above that level. Also, using certain artifacts can alter the chaos level."));
                pages.appendTag(new NBTTagString(
                        "3",
                        "\u00a7nChaos Factor\u00a7r\n\nThe chaos factor rises exponentially with the chaos level. The higher the chaos factor, the higher the chance of chaos events occuring."));
                pages.appendTag(new NBTTagString(
                        "4",
                        "\u00a7nChaos Events\u00a7r\n\nSometimes chaos will cause strange events, called chaos events. These include meteor showers and strange creatures appearing. Walls and roofs are good defenses against many chaos events."));
                tags.setTag("pages", pages);
                return tags;
            }
            default: {
                return null;
            }
        }
    }

}
