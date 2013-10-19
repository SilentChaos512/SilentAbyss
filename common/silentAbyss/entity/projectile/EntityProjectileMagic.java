package silentAbyss.entity.projectile;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import silentAbyss.item.SigilStone;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityProjectileMagic extends EntityThrowable implements IEntityAdditionalSpawnData {
	
	public int colorR = 255;
	public int colorG = 255;
	public int colorB = 255;
	int type = 0;
	int damage = 10;

	public EntityProjectileMagic(World world) {
		super(world);
	}
	
	public EntityProjectileMagic(World world, EntityLivingBase entityLiving) {
		super(world, entityLiving);
	}
	
	public EntityProjectileMagic(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(MovingObjectPosition movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), this.damage);
			switch (type) {
			case 1:
				movingobjectposition.entityHit.setFire(4);
				break;
			case 2:
				if (movingobjectposition.entityHit instanceof EntityLiving) {
					EntityLiving e = (EntityLiving) movingobjectposition.entityHit;
					e.addPotionEffect(new PotionEffect(2, 200, 1));
				}
				break;
			case 4:
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0f, false);
				break;
			}
		}
		
		for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
		
		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tags) {
		super.writeEntityToNBT(tags);
		
		tags.setByte("colorR", (byte)colorR);
		tags.setByte("colorG", (byte)colorG);
		tags.setByte("colorB", (byte)colorB);
		tags.setByte("type", (byte)type);
		tags.setByte("damage", (byte)damage);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tags) {
		super.readEntityFromNBT(tags);
		
		this.colorR = tags.getByte("colorR");
		this.colorG = tags.getByte("colorG");
		this.colorB = tags.getByte("colorB");
		this.type = tags.getByte("type");
		this.damage = tags.getByte("damage");
	}

	public EntityProjectileMagic setType(int type) {
		this.type = type;
		
		switch (type) {
		case 1:
//			this.motionX *= 0.01f;
//			this.motionY *= 0.01f;
//			this.motionZ *= 0.01f;
			break;
		case 3:
			this.motionX *= 4.0;
			this.motionY *= 4.0;
			this.motionZ *= 4.0;
			break;
		}
		
		return this;
	}
	
	public EntityProjectileMagic setColor(String color) {
		int c = 0xFFFFFF;
		
		for (int i = 1; i < 17; ++i) {
			if (SigilStone.names[i].equals(color)) {
				c = ItemDye.dyeColors[i - 1];
				break;
			}
		}
		
		colorR = (c >> 16) & 255;
		colorG = (c >> 8) & 255;
		colorB = c & 255;
		
//		AbyssLog.print("" + c);
//		AbyssLog.print(AbyssLog.coord(colorR, colorG, colorB));
		
		return this;
	}
	
	public EntityProjectileMagic setDamage(int damage) {
		this.damage = damage;
		return this;
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.003f; // normally 0.03f;
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data) {
		// This method and readSpawnData allow the additional information for
		// the entity to be passed correctly from server to client.
		data.write(this.type);
		data.write(this.damage);
		data.write(this.colorR);
		data.write(this.colorG);
		data.write(this.colorB);
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data) {
		this.type = data.readUnsignedByte();
		this.damage = data.readUnsignedByte();
		this.colorR = data.readUnsignedByte();
		this.colorG = data.readUnsignedByte();
		this.colorB = data.readUnsignedByte();
	}
}
