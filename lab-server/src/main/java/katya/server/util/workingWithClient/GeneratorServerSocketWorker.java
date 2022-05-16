package katya.server.util.workingWithClient;

import katya.common.util.Validator;
import katya.common.util.CheckBoolean;

import java.net.SocketException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GeneratorServerSocketWorker {

    private final int maxPort = 65535;
    private ServerSocketWorker serverSocketWorker;
    public GeneratorServerSocketWorker(){
        askForPort();
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

    private void inputPort(boolean answer) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
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
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    public ServerSocketWorker getServerSocketWorker(){
        return serverSocketWorker;
    }
}
