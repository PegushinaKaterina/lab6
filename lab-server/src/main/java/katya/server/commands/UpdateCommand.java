package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class UpdateCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public UpdateCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("update")
                .withQuantityOfArgs(1)
                .withDescription("обновить значение элемента коллекции, id которого равен заданному. ")
                .withDescriptionOfArgs("Значение поля ID - целое число, больше 0")
                .withGeneratesHumanBeing(true));
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
            CollectionHumanBeing.generatorHumanBeing.generateHumanBeing();
            System.out.println(collectionHumanBeing.update(id, CollectionHumanBeing.generatorHumanBeing.getHumanBeing()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
