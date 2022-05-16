package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;
import katya.common.Validator;

public class RemoveHeadCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("remove_head")
                .withQuantityOfArgs(0)
                .withDescription("вывести первый элемент коллекции и удалить его")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.removeHead())
                .build();
    }
}
