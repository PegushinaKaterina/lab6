package katya.server.commands.clientCommands;

import katya.common.util.Request;
import katya.common.util.Response;
import katya.common.util.ResponseBuilder;
import katya.server.entites.CollectionManager;

public class RemoveAllByMinutesOfWaitingCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public RemoveAllByMinutesOfWaitingCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("remove_all_by_minutes_of_waiting")
                .withQuantityOfArgs(1)
                .withDescription("удалить из коллекции все элементы, значение поля ВРЕМЯ ОЖИДАНИЯ которого эквивалентно заданному. ")
                .withDescriptionOfArgs("Значение поля ВРЕМЯ ОЖИДАНИЯ - целое число минут")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        try {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(String.valueOf(collectionManager
                            .removeAllByMinutesOfWaiting(request
                                    .getIntegerArgument()))));
        } catch (IllegalArgumentException e) {
            return new Response(new ResponseBuilder()
                    .withMessageToResponse(e.getMessage()));
        }

    }
}
