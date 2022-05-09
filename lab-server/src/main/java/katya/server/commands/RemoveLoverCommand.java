package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class RemoveLoverCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public RemoveLoverCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("remove_lower")
                .withQuantityOfArgs(0)
                .withDescription("удалить из коллекции все элементы, меньшие, чем заданный")
                .withGeneratesHumanBeing(true));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            CollectionHumanBeing.generatorHumanBeing.generateHumanBeing();
            System.out.println(collectionHumanBeing.removeLover(CollectionHumanBeing.generatorHumanBeing.getHumanBeing()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
