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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModEntities {
    
    private static int startEntityId = 300;

    public static void init() {
        
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
