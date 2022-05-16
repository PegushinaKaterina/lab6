package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.Validator;
import katya.common.util.Request;

import java.util.ArrayDeque;

public class HistoryCommand extends AbstractClientCommand {
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
    public Response executeCommand(Request request) {
        StringBuilder stringBuilder = new StringBuilder("Последние 10 команд:\n");
        for (String name : queueOfCommands) {
            stringBuilder.append(name).append("\n");
        }
        return new Response.ResponseBuilder()
                .withMessageToResponse(String.valueOf(stringBuilder))
                .build();

    }
}