package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.Validator;
import katya.common.util.Request;

import java.util.HashMap;

public class HelpCommand extends AbstractClientCommand {
    private final HashMap<String, AbstractClientCommand> availableCommands;

    public HelpCommand(HashMap<String, AbstractClientCommand> availableCommands) {
        super(new AbstractCommandBuilder()
                .withName("help")
                .withQuantityOfArgs(0)
                .withDescription("вывести справку по доступным командам")
                .withGeneratesHumanBeing(false));
        this.availableCommands = availableCommands;
    }

    @Override
    public Response executeCommand(Request request) {
        StringBuilder stringBuilder = new StringBuilder("Доступные команды:\n");
        for (AbstractClientCommand command : availableCommands.values()) {
            stringBuilder.append(command.toString()).append("\n");
        }
        return new Response.ResponseBuilder()
                .withMessageToResponse(String.valueOf(stringBuilder))
                .build();
    }
}