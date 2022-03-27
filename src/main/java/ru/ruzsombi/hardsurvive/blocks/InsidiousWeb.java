package ru.ruzsombi.hardsurvive.blocks;

import net.minecraft.block.BlockWeb;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.world.World;

import java.util.Random;

public class InsidiousWeb extends BlockWeb {
    public InsidiousWeb() {
        setHardness(6.0F);
        setBlockName("insidiousWeb");
        setBlockTextureName("web");
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(world, x, y, z, meta);
        Random random = new Random();
        if (random.nextDouble() >= 0.3D) {
            EntityCaveSpider spider = new EntityCaveSpider(world);
            spider.setLocationAndAngles(x, y, z, 0, 0);
            world.spawnEntityInWorld(spider);
        }
    }
}
