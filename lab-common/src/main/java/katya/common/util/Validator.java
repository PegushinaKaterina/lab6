package katya.common.util;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Validator<T> {
    private String string;
    private T value;

    public Validator(String string) {
        this.string = string;
    }
    public Validator(Scanner scanner) {
        try {
            this.string = scanner.nextLine();
        }catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }

    }

    public Validator<T> withCheckingNull(boolean nullable) {
        if ("".equals(string)) {
            if (nullable) {
                value = null;
            } else {
                throw new IllegalArgumentException("Значение не может быть пустой строкой");
            }
        }
        return this;
    }

    public Validator<T> withCheckingFunction(Function<String, T> function, String description) {
        if (!"".equals(string)) {
            try {
                value = function.apply(string);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Ошибка при обработке значения, " + description);
            }
        }
        return this;
    }

    public Validator<T> withCheckingPredicate(Predicate<Object> predicate, String error) {
        if (!"".equals(string)) {
            if (!predicate.test(value)) {
                throw new IllegalArgumentException(error);
            }
        }
        return this;
    }

    public T getValue() {
        if (value.getClass().equals(String.class) && !"".equals(string)) {
            value = (T) string;
        }
        return value;
    }

    public static void validateQuantityOfArgs(String[] args, int amountOfArgs) throws IllegalArgumentException {
        if (args.length != amountOfArgs) {
            throw new IllegalArgumentException("Неверное количество аргументов, данная команда требует " + amountOfArgs + " аргументов");
        }
    }
}