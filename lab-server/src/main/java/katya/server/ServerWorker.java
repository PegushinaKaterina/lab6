package katya.server;

import katya.server.commands.clientCommands.*;
import katya.server.entites.CollectionManager;
import katya.server.util.ConsoleThread;
import katya.server.util.RequestThread;
import katya.server.util.workingWithClient.GeneratorServerSocketWorker;
import katya.server.util.workingWithClient.ServerSocketWorker;
import katya.server.util.workingWithCommand.CommandListener;
import katya.server.util.workingWithCommand.CommandManager;
import katya.server.util.workingWithCommand.FileWorker;

import java.io.IOException;

public class ServerWorker {
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
            GeneratorServerSocketWorker generatorServerSocketWorker = new GeneratorServerSocketWorker();
            ServerSocketWorker serverSocketWorker = generatorServerSocketWorker.getServerSocketWorker();
            RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager);
            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
