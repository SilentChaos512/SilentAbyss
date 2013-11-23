package silentAbyss.entity;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import silentAbyss.Abyss;
import silentAbyss.entity.monster.CrimsonCreeper;
import silentAbyss.entity.monster.EntityGrumbling;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {

    public static void init() {

        /*
         * Projectiles
         */
        // EntityRegistry.registerModEntity(EntityProjectileMagic.class,
        // "Magic Projectile", EntityRegistry.findGlobalUniqueEntityId(),
        // Abyss.instance, 64, 10, true);
        // EntityRegistry.registerModEntity(EntityMeteor.class, "Abyss Meteor",
        // EntityRegistry.findGlobalUniqueEntityId(), Abyss.instance, 64,
        // 10, true);

        int id = -1;

        EntityRegistry.registerGlobalEntityID(EntityProjectileMagic.class, Strings.ENTITY_PROJECTILE_MAGIC_NAME,
                EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityProjectileMagic.class, Strings.ENTITY_PROJECTILE_MAGIC_NAME, ++id, Abyss.instance, 64, 10,
                true);

        EntityRegistry.registerGlobalEntityID(EntityMeteor.class, Strings.ENTITY_METEOR_NAME, EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityMeteor.class, Strings.ENTITY_METEOR_NAME, ++id, Abyss.instance, 64, 10, true);

        ArrayList<BiomeGenBase> temp = new ArrayList<BiomeGenBase>();
        for (BiomeGenBase b : BiomeGenBase.biomeList) {
            if (b != null && b != BiomeGenBase.hell && b != BiomeGenBase.sky) {
                temp.add(b);
            }
        }

        BiomeGenBase[] biomes = new BiomeGenBase[temp.size()];
        temp.toArray(biomes);

        id = 15;

        registerMob(EntityGrumbling.class, Strings.ENTITY_GRUMBLING_NAME, ++id, false, EnumCreatureType.monster, 5, 4, 8, biomes, 0x7A65CF, 0x4DF200);
        registerMob(CrimsonCreeper.class, Strings.ENTITY_CRIMSON_CREEPER_NAME, ++id, true, EnumCreatureType.monster, 5, 1, 4, biomes, 0xFE0000, 0x030303);
        
//        EntityRegistry.registerGlobalEntityID(EntityGrumbling.class, Strings.ENTITY_GRUMBLING_NAME,
//                EntityRegistry.findGlobalUniqueEntityId(), 0x7A65CF, 0x4DF200);
//        EntityRegistry.registerModEntity(EntityGrumbling.class, Strings.ENTITY_GRUMBLING_NAME, ++id, Abyss.instance, 80, 3, true);
//        EntityRegistry.addSpawn(EntityGrumbling.class, 5, 4, 8, EnumCreatureType.monster, biomes);
    }

    private static void registerMob(Class<? extends EntityLiving> mobClass, String name, int modID, boolean addSpawn,
            EnumCreatureType creatureType, int spawnWeight, int spawnMin, int spawnMax, BiomeGenBase[] biomes, int foregroundColor,
            int backgroundColor) {

        EntityRegistry.registerGlobalEntityID(mobClass, name, EntityRegistry.findGlobalUniqueEntityId(), foregroundColor, backgroundColor);
        EntityRegistry.registerModEntity(mobClass, name, modID, Abyss.instance, 80, 3, true);

        if (addSpawn) {
            EntityRegistry.addSpawn(mobClass, spawnWeight, spawnMin, spawnMax, creatureType, biomes);
        }
    }
}
