package silentAbyss.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import silentAbyss.item.ModItems;
import silentAbyss.item.SigilStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySigilInfuser extends TileEntity implements IInventory {

    private ItemStack[] inv;

    public int cookTime = 0;

    public TileEntitySigilInfuser() {

        inv = new ItemStack[5];
    }

    @Override
    public int getSizeInventory() {

        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        if (slot >= inv.length) { return null; }
        return inv[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            if (stack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            } else {
                stack = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {

        // System.out.println("Hello!");
        if (slot >= inv.length) { return; }
        inv[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName() {

        return "silentAbyss.tileentity.TileEntitySigilInfuser";
    }

    @Override
    public boolean isInvNameLocalized() {

        return false;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openChest() {

    }

    @Override
    public void closeChest() {

    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {

        super.readFromNBT(tagCompound);
        NBTTagList nbttaglist = tagCompound.getTagList("Items");
        inv = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound t = (NBTTagCompound) nbttaglist.tagAt(i);
            byte b0 = t.getByte("Slot");

            if (b0 >= 0 && b0 < inv.length) {
                inv[b0] = ItemStack.loadItemStackFromNBT(t);
            }
        }

        cookTime = tagCompound.getShort("CookTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {

        super.writeToNBT(tagCompound);
        tagCompound.setShort("CookTime", (short) cookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < inv.length; ++i) {
            if (inv[i] != null) {
                NBTTagCompound t = new NBTTagCompound();
                t.setByte("Slot", (byte) i);
                inv[i].writeToNBT(t);
                nbttaglist.appendTag(t);
            }
        }

        tagCompound.setTag("Items", nbttaglist);
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgessScaled(int par1) {

        return cookTime * par1 / 200;
    }

    @Override
    public void updateEntity() {

        if (canSmelt()) {
            ++cookTime;

            if (cookTime == 100) {
                cookTime = 0;
                smeltItem();
            }
        } else {
            cookTime = 0;
        }
    }

    private boolean canSmelt() {

        int sigilCount = 0;

        // First count the number of sigil stones in the input slots.
        for (int i = 0; i < 4; ++i) {
            if (inv[i] != null && inv[i].itemID == ModItems.sigilStone.itemID && inv[i].getItemDamage() != 0) {
                ++sigilCount;
            } else if (inv[i] != null) {
                // Not a sigil stone.
                return false;
            }
        }

        // If there are no sigil stones, we can't do anything.
        if (sigilCount == 0) { return false; }

        // If the output slot is empty, we can smelt.
        if (inv[4] == null) { return true; }

        // Otherwise, determine what the resulting sigil would be and compare it
        // to the output slot.
        String s = "", n = "";
        // Determine output tag.
        for (int i = 0; i < 4; ++i) {
            if (inv[i] != null) {
                n = SigilStone.names[inv[i].getItemDamage()];
                if (s == "") {
                    s = n;
                } else {
                    s += " " + n;
                }
            }
        }

        // Compare to output
        NBTTagCompound tags = inv[4].getTagCompound();
        if (tags != null && tags.hasKey("Effects")) { return s.equals(tags.getString("Effects")); }

        return false;
    }

    public void smeltItem() {

        if (canSmelt()) {
            ItemStack stack = getSmeltingResult();

            if (inv[4] == null) {
                inv[4] = stack.copy();
            } else if (inv[4].isItemEqual(stack)) {
                inv[4].stackSize += stack.stackSize;
            }

            for (int i = 0; i < 4; ++i) {
                if (inv[i] != null) {
                    --inv[i].stackSize;

                    if (inv[i].stackSize <= 0) {
                        inv[i] = null;
                    }
                }
            }
        }
    }

    private ItemStack getSmeltingResult() {

        if (!canSmelt()) { return null; }

        String s = "", n = "";
        NBTTagCompound tags;
        // Determine output tag.
        for (int i = 0; i < 4; ++i) {
            if (inv[i] != null && inv[i].itemID == ModItems.sigilStone.itemID) {
                n = SigilStone.names[inv[i].getItemDamage()];

                if (s == "") {
                    s = n;
                } else {
                    s += " " + n;
                }
            }
        }

        ItemStack stack = new ItemStack(ModItems.abyssSigil);
        tags = new NBTTagCompound();
        tags.setString("Effects", s);
        stack.setTagCompound(tags);

        return stack;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return false;
    }
}
