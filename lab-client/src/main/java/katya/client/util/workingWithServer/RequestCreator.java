package katya.client.util.workingWithServer;

import katya.client.util.workingWithCommand.AvailableCommands;
import katya.client.util.workingWithCommand.CommandToSend;
import katya.common.util.Validator;
import katya.common.entites.HumanBeing;
import katya.common.util.Request;

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
        try {
            Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
            return new Request.RequestBuilder().withName(command.getCommandName()).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithID(CommandToSend command) {
        try {
            Validator.validateQuantityOfArgs(command.getCommandArgs(), 1);
            Long id = new Validator<Long>(command.getCommandArgs()[0]).withCheckingNull(false).withCheckingFunction(Long::parseLong, "значение id должно быть целым числом").withCheckingPredicate(arg -> (Long) arg > 0, "Значение id должно быть больше 0").getValue();
            return new Request.RequestBuilder().withName(command.getCommandName()).withLongArgument(id).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithHumanBeing(CommandToSend command) {
        try {
            Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
            HumanBeing.getGeneratorHumanBeing().generateHumanBeing();
            return new Request.RequestBuilder().withName(command
                    .getCommandName())
                    .withHumanBeingArgument(HumanBeing.getGeneratorHumanBeing().getHumanBeing())
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithHumanBeingAndId(CommandToSend command) {
        try {
            Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
            Long id = new Validator<Long>(command.getCommandArgs()[0]).withCheckingNull(false).withCheckingFunction(Long::parseLong, "значение id должно быть целым числом").withCheckingPredicate(arg -> (Long) arg > 0, "Значение id должно быть больше 0").getValue();
            HumanBeing.getGeneratorHumanBeing().generateHumanBeing();
            return new Request.RequestBuilder().withName(command.getCommandName()).withLongArgument(id).withHumanBeingArgument(HumanBeing.getGeneratorHumanBeing().getHumanBeing()).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithMinutesOfWaiting(CommandToSend command) {
        try {
            Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
            Integer minutesOfWaiting = new Validator<Integer>(command.getCommandArgs()[0]).withCheckingNull(false).withCheckingFunction(Integer::parseInt, "значение времени ожидания должно быть целым числом").getValue();
            return new Request.RequestBuilder().withName(command.getCommandName()).withLongArgument(Long.valueOf(minutesOfWaiting)).build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Request createRequestWithImpactSpeed(CommandToSend command) {
        try {
            Validator.validateQuantityOfArgs(command.getCommandArgs(), 0);
            Double impactSpeed = new Validator<Double>(command.getCommandArgs()[0])
                    .withCheckingNull(false)
                    .withCheckingFunction(Double::parseDouble,
                            "значение скорости удара должно быть вещественным числом")
                    .withCheckingPredicate(arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                            "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN)
                    .getValue();
            return new Request.RequestBuilder()
                    .withName(command.getCommandName())
                    .withDoubleArgument(impactSpeed)
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
