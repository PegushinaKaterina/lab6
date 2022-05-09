package katya.server.commands;

import katya.common.Validator;

import java.util.ArrayDeque;

public class HistoryCommand extends AbstractCommand {
    private final ArrayDeque<String> queueOfCommands;

    public HistoryCommand(ArrayDeque<String> queueOfCommands) {
        super(new AbstractCommandBuilder()
                .withName("history")
                .withQuantityOfArgs(0)
                .withDescription("вывести последние 10 команд (без их аргументов)")
                .withGeneratesHumanBeing(false));
        this.queueOfCommands = queueOfCommands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            System.out.println("Последние 10 команд:");
            for (String name : queueOfCommands) {
                System.out.println(name);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}