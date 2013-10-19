package silentAbyss.command;

import silentAbyss.core.handlers.ChaosHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatMessageComponent;

public class CommandGetChaos {
	
	public static void processCommand(ICommandSender commandSender, String[] args) {
		
	    commandSender.sendChatToPlayer(ChatMessageComponent.createFromText("Current chaos: " + ChaosHandler.getChaos()));
	}

}
