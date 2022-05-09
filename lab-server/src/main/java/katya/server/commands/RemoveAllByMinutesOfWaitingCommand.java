package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class RemoveAllByMinutesOfWaitingCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public RemoveAllByMinutesOfWaitingCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("remove_all_by_minutes_of_waiting")
                .withQuantityOfArgs(1)
                .withDescription("удалить из коллекции все элементы, значение поля ВРЕМЯ ОЖИДАНИЯ которого эквивалентно заданному. ")
                .withDescriptionOfArgs("Значение поля ВРЕМЯ ОЖИДАНИЯ - целое число минут")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            Integer minutesOfWaiting = new Validator<Integer>(commandArgs[0])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение времени ожидания должно быть целым числом")
                    .getValue();
            System.out.println(collectionHumanBeing.removeAllByMinutesOfWaiting(minutesOfWaiting));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
