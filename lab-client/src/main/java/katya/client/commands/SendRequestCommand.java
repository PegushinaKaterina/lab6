package katya.client.commands;

import katya.client.ClientSocketWorker;
import katya.client.util.CommandToSend;
import katya.client.util.RequestCreator;

import java.io.IOException;
import java.time.LocalTime;

public class SendRequestCommand {
    public static boolean executeCommand(CommandToSend command, ClientSocketWorker clientSocketWorker) {
        RequestCreator requestCreator = new RequestCreator();
        try {
            katya.common.util.Request request = requestCreator.createRequestOfCommand(command);
            request.setCurrentTime(LocalTime.now());
            request.setClientInfo(clientSocketWorker.getAddress() + " " + clientSocketWorker.getPort());
            clientSocketWorker.sendRequest(request);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Произошла ошибка при сериализации запроса, повторите попытку");
        }
        return false;
    }
}
