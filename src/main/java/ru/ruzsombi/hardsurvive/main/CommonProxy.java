package ru.ruzsombi.hardsurvive.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import ru.ruzsombi.hardsurvive.Register.Register;
import ru.ruzsombi.hardsurvive.worldgen.WorldGen;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        Register.registerMobs();
        Register.registerBlocks();
        GameRegistry.registerWorldGenerator(new WorldGen(), 0);
        FMLCommonHandler.instance().bus().register(new FMLCommonEventHandler());
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    public void registerRenderers() {
    }

    public void loadServer(FMLServerStartingEvent event) {
        Register.registerCommands(event);
    }
}
