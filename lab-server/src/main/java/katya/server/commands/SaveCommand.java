package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;

import java.io.IOException;

public class SaveCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public SaveCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("save")
                .withQuantityOfArgs(0)
                .withDescription("сохранить коллекцию в файл")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs){
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            collectionHumanBeing.getFileWorker().fileWriter(collectionHumanBeing);
            System.out.println("Коллекция сохранена в файл");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Отсутствуют права на запись в файл, либо путь к файлу изменился");
        }
    }
}
