package katya.common.util;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class AnswerAccepter {
    private AnswerAccepter() {
    }

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
}
