package ru.ruzsombi.hardsurvive.main;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.ruzsombi.hardsurvive.worldgen.SpiderCave;
import ru.ruzsombi.hardsurvive.worldgen.SpiderCaveSpawner;
import ru.ruzsombi.hardsurvive.worldgen.SpiderCaveWorldSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FMLCommonEventHandler {
    private static int spawnDelayMin = 70588, spawnDelayMax = 141176; //1000=17sec
    private static long tick = 0, threshold = spawnDelayMin + new Random().nextInt(spawnDelayMax - spawnDelayMin);

    public static void spawnNow() {
        threshold = 0;
    }

    public static List<IChatComponent> info() {
        List<IChatComponent> chatComponents = new ArrayList<IChatComponent>();
        chatComponents.add(new ChatComponentText("current time in world: " + tick + ", spawns spiders in: " + threshold + " (after about " + (threshold - tick) / 17 + " sec)"));
        chatComponents.add(new ChatComponentText("spider caves:"));
        for (SpiderCave spiderCave : SpiderCaveWorldSaver.loadFromWorld().spiderCaves) {
            chatComponents.add(new ChatComponentText(spiderCave + " in " + spiderCave.x + " " + spiderCave.y + " " + spiderCave.z));
            chatComponents.add(new ChatComponentText("  possible players:"));
            for (EntityPlayerMP playerMP : spiderCave.possiblePlayers()) {
                chatComponents.add(new ChatComponentText("    " + playerMP.getDisplayName() + " in " + playerMP.posX + " " + playerMP.posY + " " + playerMP.posZ));
            }
        }
        return chatComponents;
    }

    @SubscribeEvent
    public void spiderAttackEvent(TickEvent.WorldTickEvent event) {
        if (event.world == MinecraftServer.getServer().getEntityWorld() && event.phase == TickEvent.Phase.END) {
            tick++;
            if (tick >= threshold) {
                for (SpiderCave spiderCave : SpiderCaveWorldSaver.loadFromWorld(event.world).spiderCaves) {
                    spiderCave.createSpawnerForAllPossiblePlayers();
                }
                tick = 0;
                threshold = spawnDelayMin + new Random().nextInt(spawnDelayMax - spawnDelayMin);
            }
            SpiderCaveWorldSaver spiderCaveWorldSaver = SpiderCaveWorldSaver.loadFromWorld(event.world);
            for (SpiderCaveSpawner spiderCaveSpawner : spiderCaveWorldSaver.spiderCaveSpawners) {
                spiderCaveSpawner.update();
            }
        }
    }
}
