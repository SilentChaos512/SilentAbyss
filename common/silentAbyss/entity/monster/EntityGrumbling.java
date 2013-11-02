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
import silentAbyss.item.AbyssGem;
import silentAbyss.item.GemShard;
import silentAbyss.lib.Reference;

public class EntityGrumbling extends EntityMob {
    
    public final static int DROP_FEATHER_RARITY = 3;
    public final static int DROP_SHARD_RARITY = 4;
	
	public final static double MAX_HEALTH = 8.0;
	public final static double MOVEMENT_SPEED = 0.3;

    public EntityGrumbling(World world) {
        
        super(world);
        this.setSize(0.6f, 0.7f);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes() {
        
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(MAX_HEALTH);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(MOVEMENT_SPEED);
    }
    
    @Override
    public boolean isAIEnabled() {
        
        return true;
    }
    
    @Override
    protected void dropFewItems(boolean hitByPlayer, int lootingLevel) {

        int i, numItems;
        
        // Feathers
        if (this.rand.nextInt(DROP_FEATHER_RARITY) == 0) {
            numItems = this.rand.nextInt(2 + lootingLevel) + 1;
            for (i = 0; i < numItems; ++i) {
                this.dropItem(Item.feather.itemID, 1);
            }
        }
        // Shards
        if (hitByPlayer && this.rand.nextInt(DROP_SHARD_RARITY) == 0) {
            numItems = this.rand.nextInt(1 + lootingLevel) + 1;
            int gemType = this.rand.nextInt(Reference.GEM_TYPE_COUNT);
            for (i = 0; i < numItems; ++i) {
                this.entityDropItem(GemShard.getGem(gemType), 1);
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
         * Copied from EntitySpider. I think this code makes spiders leap when attacking,
         * but it doesn't seem to do anything here.
         */
    	if (distance > 2.0f && distance < 6.0f) {
    		
    		if (this.onGround) {
    			
    			LogHelper.debug("derp");
    			double d0 = entity.posX - this.posX;
                double d1 = entity.posZ - this.posZ;
                float f2 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
                this.motionX = d0 / (double)f2 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = d1 / (double)f2 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.motionY = 4.0;
    		}
    	}
    	else {
    		
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
