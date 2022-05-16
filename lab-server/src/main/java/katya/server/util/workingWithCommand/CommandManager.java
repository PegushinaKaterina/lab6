package katya.server.util.workingWithCommand;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;

import java.time.format.DateTimeFormatter;

public class CommandManager {
    AvailableCommands availableCommands;
    private static boolean statusOfCommandListening = true;

    public static CommandHistory commandHistory = new CommandHistory();

    public CommandManager(CollectionManager collectionManager) {
        availableCommands = new AvailableCommands(collectionManager);
    }

    public Response executeClientCommand(Request request) {
        commandHistory.pushCommand(request.getCurrentTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                + " " + request.getClientInfo() + ": " + request.getCommandName());
        return availableCommands.CLIENT_AVAILABLE_COMMANDS.get(request.getCommandName()).executeCommand(request);
    }

    public String executeServerCommand(String commandName) {
        if (availableCommands.SERVER_AVAILABLE_COMMANDS.containsKey(commandName)) {
            return availableCommands.SERVER_AVAILABLE_COMMANDS.get(commandName).executeCommand();
        } else {
            return ("Такой команды не существует, для того, чтобы увидеть список команд введите HELP");
        }
    }
    public static void changeStatus() {
        statusOfCommandListening = !statusOfCommandListening;
    }

    public boolean getStatusOfCommandListening() {
        return statusOfCommandListening;
    }

}
