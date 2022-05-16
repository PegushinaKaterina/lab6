package katya.server;

import katya.server.commands.clientCommands.*;
import katya.server.commands.serverCommands.SaveCommand;
import katya.server.entites.CollectionManager;

import java.io.IOException;

public class ServerWorker {
    private final int maxPort = 65535;
    private ServerSocketWorker serverSocketWorker;
    private final String fileName;
    private final CommandListener serverCommandListener = new CommandListener();
    private CollectionManager collectionManager;
    private CommandManager commandManager;


    public ServerWorker(String fileName) {
        this.fileName = fileName;
    }

    public void startServerWorker() {
        try {
            FileWorker fileWorker = new FileWorker(fileName);
            collectionManager = new CollectionManager(fileWorker);
            commandManager = new CommandManager(
                    new HelpCommand(CommandManager.CLIENT_AVAILABLE_COMMANDS),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager),
                    new UpdateCommand(collectionManager),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new RemoveHeadCommand(collectionManager),
                    new RemoveLoverCommand(collectionManager),
                    new HistoryCommand(CommandManager.commandHistory.getHistory()),
                    new RemoveAllByMinutesOfWaitingCommand(collectionManager),
                    new SumOfMinutesOfWaitingCommand(collectionManager),
                    new CountByImpactSpeedCommand(collectionManager));
            inputPort();
            RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager);
            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ConversionException e) {
            System.out.println(("Error during type conversion"));
            System.exit(1);
        }
    }

    private void inputPort() throws IOException {
        ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getGreenText("Do you want to use a default port? [y/n]"));
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getGreenText("Please enter the remote host port (1-65535)"));
                String port = scanner.nextLine();
                try {
                    int portInt = Integer.parseInt(port);
                    if (portInt > 0 && portInt <= maxPort) {
                        serverSocketWorker = new ServerSocketWorker(portInt);
                    } else {
                        ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("The number did not fall within the limits, repeat the input"));
                        inputPort();
                    }
                } catch (IllegalArgumentException e) {
                    ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("Error processing the number, repeat the input"));
                    inputPort();
                }
            } else if ("y".equals(s)) {
                serverSocketWorker = new ServerSocketWorker();
            } else {
                ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("You entered not valid symbol, try again"));
                inputPort();
            }
        } catch (NoSuchElementException e) {
            ServerConfig.getConsoleTextPrinter().printlnText(TextColoring.getRedText("An invalid character has been entered, forced shutdown!"));
            System.exit(1);
        }
    }
}
