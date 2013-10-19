package silentAbyss.command;

import java.util.List;

import silentAbyss.lib.Commands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class CommandSA extends CommandBase {

	@Override
	public String getCommandName() {
		
		return Commands.COMMAND_SA;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
		
		return true;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List addTabCompletionOptions(ICommandSender commandSender, String[] args) {
		
		// TODO Tab completion for commands
		return null;
	}
	
	@Override
	public void processCommand(ICommandSender commandsender, String[] args) {
		
		if (args.length > 0) {
			String commandName = args[0];
			System.arraycopy(args, 1, args, 0, args.length - 1);
			
			if (commandName.equalsIgnoreCase(Commands.COMMAND_GET_CHAOS)) {
				CommandGetChaos.processCommand(commandsender, args);
			}
			else {
				throw new WrongUsageException(Commands.COMMAND_SA_USAGE, new Object[0]);
			}
		}
		else {
			throw new WrongUsageException(Commands.COMMAND_SA_USAGE, new Object[0]);
		}
	}
	
	@Override
	public String getCommandUsage(ICommandSender commandSender) {
		
		return "/" + Commands.COMMAND_SA_USAGE;
	}
}
