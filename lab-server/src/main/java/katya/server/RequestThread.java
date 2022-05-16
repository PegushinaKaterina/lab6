package katya.server;

import katya.common.Response;
import katya.common.util.Request;

import java.io.IOException;

public class RequestThread extends Thread{
    private final ServerSocketWorker serverSocketWorker;
    private final CommandManager commandManager;

    public RequestThread(ServerSocketWorker serverSocketWorker, CommandManager commandManager) {
        this.serverSocketWorker = serverSocketWorker;
        this.commandManager = commandManager;
    }


    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            try {
                Request acceptedRequest = serverSocketWorker.receiveRequest();
                    Response responseToSend = commandManager.executeClientCommand(acceptedRequest);
                    serverSocketWorker.sendResponse(responseToSend);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        serverSocketWorker.stopServer();
    }
}
