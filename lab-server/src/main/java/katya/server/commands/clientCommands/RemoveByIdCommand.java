package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;
import katya.common.Validator;

public class RemoveByIdCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("remove_by_id")
                .withQuantityOfArgs(1)
                .withDescription("удалить элемент из коллекции по его ID. ")
                .withDescriptionOfArgs("Значение поля ID - целое число, больше 0")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.removeById(request.getLongArgument()))
                .build();
    }
}
