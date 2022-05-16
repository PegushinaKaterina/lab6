package katya.server;

import katya.server.entites.CollectionManager;
import katya.server.generators.GeneratorCollectionManager;
import katya.server.util.ConsoleThread;
import katya.server.util.workingWithCommand.ServerCommandListener;
import katya.server.util.workingWithCommand.CommandManager;
import katya.server.util.workingWithCommand.FileWorker;

import java.io.IOException;

public class ServerWorker {
    private final String fileName;
    private final ServerCommandListener serverCommandListener = new ServerCommandListener();
    private CollectionManager collectionManager;
    private CommandManager commandManager;


    public ServerWorker(String fileName) {
        this.fileName = fileName;
    }

    public void startServerWorker() {
        try {
            FileWorker fileWorker = new FileWorker(fileName);
            GeneratorCollectionManager generatorCollectionManager = new GeneratorCollectionManager(fileWorker);
            collectionManager = generatorCollectionManager.getCollectionHumanBeing();
            commandManager = new CommandManager(collectionManager);
         //   GeneratorServerSocketWorker generatorServerSocketWorker = new GeneratorServerSocketWorker();
         //   ServerSocketWorker serverSocketWorker = generatorServerSocketWorker.getServerSocketWorker();
         //   RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager);
         //   requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
