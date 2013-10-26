package silentAbyss.entity;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import silentAbyss.Abyss;
import silentAbyss.core.util.LogHelper;
import silentAbyss.entity.monster.EntityGrumbling;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModEntities {
    
    public static void init() {
    	
    	/*
    	 * Projectiles
    	 */
    	EntityRegistry.registerModEntity(EntityProjectileMagic.class, "Magic Projectile", EntityRegistry.findGlobalUniqueEntityId(),
				Abyss.instance, 64, 10, true);
    	EntityRegistry.registerModEntity(EntityMeteor.class, "Abyss Meteor", EntityRegistry.findGlobalUniqueEntityId(),
    			Abyss.instance, 64, 10, true);
        
        ArrayList<BiomeGenBase> temp = new ArrayList<BiomeGenBase>();
        for (BiomeGenBase b : BiomeGenBase.biomeList) {
            if (b != null && b != BiomeGenBase.hell && b != BiomeGenBase.sky) {
                temp.add(b);
            }
        }
        
        BiomeGenBase[] biomes = new BiomeGenBase[temp.size()];
        temp.toArray(biomes);
        
        EntityRegistry.registerGlobalEntityID(EntityGrumbling.class, "Grumbling", EntityRegistry.findGlobalUniqueEntityId(), 0x7A65CF, 0x4DF200);
        EntityRegistry.registerModEntity(EntityGrumbling.class, "Grumbling", 1, Abyss.instance, 80, 3, true);
        //EntityRegistry.addSpawn(EntityGrumbling.class, 5, 4, 8, EnumCreatureType.monster, biomes);
    }
}
