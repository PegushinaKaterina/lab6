package katya.client.util.workingWithServer;

import katya.client.util.workingWithCommand.CommandToSend;
import katya.common.util.Request;

import java.io.IOException;
import java.time.LocalTime;

public final class SendRequest {
    private SendRequest() {
    }

    public static boolean sendRequest(CommandToSend command, ClientSocketWorker clientSocketWorker) {
        RequestCreator requestCreator = new RequestCreator();
        try {
            Request request = requestCreator.createRequestOfCommand(command);
            request.setCurrentTime(LocalTime.now());
            request.setClientInfo(clientSocketWorker.getAddress() + " " + clientSocketWorker.getPort());
            clientSocketWorker.sendRequest(request);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Произошла ошибка при отправке запроса, повторите попытку");
        }
        return false;
    }
}
