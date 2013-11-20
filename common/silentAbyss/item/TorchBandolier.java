package silentAbyss.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TorchBandolier extends ItemSA {

    public final static int SLOTS = 16;
    public final static int MAX_DAMAGE = SLOTS * 64;

    public TorchBandolier(int id) {

        super(id);
        setMaxDamage(MAX_DAMAGE);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        if (stack.getItemDamage() == MAX_DAMAGE) {
            list.add(LocalizationHelper.getMessageText(Strings.TORCH_BANDOLIER_NAME));
        }
        else {
            list.add((new StringBuilder()).append(EnumChatFormatting.YELLOW).append(MAX_DAMAGE - stack.getItemDamage()).append(" / ")
                    .append(MAX_DAMAGE).toString());
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return getUnlocalizedName(Strings.TORCH_BANDOLIER_NAME);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister reg) {

        itemIcon = reg.registerIcon(Strings.RESOURCE_PREFIX + Strings.TORCH_BANDOLIER_NAME);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {

        setDamage(stack, MAX_DAMAGE);
    }

    /**
     * Absorb torches when player sneak-right-clicks.
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (player.isSneaking()) {
            // Absorb a stack of torches.
            ItemStack torches;
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                torches = player.inventory.getStackInSlot(i);
                if (torches != null && torches.itemID == Block.torchWood.blockID) {
                    int damage = stack.getItemDamage();

                    // Decrease damage value of torch bandolier, reduce stack
                    // size of torch stack.
                    if (damage - torches.stackSize < 0) {
                        stack.damageItem(-damage, player);
                        torches.stackSize -= damage;
                    }
                    else {
                        stack.damageItem(-torches.stackSize, player);
                        torches.stackSize = 0;
                    }

                    // If torch stack is empty, get rid of it.
                    if (torches.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(i, null);
                    }
                }
            }
        }

        return stack;
    }

    /**
     * Place a torch, if possible. Mostly the same code Abyss tools use to place blocks.
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {

        if (stack.getItemDamage() == MAX_DAMAGE) {
            return false;
        }

        boolean used = false;

        ItemStack fakeTorchStack = new ItemStack(Block.torchWood);
        Item torch = fakeTorchStack.getItem();

        ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[side];

        int px = x + d.offsetX;
        int py = y + d.offsetY;
        int pz = z + d.offsetZ;

        used = torch.onItemUse(fakeTorchStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        if (used) {
            stack.damageItem(1, player);
        }

        return used;
    }

    @Override
    public void addRecipes() {

        GameRegistry.addShapedRecipe(new ItemStack(this), "lll", "gpg", "lll", 'l', Item.leather, 'g', Item.ingotGold, 'p',
                Gem.getGem(Reference.INDEX_PURITE));
    }
}