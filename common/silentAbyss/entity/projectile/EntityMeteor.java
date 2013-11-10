package silentAbyss.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.ModDamageSources;

public class EntityMeteor extends EntityLargeFireball {

    public EntityMeteor(World world) {

        super(world);
    }

    public EntityMeteor(World world, double x, double y, double z) {

        super(world, x, y, z, 0.0, -1.0, 0.0);
    }

    public EntityMeteor(World world, double x, double y, double z, double par5, double par6, double par7) {

        super(world, x, y, z, par5, par6, par7);
    }
    
    public EntityMeteor(World world, EntityLivingBase entity, double par3, double par5, double par7) {
        
        super(world, entity, par3, par5, par7);
        
        this.posX = entity.posX + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS.value - Config.METEOR_SHOWER_RADIUS.value);
        this.posY = entity.posY + Abyss.rng.nextInt(250) + 50;
        this.posZ = entity.posZ + (Abyss.rng.nextDouble() * 2 * Config.METEOR_SHOWER_RADIUS.value - Config.METEOR_SHOWER_RADIUS.value);
        
        if (world.isRemote) {
            this.setVelocity(0.0, -4.0, 0.0);
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {

        if (!worldObj.isRemote) {
            if (mop.entityHit != null) {
                mop.entityHit.attackEntityFrom(ModDamageSources.meteor, 5.0f);
            }

            worldObj.newExplosion((Entity) null, posX, posY, posZ, 1.0f, false, false);
            setDead();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tags) {

        super.readEntityFromNBT(tags);
        setDead();
    }

}
