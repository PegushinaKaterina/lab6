package katya.server.util;

import katya.common.util.Request;
import katya.common.util.Response;
import katya.server.util.workingWithClient.ServerSocketWorker;
import katya.server.util.workingWithCommand.CommandManager;

import java.io.IOException;

public class RequestThread extends Thread {
    private final ServerSocketWorker serverSocketWorker;
    private final CommandManager commandManager;

    public RequestThread(ServerSocketWorker serverSocketWorker, CommandManager commandManager) {
        this.serverSocketWorker = serverSocketWorker;
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        while (commandManager.getStatusOfCommandListening()) {
            try {
                Request acceptedRequest = serverSocketWorker.receiveRequest();
                Response responseToSend = commandManager.executeClientCommand(acceptedRequest);
                serverSocketWorker.sendResponse(responseToSend);
            } catch (IOException e) {
                System.out.println("Ошибка при обработке запроса от клиента");
            } catch (ClassNotFoundException e) {
                System.out.println("Пришел некорректный запрос от клиента");
            }
        }
        serverSocketWorker.stopServer();
    }
}
