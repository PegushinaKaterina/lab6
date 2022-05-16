package katya.client.util.workingWithServer;

import katya.client.util.workingWithCommand.CommandToSend;

import java.io.IOException;
import java.time.LocalTime;

public class SendRequest {
    public static boolean sendRequest(CommandToSend command, ClientSocketWorker clientSocketWorker) {
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
