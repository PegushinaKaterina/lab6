package katya.server.commands.clientCommands;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.common.util.ResponseBuilder;
import katya.server.entites.CollectionManager;

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
        return new Response(new ResponseBuilder()
                .withMessageToResponse(collectionManager.info()));
    }
}
