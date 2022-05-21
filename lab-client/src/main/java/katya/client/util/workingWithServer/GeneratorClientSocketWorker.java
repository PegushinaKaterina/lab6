package katya.client.util.workingWithServer;

import katya.common.util.Validator;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static katya.common.util.AnswerAccepter.acceptAnswer;

public class GeneratorClientSocketWorker {
    private final int maxPort = 65535;
    private ClientSocketWorker clientSocketWorker;

    public GeneratorClientSocketWorker(Scanner scanner) {
        askForAddress(scanner);
        askForPort(scanner);
    }


    private void askForAddress(Scanner scanner) {
        String question = "Вы хотите использовать адрес сервера по умолчанию? Введите да/нет";
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
                inputAddress(answer, scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Ошибка при установке адреса");
                System.out.println(question);
            }
        }
    }

    private void inputAddress(boolean answer, Scanner scanner) throws IOException {
        try {
            if (answer) {
                clientSocketWorker = new ClientSocketWorker();
            } else {
                System.out.println(("Введите интернет-адрес сервера"));
                String address = scanner.nextLine();
                clientSocketWorker = new ClientSocketWorker();
                clientSocketWorker.setAddress(address);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
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
            }
        }
    }

    private void inputPort(boolean answer, Scanner scanner) throws IllegalArgumentException {
        try {
            if (!answer) {
                System.out.println("Введите порт удаленного хоста (1-65535)");
                String port = scanner.nextLine();
                int portInt = new Validator<Integer>(port)
                        .withCheckingNull(false)
                        .withCheckingFunction(Integer::parseInt, "Номер порта должен быть целым числом")
                        .withCheckingPredicate((arg) -> (int) arg > 0 && (int) arg <= maxPort, "Номер порта должен быть больше 0 и меньше 65536")
                        .getValue();
                clientSocketWorker.setPort(portInt);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
    }

    public ClientSocketWorker getClientSocketWorker() {
        return clientSocketWorker;
    }
}
