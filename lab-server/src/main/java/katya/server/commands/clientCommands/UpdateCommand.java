package katya.server.commands.clientCommands;

import katya.common.util.Request;
import katya.common.util.Response;
import katya.common.util.ResponseBuilder;
import katya.server.entites.CollectionManager;

public class UpdateCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder().withName("update").withQuantityOfArgs(1).withDescription("обновить значение элемента коллекции, id которого равен заданному. ").withDescriptionOfArgs("Значение поля ID - целое число, больше 0").withGeneratesHumanBeing(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            return new Response(new ResponseBuilder().withMessageToResponse(collectionManager.update(request.getLongArgument(), request.getHumanBeingArgument())));
        } catch (IllegalArgumentException e) {
            return new Response(new ResponseBuilder().withMessageToResponse(e.getMessage()));
        }
    }
}
