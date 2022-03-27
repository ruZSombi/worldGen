package ru.ruzsombi.hardsurvive;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import ru.ruzsombi.hardsurvive.main.CommonProxy;
import ru.ruzsombi.hardsurvive.worldgen.WorldGen;

@Mod(modid = HardSurvive.MODID, version = HardSurvive.VERSION)
public class HardSurvive {
    public static final String MODID = "hardsurvive";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static HardSurvive instance;

    @SidedProxy(clientSide = "ru.ruzsombi.hardsurvive.main.ClientProxy",
            serverSide = "ru.ruzsombi.hardsurvive.main.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        proxy.loadServer(event);
    }
}
