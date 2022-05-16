package katya.server;

public class ConsoleThread extends Thread{
    private final CommandListener CommandListener;
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
