package katya.common.util;

import java.util.Locale;

public class CheckBoolean {
    public static Boolean checkBoolean(String bool) {
        bool = bool.toLowerCase(Locale.ROOT);
        if (bool.equals("да") || bool.equals("true")) {
            return true;
        } else if (bool.equals("нет") || bool.equals("false")) {
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
