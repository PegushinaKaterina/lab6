package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;
import katya.common.Validator;

public class ClearCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("clear")
                .withQuantityOfArgs(0)
                .withDescription("очистить коллекцию")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
       return new Response.ResponseBuilder()
               .withMessageToResponse(collectionManager.clear())
               .build();
    }
}
