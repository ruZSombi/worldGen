package ru.ruzsombi.hardsurvive.worldgen;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpiderCave {
    public int x, y, z, chunkX, chunkY;
    public boolean active = true, generate = false;

    public SpiderCave() {
    }

    public SpiderCave(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        Random random = new Random();
        x = chunkX + random.nextInt(16);
        z = chunkY + random.nextInt(16);
        y = 20 + random.nextInt(30);
    }

    public List<EntityPlayerMP> possiblePlayers() {
        List<EntityPlayerMP> playerMPList = new ArrayList<EntityPlayerMP>();
        for (Object player : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            if (player instanceof EntityPlayerMP) {
                EntityPlayerMP playerMP = (EntityPlayerMP) player;
                double x = playerMP.posX, z = playerMP.posZ;
                double distance = Math.sqrt(Math.pow(this.x - x, 2) + (Math.pow(this.y - z, 2)));
                if (distance <= 500) {
                    playerMPList.add(playerMP);
                }
            }
        }
        return playerMPList;
    }

    public void createSpawnerForAllPossiblePlayers() {
        List<EntityPlayerMP> playerMPList = possiblePlayers();
        Random random = new Random();
        for (EntityPlayerMP playerMP : playerMPList) {
            SpiderCaveSpawner spiderCaveSpawner = new SpiderCaveSpawner(playerMP, 1000 + random.nextInt(1000), 100 + random.nextInt(100));
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        x = nbtTagCompound.getInteger("x");
        y = nbtTagCompound.getInteger("y");
        z = nbtTagCompound.getInteger("z");
        chunkX = nbtTagCompound.getInteger("chunkX");
        chunkY = nbtTagCompound.getInteger("chunkY");
        active = nbtTagCompound.getBoolean("active");
        generate = nbtTagCompound.getBoolean("generate");
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("x", x);
        nbtTagCompound.setInteger("y", y);
        nbtTagCompound.setInteger("z", z);
        nbtTagCompound.setInteger("chunkX", chunkX);
        nbtTagCompound.setInteger("chunkY", chunkY);
        nbtTagCompound.setBoolean("active", active);
        nbtTagCompound.setBoolean("generate", generate);
    }
}
