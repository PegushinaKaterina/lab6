package katya.common.util;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class SocketInitializer {
    private SocketInitializer() {
    }
    private final static int MAX_PORT = 65535;

    public static boolean acceptAnswer(Scanner scanner) throws IllegalArgumentException {
        boolean answer = true;
        try {
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
    public static boolean ask(String question, Scanner scanner){
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
        return answer;
    }

    public static String askForAddress(Scanner scanner){
        String question = "Вы хотите использовать адрес сервера по умолчанию? Введите да/нет";
        boolean answer = ask(question, scanner);
        boolean isRunning = true;
        String address = null;
        isRunning = true;
        while (isRunning) {
            try {
                address = inputAddress(answer, scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return address;
    }

    private static String inputAddress(boolean answer, Scanner scanner){
        String address = null;
        try {
            if (!answer) {
                System.out.println(("Введите интернет-адрес сервера"));
                address = scanner.nextLine();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
        return address;
    }

    public static Integer askForPort(Scanner scanner){
        String question = "Вы хотите использовать порт по умолчанию? Введите да/нет";
        boolean answer = ask(question, scanner);
        boolean isRunning = true;
        Integer port = null;
        while (isRunning) {
            try {
                port = inputPort(answer, scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return port;
    }

    private static Integer inputPort(boolean answer, Scanner scanner) throws IllegalArgumentException {
        Integer portInt = null;
        try {
            if (!answer) {
                System.out.println("Введите порт удаленного хоста (1-65535)");
                String port = scanner.nextLine();
                portInt = new Validator<Integer>(port)
                        .withCheckingNull(false)
                        .withCheckingFunction(Integer::parseInt, "Номер порта должен быть целым числом")
                        .withCheckingPredicate((arg) -> (int) arg > 0 && (int) arg <= MAX_PORT, "Номер порта должен быть больше 0 и меньше 65536")
                        .getValue();

            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
        return portInt;
    }
}
