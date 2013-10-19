package silentAbyss.tool;

import java.util.List;

import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.NBTHelper;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ItemSA;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class TeleporterLinker extends ItemSA {

	private Icon iconIdle, iconActive;
	
	public TeleporterLinker(int par1) {
		
		super(par1);
		
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setUnlocalizedName(Strings.TELEPORTER_LINKER_NAME);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {
        
        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.TELEPORTER_LINKER_NAME);
        return s.toString();
    }
	
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		
		resetTagCompound(stack);
	}
	
	public static void resetTagCompound(ItemStack stack) {
		
		if (stack.itemID != ModItems.teleporterLinker.itemID) {
			LogHelper.warning("Attempted to reset Teleporter Linker tag compound on a different item type!");
			return;
		}
		
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.stackTagCompound.setInteger("State", 0);
		NBTHelper.setXYZD(stack.stackTagCompound, 0, 0, 0, 0);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		
        if (stack.stackTagCompound == null) {
        	resetTagCompound(stack);
        }
        
        NBTTagCompound tags = stack.getTagCompound();
        if (tags.getInteger("State") == 0) {
        	list.add("Not linked");
        }
        else {
        	list.add("Linked to " + LogHelper.coord(tags.getInteger("X"), tags.getInteger("Y"), tags.getInteger("Z")));
        }
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return (par1 == 0 ? iconIdle : iconActive);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconIdle = iconRegister.registerIcon("SilentAbyss:TeleporterLinkerIdle");
		iconActive = iconRegister.registerIcon("SilentAbyss:TeleporterLinkerActive");

		this.itemIcon = iconActive;
	}
}
