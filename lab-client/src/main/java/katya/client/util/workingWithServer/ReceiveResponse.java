package katya.client.util.workingWithServer;

import katya.common.util.Response;

import java.io.IOException;

public final class ReceiveResponse {
    private ReceiveResponse() {
    }

    public static void receiveResponse(ClientSocketWorker clientSocketWorker) {
        try {
            Response response = null;
            for (int i = 0; i < 50 && response == null; i++) {
                try {
                    System.out.println("Ждем ответ от сервера...");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response = clientSocketWorker.receiveResponse();
            }
            if (response == null) {
                System.out.println("Превышено время ожидания ответа от сервера");
                return;
            }
            System.out.println(response);
        } catch (IOException e) {
            System.out.println(("Произошла ошибка при получении ответа от сервера"));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(("Пришел некорректный ответ от сервера"));
        }
    }
}
