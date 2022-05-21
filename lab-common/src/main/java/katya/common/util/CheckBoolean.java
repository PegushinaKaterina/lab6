package katya.common.util;

import java.util.Locale;

public final class CheckBoolean {
    private CheckBoolean() {
    }

    public static boolean checkBoolean(String string) {
        String bool = string.toLowerCase(Locale.ROOT);
        if ("да".equals(bool) || "true".equals(bool) || "yes".equals(bool)) {
            return true;
        } else if ("нет".equals(bool) || "false".equals(bool) || "no".equals(bool)) {
            return false;
        } else {
            throw new IllegalArgumentException("Значение может быть только да или нет");
        }
    }
}
