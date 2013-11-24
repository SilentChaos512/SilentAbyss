package silentAbyss.entity.projectile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemDye;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

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
            movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, getThrower()), damage);
            switch (type) {
                case 1:
                    movingobjectposition.entityHit.setFire(5);
                    break;
                case 2:
                    if (movingobjectposition.entityHit instanceof EntityLiving) {
                        EntityLiving e = (EntityLiving) movingobjectposition.entityHit;
                        e.addPotionEffect(new PotionEffect(2, 200, 1));
                    }
                    break;
                case 4:
                    worldObj.createExplosion(this, posX, posY, posZ, 2.0f, false);
                    break;
            }
        }

        for (int i = 0; i < 8; ++i) {
            worldObj.spawnParticle("smoke", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!worldObj.isRemote) {
            setDead();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tags) {

        super.writeEntityToNBT(tags);

        tags.setByte("colorR", (byte) colorR);
        tags.setByte("colorG", (byte) colorG);
        tags.setByte("colorB", (byte) colorB);
        tags.setByte("type", (byte) type);
        tags.setByte("damage", (byte) damage);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tags) {

        super.readEntityFromNBT(tags);

        colorR = tags.getByte("colorR");
        colorG = tags.getByte("colorG");
        colorB = tags.getByte("colorB");
        type = tags.getByte("type");
        damage = tags.getByte("damage");
    }

    public EntityProjectileMagic setType(int type) {

        this.type = type;

        switch (type) {
            case 1:
                // this.motionX *= 0.01f;
                // this.motionY *= 0.01f;
                // this.motionZ *= 0.01f;
                break;
            case 3:
                motionX *= 4.0;
                motionY *= 4.0;
                motionZ *= 4.0;
                break;
        }

        return this;
    }

    public EntityProjectileMagic setColor(int color) {

        int c = 0xFFFFFF;

        if (color < ItemDye.dyeColors.length) {
            c = ItemDye.dyeColors[color];
        }

        colorR = c >> 16 & 255;
        colorG = c >> 8 & 255;
        colorB = c & 255;

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
        data.write(type);
        data.write(damage);
        data.write(colorR);
        data.write(colorG);
        data.write(colorB);
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data) {

        try {
            type = data.readUnsignedByte();
            damage = data.readUnsignedByte();
            colorR = data.readUnsignedByte();
            colorG = data.readUnsignedByte();
            colorB = data.readUnsignedByte();
        } catch (Exception ex) {
        }
    }
}
