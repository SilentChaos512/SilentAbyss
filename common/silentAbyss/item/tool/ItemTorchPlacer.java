package silentAbyss.item.tool;

import silentAbyss.item.ItemSA;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * This item is abandoned for now.
 *
 */
public class ItemTorchPlacer extends ItemSA {

	public ItemTorchPlacer(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxDamage(8 * 64);
		this.setMaxStackSize(1);
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		this.setDamage(par1ItemStack, 8 * 64);
	}
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer)par3Entity;
		if (this.getDamage(par1ItemStack) > 0 && player.inventory.hasItem(Block.torchWood.blockID)) {
			player.inventory.consumeInventoryItem(Block.torchWood.blockID);
			this.setDamage(par1ItemStack, this.getDamage(par1ItemStack) - 1);
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		par3World.setBlock(par4, par5 + 1, par6, Block.torchWood.blockID);
		
//		AbyssLog.print("int par4 = " + par4);
//		AbyssLog.print("int par5 = " + par5);
//		AbyssLog.print("int par6 = " + par6);
//		AbyssLog.print("int par7 = " + par7);
//		AbyssLog.print("float par8 = " + par8);
//		AbyssLog.print("float par9 = " + par9);
//		AbyssLog.print("float par10 = " + par10);
		
		return false;
	}
}
