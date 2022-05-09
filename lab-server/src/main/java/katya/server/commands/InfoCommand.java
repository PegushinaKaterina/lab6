package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class InfoCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public InfoCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("info")
                .withQuantityOfArgs(0)
                .withDescription(" вывести в стандартный поток вывода информацию о коллекции")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            System.out.println(collectionHumanBeing.info());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
