package katya.client.commands;

import katya.client.ClientSocketWorker;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class ReceiveResponseCommand {
    public static void executeCommand(ClientSocketWorker clientSocketWorker) {
        try {
            katya.common.Response response = clientSocketWorker.receiveResponse();
            System.out.println(response.toString());
        } catch (SocketTimeoutException e) {
            System.out.println(("The waiting time for a response from the server has been exceeded, try again later"));
        } catch (IOException e) {
            System.out.println(("An error occurred while receiving a response from the server"));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(("The response came damaged"));
        }
    }
}
