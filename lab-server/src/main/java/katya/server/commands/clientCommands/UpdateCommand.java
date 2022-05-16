package katya.server.commands.clientCommands;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;

public class UpdateCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("update")
                .withQuantityOfArgs(1)
                .withDescription("обновить значение элемента коллекции, id которого равен заданному. ")
                .withDescriptionOfArgs("Значение поля ID - целое число, больше 0")
                .withGeneratesHumanBeing(true));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.update(request.getLongArgument(), request.getHumanBeingArgument()))
                .build();
    }
}
