package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;
import katya.common.Validator;

public class InfoCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("info")
                .withQuantityOfArgs(0)
                .withDescription(" вывести в стандартный поток вывода информацию о коллекции")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.info())
                .build();
    }
}
