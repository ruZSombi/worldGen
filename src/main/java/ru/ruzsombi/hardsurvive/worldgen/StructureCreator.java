package ru.ruzsombi.hardsurvive.worldgen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class StructureCreator {
    public static void fill3dRect(World world, Block[] blocks, Integer[] percentMap, Random rnd, int x, int y, int z, int with, int height, int deep) {
        for (int i = y; i < deep; i++) {
            for (int j = x; j < with; j++) {
                for (int k = z; k < height; k++) {
                    //x + j, y + i, z + k
                    setBlockInPercentMap(world, blocks, percentMap, rnd, j, i, k);
                }
            }
        }
    }

    public static void fill3dSphere(World world, Block[] blocks, Integer[] percentMap, Random rnd, int x, int y, int z, int r) {
        int height = (y + r) - (y - r);
        for (int i = 0; i < height; i++) {
            double actR = r * Math.sin((Math.PI * i) / height);
            for (double j = 0; j < Math.PI * 2; j += Math.PI / (actR * 4)) {
                for (int k = 0; k < actR; k++) {
                    float nx = x + k * (float) Math.cos(j), nz = z + k * (float) Math.sin(j), ny = (y - r) + i;
                    setBlockInPercentMap(world, blocks, percentMap, rnd, Math.round(nx), Math.round(ny), Math.round(nz));
                }
            }
        }
    }

//    public static void fill3dHalfSphere(World world, Block[] assets.hardsurvive.textures.blocks, Integer[] percentMap, Random rnd, int x, int y, int z, int r, ForgeDirection direction) {
//        int height = (y + r) - (y - r);
//        for (int i = 0; i < height; i++) {
//            double actR = r * Math.sin((Math.PI * i) / height);
//            for (double j = 0; j < Math.PI * 2; j += Math.PI / (actR * 4)) {
//                for (int k = 0; k < actR; k++) {
//                    float nx = x + k * (float) Math.cos(j), nz = z + k * (float) Math.sin(j), ny = (y - r) + i;
//                    setBlockInPercentMap(world, assets.hardsurvive.textures.blocks, percentMap, rnd, Math.round(nx), Math.round(ny), Math.round(nz));
//                }
//            }
//        }
//    }

    public static void setBlockInPercentMap(World world, Block[] blocks, Integer[] percentMap, Random rnd, int x, int y, int z) {
        Arrays.sort(percentMap, Collections.reverseOrder());
        int rndPr = rnd.nextInt(100);
        for (int p = 0; p < percentMap.length; p++) {
            if (rndPr <= percentMap[p]) {
                world.setBlock(x, y, z, blocks[p]);
            }
        }
    }
}
