package katya.server.commands.serverCommands;

import katya.server.entites.CollectionManager;

import java.io.IOException;

public class SaveCommand extends AbstractServerCommand {

    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super(new AbstractServerCommand.AbstractCommandBuilder()
                .withName("save")
                .withDescription("сохранить коллекцию в файл"));
        this.collectionManager = collectionManager;
    }

    @Override
    public String executeCommand() {
        try {
            collectionManager.getFileWorker().fileWriter(collectionManager);
            return "Коллекция сохранена в файл";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Отсутствуют права на запись в файл, либо путь к файлу изменился";
        }
    }
}

