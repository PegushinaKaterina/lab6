package katya.server.util.workingWithClient;

import katya.common.util.SocketInitializer;

import java.net.SocketException;
import java.util.Scanner;

public class GeneratorServerSocketWorker {

    private ServerSocketWorker serverSocketWorker;

    public GeneratorServerSocketWorker(Scanner scanner) {
        setPort(scanner);
    }

    private void setPort(Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            try {
                Integer port = SocketInitializer.askForPort(scanner);
                serverSocketWorker = new ServerSocketWorker();
                if (port != null) {
                    serverSocketWorker.setPort(port);
                }
                isRunning = false;
            } catch (SocketException e) {
                System.out.println("Ошибка при установке порта");
            }
        }
    }

    /*private void askForPort(Scanner scanner) {
        String question = "Вы хотите использовать порт по умолчанию? Введите да/нет";
        System.out.println(question);
        boolean isRunning = true;
        boolean answer = true;
        while (isRunning) {
            try {
                answer = acceptAnswer(scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        isRunning = true;
        while (isRunning) {
            try {
                inputPort(answer, scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (SocketException e) {
                System.out.println("Ошибка при установке порта");
            }
        }
    }


    private void inputPort(boolean answer, Scanner scanner) throws IllegalArgumentException, SocketException {
        try {
            if (answer) {
                serverSocketWorker = new ServerSocketWorker();
            } else {
                System.out.println("Введите порт удаленного хоста (1-65535)");
                String port = scanner.nextLine();
                int portInt = new Validator<Integer>(port)
                        .withCheckingNull(false)
                        .withCheckingFunction(Integer::parseInt, "Номер порта должен быть целым числом")
                        .withCheckingPredicate((arg) -> (int) arg > 0 && (int) arg <= maxPort, "Номер порта должен быть больше 0 и меньше 65536")
                        .getValue();
                serverSocketWorker = new ServerSocketWorker();
                serverSocketWorker.setPort(portInt);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
    }*/

    public ServerSocketWorker getServerSocketWorker() {
        return serverSocketWorker;
    }
}
