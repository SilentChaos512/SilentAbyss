package silentAbyss.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.world.gen.feature.WorldGenAbyssShrine;
import cpw.mods.fml.common.IWorldGenerator;

public class AbyssWorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.dimensionId) {
            case -1:
                generateNether();
                break;
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd();
                break;
            case Abyss.dimension:
                generateAbyss(world, random, chunkX * 16, chunkZ * 16);
                break;
            default:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    private void generateSurface(World world, Random random, int chunkX, int chunkZ) {

        int i, x, y, z;

        // Basic Abyss gems.
        for (i = 0; i < Config.WORLD_GEM_CLUSTER_COUNT.value; ++i) {
            x = chunkX + random.nextInt(16);
            y = random.nextInt(Config.WORLD_GEM_MAX_HEIGHT.value);
            z = chunkZ + random.nextInt(16);
            new WorldGenMinable(SARegistry.getBlock(Names.ORE).blockID, random.nextInt(4), Config.WORLD_GEM_CLUSTER_SIZE.value,
                    Block.stone.blockID).generate(world, random, x, y, z);
        }

        // Abyssite
        for (i = 0; i < Config.WORLD_ABYSSITE_CLUSTER_COUNT.value; ++i) {
            x = chunkX + random.nextInt(16);
            y = random.nextInt(Config.WORLD_ABYSSITE_MAX_HEIGHT.value);
            z = chunkZ + random.nextInt(16);
            new WorldGenMinable(SARegistry.getBlock(Names.ORE).blockID, EnumGem.ABYSSITE.id, Config.WORLD_ABYSSITE_CLUSTER_SIZE.value,
                    Block.stone.blockID).generate(world, random, x, y, z);
        }

        // Purite
        for (i = 0; i < Config.WORLD_PURITE_CLUSTER_COUNT.value; ++i) {
            x = chunkX + random.nextInt(16);
            y = random.nextInt(Config.WORLD_PURITE_MAX_HEIGHT.value);
            z = chunkZ + random.nextInt(16);
            new WorldGenMinable(SARegistry.getBlock(Names.ORE).blockID, EnumGem.PURITE.id, Config.WORLD_PURITE_CLUSTER_SIZE.value,
                    Block.stone.blockID).generate(world, random, x, y, z);
        }

        // Structures
        // Abyss Shrine
        if (Config.STRUCTURE_SHRINE_RARITY.value > 0 && random.nextInt(Config.STRUCTURE_SHRINE_RARITY.value) == 0) {
            x = chunkX + random.nextInt(16);
            y = random.nextInt(64) + 128;
            z = chunkZ + random.nextInt(16);
            new WorldGenAbyssShrine().generate(world, random, x, y, z);
        }
    }

    private void generateAbyss(World world, Random random, int chunkX, int chunkZ) {

        int i, x, y, z;

        for (i = 0; i < 2; ++i) {
            x = chunkX + random.nextInt(16);
            z = chunkZ + random.nextInt(16);
            y = world.getTopSolidOrLiquidBlock(x, z);
            new WorldGenTrees(false, 3 + random.nextInt(5), 0, 0, false).generate(world, random, x, y, z);
        }
    }

    private void generateNether() {

        // TODO
    }

    private void generateEnd() {

        // TODO
    }
}
