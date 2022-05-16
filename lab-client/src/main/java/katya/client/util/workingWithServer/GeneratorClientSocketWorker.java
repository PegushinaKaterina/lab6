package katya.client.util.workingWithServer;

import katya.common.util.CheckBoolean;
import katya.common.util.Validator;

import java.io.IOException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GeneratorClientSocketWorker {
    private final int maxPort = 65535;
    private ClientSocketWorker clientSocketWorker;

    public GeneratorClientSocketWorker() {
        askForAddress();
        askForPort();
    }

    private boolean inputAnswer() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        boolean answer = true;
        try (scanner) {
            String stringAnswer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            answer = new Validator<Boolean>(stringAnswer)
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckBoolean::checkBoolean, "Ответ должен быть да/нет")
                    .getValue();
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
        return answer;
    }

    private void askForAddress() {
        String question = "Вы хотите использовать адрес сервера по умолчанию? Введите да/нет";
        System.out.println(question);
        boolean isRunning = true;
        while (isRunning) {
            try {
                boolean answer = inputAnswer();
                inputAddress(answer);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                e.getMessage();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println(question);
            }
            /*catch (UnknownHostException e) {
                System.out.println(("Неизвестный адрес, повторите попытку"));
                System.out.println(question);
            } catch (SocketException e) {
                System.out.println(("Ошибка при создании Сокета, повторите попытку"));
                System.out.println(question);
            }
            */
        }
    }

    private void inputAddress(boolean answer) throws IOException {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
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

    private void askForPort() {
        String question = "Вы хотите использовать порт по умолчанию? Введите да/нет";
        System.out.println(question);
        boolean isRunning = true;
        while (isRunning) {
            try {
                boolean answer = inputAnswer();
                inputPort(answer);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }
    }

    private void inputPort(boolean answer) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
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
