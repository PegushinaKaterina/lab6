package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class SumOfMinutesOfWaitingCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public SumOfMinutesOfWaitingCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("sum_of_minutes_of_waiting")
                .withQuantityOfArgs(0)
                .withDescription("вывести сумму значений поля ВРЕМЯ ОЖИДАНИЯ для всех элементов коллекции")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());

            System.out.println(collectionHumanBeing.sumOfMinutesOfWaiting());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
