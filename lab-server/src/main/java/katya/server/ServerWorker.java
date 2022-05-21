package katya.server;

import katya.server.entites.CollectionManager;
import katya.server.generators.GeneratorCollectionManager;
import katya.server.util.ConsoleThread;
import katya.server.util.RequestThread;
import katya.server.util.workingWithClient.GeneratorServerSocketWorker;
import katya.server.util.workingWithClient.ServerSocketWorker;
import katya.server.util.workingWithCommand.CommandManager;
import katya.server.util.workingWithCommand.FileWorker;
import katya.server.util.workingWithCommand.ServerCommandListener;

import java.io.IOException;
import java.util.Scanner;

public class ServerWorker {
    private final String fileName;
    private final ServerCommandListener serverCommandListener = new ServerCommandListener();


    public ServerWorker(String fileName) {
        this.fileName = fileName;
    }

    public void startServerWorker() {
        try {
            FileWorker fileWorker = new FileWorker(fileName);
            GeneratorCollectionManager generatorCollectionManager = new GeneratorCollectionManager(fileWorker);
            CollectionManager collectionManager = generatorCollectionManager.getCollectionManager();
            CommandManager commandManager = new CommandManager(collectionManager);
            Scanner scanner = new Scanner(System.in);
            GeneratorServerSocketWorker generatorServerSocketWorker = new GeneratorServerSocketWorker(scanner);
            ServerSocketWorker serverSocketWorker = generatorServerSocketWorker.getServerSocketWorker();
            RequestThread requestThread = new RequestThread(serverSocketWorker, commandManager);
            ConsoleThread consoleThread = new ConsoleThread(serverCommandListener, commandManager, scanner);
            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
