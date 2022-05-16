package katya.server.commands.clientCommands;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;

public class ShowCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("show")
                .withQuantityOfArgs(0)
                .withDescription("вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.show())
                .build();
    }
}
