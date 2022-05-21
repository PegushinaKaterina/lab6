package katya.client.util.workingWithServer;

import katya.client.util.workingWithCommand.AvailableCommands;
import katya.client.util.workingWithCommand.CommandToSend;
import katya.common.entites.HumanBeing;
import katya.common.util.Request;
import katya.common.util.RequestBuilder;
import katya.common.util.Validator;

public class RequestCreator {

    public Request createRequestOfCommand(CommandToSend command) {
        String name = command.getCommandName();
        Request request;
        if (AvailableCommands.COMMANDS_WITHOUT_ARGS.contains(name)) {
            request = createRequestWithoutArgs(command);
        } else if (AvailableCommands.COMMANDS_WITH_ID_ARG.contains(name)) {
            request = createRequestWithID(command);
        } else if (AvailableCommands.COMMANDS_WITH_HUMAN_BEING_ARG.contains(name)) {
            request = createRequestWithHumanBeing(command);
        } else if (AvailableCommands.COMMANDS_WITH_HUMAN_BEING_AND_ID_ARGS.contains(name)) {
            request = createRequestWithHumanBeingAndId(command);
        } else if (AvailableCommands.COMMANDS_WITH_MINUTES_OF_WAITING_ARG.contains(name)) {
            request = createRequestWithMinutesOfWaiting(command);
        } else if (AvailableCommands.COMMANDS_WITH_IMPACT_SPEED_ARG.contains(name)) {
            request = createRequestWithImpactSpeed(command);
        } else {
            throw new IllegalArgumentException("Такой команды не существует, для того, чтобы увидеть список команд введите HELP");
        }
        return request;
    }

    private Request createRequestWithoutArgs(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
        return new Request(new RequestBuilder()
                .withName(command.getCommandName()));
    }

    private Request createRequestWithID(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Long id = new Validator<Long>(command.getCommandArgs()[0]).withCheckingNull(false).withCheckingFunction(Long::parseLong, "значение id должно быть целым числом").withCheckingPredicate(arg -> (Long) arg > 0, "Значение id должно быть больше 0").getValue();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withLongArgument(id));
    }

    private Request createRequestWithHumanBeing(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
        HumanBeing.getGeneratorHumanBeing().generateHumanBeing();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withHumanBeingArgument(HumanBeing
                        .getGeneratorHumanBeing()
                        .getHumanBeing()));
    }

    private Request createRequestWithHumanBeingAndId(CommandToSend command) {

        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Long id = new Validator<Long>(command.getCommandArgs()[0]).withCheckingNull(false).withCheckingFunction(Long::parseLong, "значение id должно быть целым числом").withCheckingPredicate(arg -> (Long) arg > 0, "Значение id должно быть больше 0").getValue();
        HumanBeing.getGeneratorHumanBeing().generateHumanBeing();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withLongArgument(id)
                .withHumanBeingArgument(HumanBeing
                        .getGeneratorHumanBeing()
                        .getHumanBeing()));
    }

    private Request createRequestWithMinutesOfWaiting(CommandToSend command) {

        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Integer minutesOfWaiting = new Validator<Integer>(command.getCommandArgs()[0])
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt,
                        "значение времени ожидания должно быть целым числом").getValue();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withIntegerArgument(minutesOfWaiting));
    }

    private Request createRequestWithImpactSpeed(CommandToSend command) {
        Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
        Double impactSpeed = new Validator<Double>(command.getCommandArgs()[0])
                .withCheckingNull(false)
                .withCheckingFunction(Double::parseDouble,
                        "значение скорости удара должно быть вещественным числом")
                .withCheckingPredicate(arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                        "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN)
                .getValue();
        return new Request(new RequestBuilder()
                .withName(command.getCommandName())
                .withDoubleArgument(impactSpeed));
    }
}
