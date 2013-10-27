package silentAbyss.command;

import silentAbyss.core.handlers.ChaosHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;

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
