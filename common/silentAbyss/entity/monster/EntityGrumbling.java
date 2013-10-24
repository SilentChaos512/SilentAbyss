package silentAbyss.entity.monster;

import silentAbyss.core.util.LogHelper;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGrumbling extends EntityMob {
	
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
        
        // TODO: EntityGrumbling.dropFewItems
        return;
    }
    
    @Override
    protected void dropRareDrop(int par1) {
        
        // TODO: EntityGrumbling.dropRareDrop
        return;
    }
    
    @Override
    protected void attackEntity(Entity entity, float distance) {
    	
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
}
