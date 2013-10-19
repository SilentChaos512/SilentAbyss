package silentAbyss.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAbyssTeleporter extends TileEntity {
	public int destX, destY, destZ, destD;

	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("destX", destX);
		par1.setInteger("destY", destY);
		par1.setInteger("destZ", destZ);
		par1.setInteger("destD", destD);
	}
	
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		this.destX = par1.getInteger("destX");
		this.destY = par1.getInteger("destY");
		this.destZ = par1.getInteger("destZ");
		this.destD = par1.getInteger("destD");
	}
}
