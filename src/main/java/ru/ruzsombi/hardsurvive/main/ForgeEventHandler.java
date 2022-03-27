package ru.ruzsombi.hardsurvive.main;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.world.WorldEvent;
import ru.ruzsombi.hardsurvive.worldgen.SpiderCaveWorldSaver;

public class ForgeEventHandler {
    @SubscribeEvent
    public void worldEventLoad(WorldEvent.Load event) {
        if (event.world == MinecraftServer.getServer().getEntityWorld()) {
            SpiderCaveWorldSaver spiderCaveWorldSaver = new SpiderCaveWorldSaver(event.world);
        }
    }
}
