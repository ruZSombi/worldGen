package ru.ruzsombi.hardsurvive.main;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class DebugCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "spiderDebug";
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "commands.give.usage";
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        if (p_71515_2_[0].equals("spawn")) {
            FMLCommonEventHandler.spawnNow();
        }
        if (p_71515_2_[0].equals("info")) {
            for (IChatComponent chatComponent : FMLCommonEventHandler.info()) {
                p_71515_1_.addChatMessage(chatComponent);
            }
        }
    }
}
