package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class RemoveHeadCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public RemoveHeadCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("remove_head")
                .withQuantityOfArgs(0)
                .withDescription("вывести первый элемент коллекции и удалить его")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            System.out.println(collectionHumanBeing.removeHead());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
