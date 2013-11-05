package silentAbyss.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;
import silentAbyss.core.handlers.ChaosHandler;

public class CommandGetChaos {

    public static void processCommand(ICommandSender commandSender, String[] args) {

        StringBuilder s = new StringBuilder();
        s.append("Current chaos: ");
        s.append(ChaosHandler.getChaos());
        s.append(" (Chaos factor: ");
        s.append(String.format("%.3f", ChaosHandler.getChaosFactor()));
        s.append(")");
        commandSender.sendChatToPlayer(ChatMessageComponent.createFromText(s.toString()));
    }

}
