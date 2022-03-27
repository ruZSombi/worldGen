package ru.ruzsombi.hardsurvive.worldgen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import ru.ruzsombi.hardsurvive.Register.ModBlocks;

import java.util.Random;

public class SpiderCaveGenerator extends WorldGenerator {
    private int size = 10;

    public int getSize() {
        return size;
    }

    @Override
    public boolean generate(World w, Random rnd, int x, int y, int z) {
        StructureCreator.fill3dSphere(w, new Block[]{Blocks.dirt}, new Integer[]{100}, rnd, x, y, z, size);
        StructureCreator.fill3dSphere(w, new Block[]{Blocks.air, ModBlocks.insidiousWeb}, new Integer[]{100, 70}, rnd, x, y, z, size - 2);
        w.setBlock(x, y, z, ModBlocks.spiderEgg);
        return true;
    }
}
