package ru.ruzsombi.hardsurvive.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class WorldGen implements IWorldGenerator {
    private static SpiderCaveGenerator spiderCaveGenerator = new SpiderCaveGenerator();

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case -1:
                generateNether(world, rand, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateOverworld(world, rand, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, rand, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    private void generateOverworld(World world, Random random, int i, int k) {
        if (random.nextDouble() <= 0.004D) {
            int x = i + ((-30 + random.nextInt(60)) * 16),
                    y = k + ((-30 + random.nextInt(60)) * 16);
            SpiderCaveWorldSaver.loadFromWorld(world).spiderCaves.add(new SpiderCave(x, y));
//            System.out.println("create spider cave in " + x + " " + y);
        }
        for (SpiderCave spiderCave : SpiderCaveWorldSaver.loadFromWorld().spiderCaves) {
            if (!spiderCave.generate && (spiderCave.chunkX == i && spiderCave.chunkY == k)) {
                spiderCaveGenerator.generate(world, random, spiderCave.x, spiderCave.y, spiderCave.z);
                spiderCave.generate = true;
//                System.out.println("generate spider cave in " + x + " " + y + " " + z);
            }
        }
    }

    private void generateNether(World world, Random random, int i, int k) {

    }

    private void generateEnd(World world, Random random, int x, int z) {
    }
}
