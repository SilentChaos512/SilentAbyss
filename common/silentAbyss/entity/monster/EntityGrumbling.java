package silentAbyss.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import silentAbyss.core.util.LogHelper;
import silentAbyss.item.GemShard;
import silentAbyss.lib.Reference;

public class EntityGrumbling extends EntityMob {

    public final static int DROP_FEATHER_RARITY = 3;
    public final static int DROP_SHARD_RARITY = 4;

    public final static double MAX_HEALTH = 8.0;
    public final static double MOVEMENT_SPEED = 0.3;

    public EntityGrumbling(World world) {

        super(world);
        setSize(0.6f, 0.7f);
        getNavigator().setAvoidsWater(true);
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        tasks.addTask(2, new EntityAIWander(this, 1.0D));
        tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(3, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(MAX_HEALTH);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(MOVEMENT_SPEED);
    }

    @Override
    public boolean isAIEnabled() {

        return true;
    }

    @Override
    protected void dropFewItems(boolean hitByPlayer, int lootingLevel) {

        int i, numItems;

        // Feathers
        if (rand.nextInt(DROP_FEATHER_RARITY) == 0) {
            numItems = rand.nextInt(2 + lootingLevel) + 1;
            for (i = 0; i < numItems; ++i) {
                dropItem(Item.feather.itemID, 1);
            }
        }
        // Shards
        if (hitByPlayer && rand.nextInt(DROP_SHARD_RARITY) == 0) {
            numItems = rand.nextInt(1 + lootingLevel) + 1;
            int gemType = rand.nextInt(Reference.GEM_TYPE_COUNT);
            for (i = 0; i < numItems; ++i) {
                entityDropItem(GemShard.getGem(gemType), 1);
            }
        }
    }

    @Override
    protected void dropRareDrop(int par1) {

        // TODO: EntityGrumbling.dropRareDrop
        return;
    }

    @Override
    protected void attackEntity(Entity entity, float distance) {

        /*
         * Copied from EntitySpider. I think this code makes spiders leap when
         * attacking, but it doesn't seem to do anything here.
         */
        if (distance > 2.0f && distance < 6.0f) {

            if (onGround) {

                LogHelper.debug("derp");
                double d0 = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                motionX = d0 / f2 * 0.5D * 0.800000011920929D + motionX * 0.20000000298023224D;
                motionZ = d1 / f2 * 0.5D * 0.800000011920929D + motionZ * 0.20000000298023224D;
                motionY = 4.0;
            }
        } else {

            super.attackEntity(entity, distance);
        }
    }

    @Override
    protected String getLivingSound() {

        return "mob.villager.idle";
    }

    @Override
    protected String getHurtSound() {

        return "mob.villager.hit";
    }

    @Override
    protected String getDeathSound() {

        return "mob.villager.death";
    }
}
