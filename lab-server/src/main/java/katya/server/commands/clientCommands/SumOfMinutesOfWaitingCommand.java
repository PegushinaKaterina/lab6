package katya.server.commands.clientCommands;

import katya.common.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;
import katya.common.Validator;

public class SumOfMinutesOfWaitingCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public SumOfMinutesOfWaitingCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("sum_of_minutes_of_waiting")
                .withQuantityOfArgs(0)
                .withDescription("вывести сумму значений поля ВРЕМЯ ОЖИДАНИЯ для всех элементов коллекции")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }


    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.sumOfMinutesOfWaiting())
                .build();
    }
}
