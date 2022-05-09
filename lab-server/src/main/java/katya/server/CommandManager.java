package katya.server;

import katya.common.commands.AbstractCommand;
import katya.server.CommandHistory;
import katya.server.commands.AbstractCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class CommandManager {
    public static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();
    public static CommandHistory commandHistory = new CommandHistory();

    public CommandManager(AbstractCommand helpCommand,
                          AbstractCommand infoCommand,
                          AbstractCommand showCommand,
                          AbstractCommand addCommand,
                          AbstractCommand updateCommand,
                          AbstractCommand removeByIdCommand,
                          AbstractCommand clearCommand,
                          AbstractCommand saveCommand,
                          AbstractCommand executeScriptCommand,
                          AbstractCommand exitCommand,
                          AbstractCommand removeHead,
                          AbstractCommand removeLower,
                          AbstractCommand historyCommand,
                          AbstractCommand removeAllByMinutesOfWaiting,
                          AbstractCommand sumOfMinutesOfWaiting,
                          AbstractCommand countByImpactSpeed
    ) {
        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        AVAILABLE_COMMANDS.put(addCommand.getName(), addCommand);
        AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        AVAILABLE_COMMANDS.put(removeByIdCommand.getName(), removeByIdCommand);
        AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        AVAILABLE_COMMANDS.put(saveCommand.getName(), saveCommand);
        AVAILABLE_COMMANDS.put(executeScriptCommand.getName(), executeScriptCommand);
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(removeHead.getName(), removeHead);
        AVAILABLE_COMMANDS.put(removeLower.getName(), removeLower);
        AVAILABLE_COMMANDS.put(historyCommand.getName(), historyCommand);
        AVAILABLE_COMMANDS.put(removeAllByMinutesOfWaiting.getName(), removeAllByMinutesOfWaiting);
        AVAILABLE_COMMANDS.put(sumOfMinutesOfWaiting.getName(), sumOfMinutesOfWaiting);
        AVAILABLE_COMMANDS.put(countByImpactSpeed.getName(), countByImpactSpeed);

    }

    public void performCommand(String command) {
        command = command.trim();
        String[] commandString = command.split(" ");
        String commandName = commandString[0].toLowerCase(Locale.ROOT);
        String[] commandsArgs = Arrays.copyOfRange(commandString, 1, commandString.length);
        if (AVAILABLE_COMMANDS.containsKey(commandName)) {
            AbstractCommand executingCommand = AVAILABLE_COMMANDS.get(commandName);
            executingCommand.executeCommand(commandsArgs);
            commandHistory.pushCommand(commandName);
        } else {
            System.out.println("Такой команды не существует. Для справки введите команду help");
        }
    }
}
