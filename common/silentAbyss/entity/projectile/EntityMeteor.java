package silentAbyss.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import silentAbyss.core.util.ModDamageSources;

public class EntityMeteor extends EntityLargeFireball {

    public EntityMeteor(World world) {

        super(world);
    }

    public EntityMeteor(World world, double x, double y, double z) {

        super(world, x, y, z, 0, -1, 0);
    }

    public EntityMeteor(World world, double x, double y, double z, double par5, double par6, double par7) {

        super(world, x, y, z, par5, par6, par7);
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
