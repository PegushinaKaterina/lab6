package katya.server.commands;

import katya.common.Validator;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, AbstractCommand> availableCommands;

    public HelpCommand(HashMap<String, AbstractCommand> availableCommands) {
        super(new AbstractCommandBuilder()
                .withName("help")
                .withQuantityOfArgs(0)
                .withDescription("вывести справку по доступным командам")
                .withGeneratesHumanBeing(false));
        this.availableCommands = availableCommands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Доступные команды:");
        for (AbstractCommand command : availableCommands.values()) {
            System.out.println(command.toString());
        }
    }
}
