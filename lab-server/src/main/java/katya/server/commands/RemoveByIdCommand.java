package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public RemoveByIdCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("remove_by_id")
                .withQuantityOfArgs(1)
                .withDescription("удалить элемент из коллекции по его ID. ")
                .withDescriptionOfArgs("Значение поля ID - целое число, больше 0")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            Long id = new Validator<Long>(commandArgs[0])
                    .withCheckingNull(false)
                    .withCheckingFunction(Long::parseLong, "значение должно быть целым числом")
                    .withCheckingPredicate(arg -> (Long) arg > 0,
                            "Значение должно быть больше 0")
                    .getValue();
            System.out.println(collectionHumanBeing.removeById(id));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
