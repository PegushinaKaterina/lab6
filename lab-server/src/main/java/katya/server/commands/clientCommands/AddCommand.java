package katya.server.commands.clientCommands;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;

/**
 * Класс команды: add {element} : добавить новый элемент в коллекцию
 */
public class AddCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("add")
                .withQuantityOfArgs(0)
                .withDescription("добавить новый элемент в коллекцию")
                .withGeneratesHumanBeing(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.add(request.getHumanBeingArgument()))
                .build();

    }
}
