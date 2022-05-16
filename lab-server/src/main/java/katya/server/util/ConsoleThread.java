package katya.server.util;

import katya.server.util.workingWithCommand.CommandListener;
import katya.server.util.workingWithCommand.CommandManager;

public class ConsoleThread extends Thread{
    private final katya.server.util.workingWithCommand.CommandListener CommandListener;
    private final CommandManager commandManager;

    public ConsoleThread(CommandListener CommandListener, CommandManager commandManager) {
        this.CommandListener = CommandListener;
        this.commandManager = commandManager;
    }

    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            String command = CommandListener.readCommand();
            commandManager.executeServerCommand(command);
        }
    }
}
