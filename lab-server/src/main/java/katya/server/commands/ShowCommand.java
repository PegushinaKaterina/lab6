package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

public class ShowCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public ShowCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("show")
                .withQuantityOfArgs(0)
                .withDescription("вывести в стандартный поток вывода все элементы коллекции в строковом представлении")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs){
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            System.out.println(collectionHumanBeing.show());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
