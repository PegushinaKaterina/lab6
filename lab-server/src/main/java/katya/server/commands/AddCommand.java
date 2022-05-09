package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

/**
 * Класс команды: add {element} : добавить новый элемент в коллекцию
 */
public class AddCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public AddCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("add")
                .withQuantityOfArgs(0)
                .withDescription("добавить новый элемент в коллекцию")
                .withGeneratesHumanBeing(true));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            CollectionHumanBeing.generatorHumanBeing.generateHumanBeing();
            System.out.println(collectionHumanBeing.add(CollectionHumanBeing.generatorHumanBeing.getHumanBeing()));
        } catch (
                IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
