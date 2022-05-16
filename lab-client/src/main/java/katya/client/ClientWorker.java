package katya.client;

import katya.client.util.workingWithCommand.CommandListener;
import katya.client.util.workingWithCommand.CommandManager;
import katya.client.util.workingWithServer.ClientSocketWorker;
import katya.client.util.workingWithServer.GeneratorClientSocketWorker;
import katya.client.util.workingWithServer.RequestCreator;

import java.util.Scanner;

public class ClientWorker {

    private final CommandListener commandListener = new CommandListener();
    private final RequestCreator requestCreator = new RequestCreator();

    private ClientSocketWorker clientSocketWorker;
    private boolean statusOfCommandListening = true;

    public void startClientWorker() {
        GeneratorClientSocketWorker generatorClientSocketWorker = new GeneratorClientSocketWorker();
        clientSocketWorker = generatorClientSocketWorker.getClientSocketWorker();
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
            CommandManager.performCommandd(scanner, clientSocketWorker);
        }
    }
}
