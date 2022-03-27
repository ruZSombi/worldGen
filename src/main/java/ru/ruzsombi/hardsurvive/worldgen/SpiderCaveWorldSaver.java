package ru.ruzsombi.hardsurvive.worldgen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

import java.util.ArrayList;
import java.util.List;

public class SpiderCaveWorldSaver extends WorldSavedData {
    public final List<SpiderCave> spiderCaves = new ArrayList<SpiderCave>();
    public final List<SpiderCaveSpawner> spiderCaveSpawners = new ArrayList<SpiderCaveSpawner>();

    public World world;

    public SpiderCaveWorldSaver(World world) {
        super("spiderCaves");
        this.world = world;
        this.world.mapStorage.setData(this.mapName, this);
    }

    public SpiderCaveWorldSaver() {
        super("spiderCaves");
        this.world = MinecraftServer.getServer().getEntityWorld();
        this.world.mapStorage.setData(this.mapName, this);
    }

    public static SpiderCaveWorldSaver loadFromWorld(World world) {
        return (SpiderCaveWorldSaver) world.mapStorage.loadData(SpiderCaveWorldSaver.class, "spiderCaves");
    }

    public static SpiderCaveWorldSaver loadFromWorld() {
        return (SpiderCaveWorldSaver) MinecraftServer.getServer().getEntityWorld().mapStorage.loadData(SpiderCaveWorldSaver.class, "spiderCaves");
    }

    @Override
    public void readFromNBT(NBTTagCompound p_76184_1_) {
        NBTTagList spiderCavesNbtTagList = p_76184_1_.getTagList("spiderCaves", 10);
        for (int i = 0; i < spiderCavesNbtTagList.tagCount(); i++) {
            SpiderCave spiderCave = new SpiderCave();
            spiderCave.readFromNBT(spiderCavesNbtTagList.getCompoundTagAt(i));
            spiderCaves.add(spiderCave);
        }

        NBTTagList spiderCavesSpawnerNbtTagList = p_76184_1_.getTagList("spiderCavesSpawner", 10);
        for (int i = 0; i < spiderCavesSpawnerNbtTagList.tagCount(); i++) {
            SpiderCaveSpawner spiderCaveSpawner = new SpiderCaveSpawner();
            spiderCaveSpawner.readFromNBT(spiderCavesSpawnerNbtTagList.getCompoundTagAt(i));
            spiderCaveSpawners.add(spiderCaveSpawner);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound p_76187_1_) {
        NBTTagList spiderCavesNbtTagList = new NBTTagList();
        for (SpiderCave spiderCave : spiderCaves) {
            NBTTagCompound compound = new NBTTagCompound();
            spiderCave.writeToNBT(compound);
            spiderCavesNbtTagList.appendTag(compound);
        }
        p_76187_1_.setTag("spiderCaves", spiderCavesNbtTagList);

        NBTTagList spiderCavesSpawnerNbtTagList = new NBTTagList();
        for (SpiderCaveSpawner spiderCaveSpawner : spiderCaveSpawners) {
            NBTTagCompound compound = new NBTTagCompound();
            spiderCaveSpawner.writeToNBT(compound);
            spiderCavesSpawnerNbtTagList.appendTag(compound);
        }
        p_76187_1_.setTag("spiderCavesSpawner", spiderCavesSpawnerNbtTagList);
    }
}
