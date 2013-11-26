package silentAbyss.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.world.World;

public class CrimsonCreeper extends EntityCreeper {

    public CrimsonCreeper(World world) {

        super(world);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(32.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.3);
    }
}
