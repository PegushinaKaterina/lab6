package katya.server.commands.clientCommands;

import katya.common.util.Response;
import katya.common.util.Request;
import katya.server.entites.CollectionManager;

public class CountByImpactSpeedCommand extends AbstractClientCommand {
    private final CollectionManager collectionManager;

    public CountByImpactSpeedCommand(CollectionManager collectionManager) {
        super(new AbstractCommandBuilder()
                .withName("count_by_impact_speed")
                .withQuantityOfArgs(1)
                .withDescription("вывести количество элементов, значение поля СКОРОСТЬ УДАРА которых равно заданному. ")
                .withDescriptionOfArgs("Значение поля СКОРОСТЬ УДАРА - вещественное число, которое больше чем -484")
                .withGeneratesHumanBeing(false));
        this.collectionManager = collectionManager;
    }

    @Override
    public Response executeCommand(Request request) {
        return new Response.ResponseBuilder()
                .withMessageToResponse(collectionManager.countByImpactSpeed(request.getDoubleArgument()))
                .build();
    }
}
