package silentAbyss.world.biome;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGen1 extends BiomeGenBase {

    public final Material blockMaterial;

    public BiomeGen1(int par1) {

        super(par1);
        this.blockMaterial = Material.water;
        this.minHeight = 0.1f;
        this.maxHeight = 0.6f;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.setBiomeName("Abyss1");
        this.waterColorMultiplier = 0x2A1A98;
        setTemperatureRainfall(1.0F, 0.9F);
    }
}
