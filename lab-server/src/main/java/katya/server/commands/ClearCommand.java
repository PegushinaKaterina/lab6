package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class ClearCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public ClearCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("clear")
                .withQuantityOfArgs(0)
                .withDescription("очистить коллекцию")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            System.out.println(collectionHumanBeing.clear());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
