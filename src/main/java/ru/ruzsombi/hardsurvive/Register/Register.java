package ru.ruzsombi.hardsurvive.Register;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import ru.ruzsombi.hardsurvive.HardSurvive;
import ru.ruzsombi.hardsurvive.main.DebugCommand;
import ru.ruzsombi.hardsurvive.mobs.RedSpider;

public class Register {
    public static void registerMobs() {
        Register.registerEntity(RedSpider.class, "RedSpider", 0x00FFFF, 0x00008B);
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(ModBlocks.spiderEgg, "spiderEgg");
        GameRegistry.registerBlock(ModBlocks.insidiousWeb, "insidiousWeb");
    }

    public static void registerRenders() {
//        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(GameRegistry.findBlock(HardSurvive.MODID, "spiderEgg")), );
    }

    public static void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new DebugCommand());
    }

    public static void registerEntity(Class<? extends Entity> entityClass, String name, int primaryColor, int secondaryColor) {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();
        long seed = name.hashCode();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, name, entityID, HardSurvive.instance, 64, 1, true);
        EntityList.entityEggs.put(entityID, new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
    }
}
