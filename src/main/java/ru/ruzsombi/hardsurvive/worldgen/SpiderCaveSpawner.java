package ru.ruzsombi.hardsurvive.worldgen;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpiderCaveSpawner {
    private EntityPlayerMP entityPlayerMP;
    private int tick = 0, spawnTime, spawnRate, spawnTick = 0, playSoundTick = 0, playSoundTime, playSoundRate;
    private List<String> entityMobs = new ArrayList<String>();

    public SpiderCaveSpawner() {
    }

    public SpiderCaveSpawner(EntityPlayerMP entityPlayerMP, int spawnTime, int spawnRate) {
        this.entityPlayerMP = entityPlayerMP;
        this.spawnTime = spawnTime;
        this.spawnRate = spawnRate;

        Random random = new Random();
        playSoundTime = 50 + random.nextInt(50);
        playSoundRate = 10 + random.nextInt(10);

        SpiderCaveWorldSaver.loadFromWorld().spiderCaveSpawners.add(this);
        possibleMobs();
    }

    public void possibleMobs() {
        entityMobs.add("RedSpider");
    }

    public void spawnToPlayer() {
        Random random = new Random();
        int randomMob = random.nextInt(entityMobs.size());
        EntityMob mob = (EntityMob) EntityList.createEntityByName(entityMobs.get(randomMob), entityPlayerMP.worldObj);
        double spawnRange = 50 + random.nextInt(50);
        double x = entityPlayerMP.posX + spawnRange * Math.cos(random.nextDouble() * (Math.PI * 2)),
                z = entityPlayerMP.posZ + spawnRange * Math.sin(random.nextDouble() * (Math.PI * 2)),
                y = entityPlayerMP.worldObj.getHeightValue((int) x, (int) z) + 1;
        mob.setLocationAndAngles(x, y, z, 0, 0);
        entityPlayerMP.worldObj.spawnEntityInWorld(mob);
//        mob.moveEntity(entityPlayerMP.posX, entityPlayerMP.posY, entityPlayerMP.posZ);
    }

    public void update() {
        if (tick < playSoundTime) {
            playSoundTick++;
            if (playSoundTick >= playSoundRate) {
                Random random = new Random();
//                String randomSound = sounds[random.nextInt(sounds.length)];
//                entityPlayerMP.playSound(randomSound, 1, 1);
                playSoundTick = 0;
            }
        }
        if (tick < spawnTime) {
            spawnTick++;
            if (spawnTick >= spawnRate) {
                spawnToPlayer();
                spawnTick = 0;
            }
        }
        if (tick >= spawnTime) {
            SpiderCaveWorldSaver.loadFromWorld().spiderCaveSpawners.remove(this);
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        entityPlayerMP.readFromNBT(nbtTagCompound);
        tick = nbtTagCompound.getInteger("tick");
        spawnTime = nbtTagCompound.getInteger("spawnTime");
        spawnRate = nbtTagCompound.getInteger("spawnRate");
        spawnTick = nbtTagCompound.getInteger("spawnTick");
        playSoundTick = nbtTagCompound.getInteger("playSoundTick");
        playSoundTime = nbtTagCompound.getInteger("playSoundTime");
        playSoundRate = nbtTagCompound.getInteger("playSoundRate");

        NBTTagList nbtTagList = nbtTagCompound.getTagList("mobs", 10);
        for (int i = 0; i < nbtTagList.tagCount(); i++) {
            entityMobs.add(nbtTagList.getCompoundTagAt(i).getString("mob"));
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("tick", tick);
        nbtTagCompound.setInteger("spawnTime", spawnTime);
        nbtTagCompound.setInteger("spawnRate", spawnRate);
        nbtTagCompound.setInteger("spawnTick", spawnTick);
        nbtTagCompound.setInteger("playSoundTick", playSoundTick);
        nbtTagCompound.setInteger("playSoundTime", playSoundTime);
        nbtTagCompound.setInteger("playSoundRate", playSoundRate);

        NBTTagList nbtTagList = new NBTTagList();
        for (String s : entityMobs) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("mob", s);
            nbtTagList.appendTag(compound);
        }
        nbtTagCompound.setTag("mobs", nbtTagList);
    }
}
