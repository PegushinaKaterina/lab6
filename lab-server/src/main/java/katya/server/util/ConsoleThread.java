package katya.server.util;

import katya.server.util.workingWithCommand.ServerCommandListener;
import katya.server.util.workingWithCommand.CommandManager;

public class ConsoleThread extends Thread {
    private final ServerCommandListener commandListener;
    private final CommandManager commandManager;

    public ConsoleThread(ServerCommandListener commandListener, CommandManager commandManager) {
        this.commandListener = commandListener;
        this.commandManager = commandManager;
    }

    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            System.out.println("11111111");
            String command = commandListener.readCommand();
            commandManager.executeServerCommand(command);
        }
    }
}
