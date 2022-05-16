package katya.server.util.workingWithCommand;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.commands.clientCommands.AbstractClientCommand;
import katya.server.commands.serverCommands.AbstractServerCommand;
import katya.server.util.workingWithCommand.CommandHistory;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CommandManager {
    public static final HashMap<String, AbstractClientCommand> CLIENT_AVAILABLE_COMMANDS = new HashMap<>();
    public static final HashMap<String, AbstractServerCommand> SERVER_AVAILABLE_COMMANDS = new HashMap<>();;
    private static boolean statusOfCommandListening = true;

    public static CommandHistory commandHistory = new CommandHistory();

    public CommandManager(AbstractClientCommand helpCommand,
                          AbstractClientCommand infoCommand,
                          AbstractClientCommand showCommand,
                          AbstractClientCommand addCommand,
                          AbstractClientCommand updateCommand,
                          AbstractClientCommand removeByIdCommand,
                          AbstractClientCommand clearCommand,
                          AbstractClientCommand removeHead,
                          AbstractClientCommand removeLower,
                          AbstractClientCommand historyCommand,
                          AbstractClientCommand removeAllByMinutesOfWaiting,
                          AbstractClientCommand sumOfMinutesOfWaiting,
                          AbstractClientCommand countByImpactSpeed
    ) {
        CLIENT_AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
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
    }

    public Response executeClientCommand(Request request) {
        commandHistory.pushCommand(request.getCurrentTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                + " " + request.getClientInfo() + ": " + request.getCommandName());
        return CLIENT_AVAILABLE_COMMANDS.get(request.getCommandName()).executeCommand(request);
    }

    public String executeServerCommand(String commandName) {
        if (SERVER_AVAILABLE_COMMANDS.containsKey(commandName)) {
            return SERVER_AVAILABLE_COMMANDS.get(commandName).executeCommand();
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
