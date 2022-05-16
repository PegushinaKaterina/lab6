package katya.server.util.workingWithCommand;

import katya.server.commands.clientCommands.*;
import katya.server.commands.serverCommands.AbstractServerCommand;
import katya.server.commands.serverCommands.ExitCommand;
import katya.server.commands.serverCommands.SaveCommand;
import katya.server.commands.serverCommands.ServerHelpCommand;
import katya.server.entites.CollectionManager;

import java.util.HashMap;

public final class AvailableCommands {
    public static final HashMap<String, AbstractClientCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    public static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();

    public AvailableCommands(CollectionManager collectionManager) {
        AbstractClientCommand clientHelpCommand = new ClientHelpCommand(CLIENT_AVAILABLE_COMMANDS);
        AbstractClientCommand infoCommand =   new InfoCommand(collectionManager);
        AbstractClientCommand showCommand =  new ShowCommand(collectionManager);
        AbstractClientCommand addCommand = new AddCommand(collectionManager);
        AbstractClientCommand updateCommand = new UpdateCommand(collectionManager);
        AbstractClientCommand removeByIdCommand = new RemoveByIdCommand(collectionManager);
        AbstractClientCommand clearCommand = new ClearCommand(collectionManager);
        AbstractClientCommand removeHead = new RemoveHeadCommand(collectionManager);
        AbstractClientCommand removeLower = new RemoveLoverCommand(collectionManager);
        AbstractClientCommand historyCommand = new HistoryCommand(CommandManager.commandHistory.getHistory());
        AbstractClientCommand removeAllByMinutesOfWaiting = new RemoveAllByMinutesOfWaitingCommand(collectionManager);
        AbstractClientCommand sumOfMinutesOfWaiting = new SumOfMinutesOfWaitingCommand(collectionManager);
        AbstractClientCommand countByImpactSpeed = new CountByImpactSpeedCommand(collectionManager);

        CLIENT_AVAILABLE_COMMANDS.put(clientHelpCommand.getName(), clientHelpCommand);
        CLIENT_AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        CLIENT_AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        CLIENT_AVAILABLE_COMMANDS.put(addCommand.getName(), addCommand);
        CLIENT_AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeByIdCommand.getName(), removeByIdCommand);
        CLIENT_AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeHead.getName(), removeHead);
        CLIENT_AVAILABLE_COMMANDS.put(removeLower.getName(), removeLower);
        CLIENT_AVAILABLE_COMMANDS.put(historyCommand.getName(), historyCommand);
        CLIENT_AVAILABLE_COMMANDS.put(removeAllByMinutesOfWaiting.getName(), removeAllByMinutesOfWaiting);
        CLIENT_AVAILABLE_COMMANDS.put(sumOfMinutesOfWaiting.getName(), sumOfMinutesOfWaiting);
        CLIENT_AVAILABLE_COMMANDS.put(countByImpactSpeed.getName(), countByImpactSpeed);

        AbstractServerCommand serverHelpCommand = new ServerHelpCommand(SERVER_AVAILABLE_COMMANDS);
        AbstractServerCommand saveCommand = new SaveCommand(collectionManager);
        AbstractServerCommand exitCommand = new ExitCommand(collectionManager);

        SERVER_AVAILABLE_COMMANDS.put(serverHelpCommand.getName(), serverHelpCommand);
        SERVER_AVAILABLE_COMMANDS.put(saveCommand.getName(), saveCommand);
        SERVER_AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
    }
}
