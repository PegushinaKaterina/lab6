package katya.server.commands.clientCommands;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;

public class RemoveLoverCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public RemoveLoverCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("remove_lower")
                .withQuantityOfArgs(0)
                .withDescription("удалить из коллекции все элементы, меньшие, чем заданный")
                .withGeneratesHumanBeing(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.removeLover(request.getHumanBeingArgument()))
                .build();
    }
}
