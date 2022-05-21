package katya.server.util.workingWithClient;

import katya.common.util.Validator;

import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static katya.common.util.AnswerAccepter.acceptAnswer;

public class GeneratorServerSocketWorker {

    private final int maxPort = 65535;
    private ServerSocketWorker serverSocketWorker;

    public GeneratorServerSocketWorker(Scanner scanner) {
        askForPort(scanner);
    }

    private void askForPort(Scanner scanner) {
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
    }

    public ServerSocketWorker getServerSocketWorker() {
        return serverSocketWorker;
    }
}
