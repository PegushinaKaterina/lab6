package katya.client.state;

import java.util.function.Function;
import java.util.function.Predicate;

public class valueSetter<T> {
    private String string;
    private T value;

    public valueSetter(String string) {
        this.string = string;
    }

    public valueSetter withCheckingNull(boolean nullable) {
        if ("".equals(string)) {
            if (nullable) {
                value = null;
            } else {
                throw new IllegalArgumentException("Значение не может быть пустой строкой");
            }
        }
        return this;
    }

    public valueSetter withCheckingFunction(Function<String, T> function, String description) {
        if (!"".equals(string)) {
            try {
                value = function.apply(string);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Ошибка при обработке значения, " + description);
            }
        }
        return this;
    }

    public valueSetter withCheckingPredicate(Predicate<Object> predicate, String error) {
        if (!"".equals(string)) {
            if (!predicate.test(value)) {
                throw new IllegalArgumentException(error);
            }
        }
        return this;
    }

    public T getValue() {
        if (value.getClass().equals(String.class) && !"".equals(string)){
            value = (T) string;
        }
        return value;
    }
}

