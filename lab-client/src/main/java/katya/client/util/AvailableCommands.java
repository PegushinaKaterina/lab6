package katya.client.util;

import katya.common.Validator;
import katya.common.entites.HumanBeing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;

public final class AvailableCommands {

    public static final Set<String> COMMANDS_WITHOUT_ARGS = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_ID_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_HUMAN_BEING_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_HUMAN_BEING_AND_ID_ARGS = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_MINUTES_OF_WAITING_ARG = new HashSet<>();
    public static final Set<String> COMMANDS_WITH_IMPACT_SPEED_ARG = new HashSet<>();
    public static final String SCRIPT_ARGUMENT_COMMAND;

    static {
        Collections.addAll(COMMANDS_WITHOUT_ARGS,
                "help",
                "info",
                "show",
                "clear",
                "exit",
                "remove_head",
                "history"
        );
        Collections.addAll(COMMANDS_WITH_HUMAN_BEING_ARG,
                "add",
                "remove_lower"
        );
        Collections.addAll(COMMANDS_WITH_HUMAN_BEING_AND_ID_ARGS,
                "update"
        );
        Collections.addAll(COMMANDS_WITH_ID_ARG,
                "remove_by_id"
        );
        Collections.addAll(COMMANDS_WITH_MINUTES_OF_WAITING_ARG,
                "sum_of_minutes_of_waiting"
        );
        Collections.addAll(COMMANDS_WITH_IMPACT_SPEED_ARG,
                "count_by_impact_speed"
        );
        SCRIPT_ARGUMENT_COMMAND = "execute_script";
    }

    private AvailableCommands() {
    }
}
