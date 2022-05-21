package katya.server.commands.serverCommands;

import katya.server.AbstractServerCommand;
import katya.server.entites.CollectionManager;
import katya.server.util.workingWithCommand.CommandManager;

import java.io.IOException;
import java.util.Scanner;

import static katya.common.util.SocketInitializer.acceptAnswer;

public class ExitCommand extends AbstractServerCommand {
    private final Scanner scanner = new Scanner(System.in);
    private final CollectionManager collectionManager;

    public ExitCommand(CollectionManager collectionManager) {
        super(new AbstractServerCommand.AbstractCommandBuilder()
                .withName("exit")
                .withDescription("завершить программу"));
        this.collectionManager = collectionManager;
    }

    @Override
    public String executeCommand() {
        askForSave(scanner);
        CommandManager.changeStatus();
        System.out.println("Работа сервера завершена");
        scanner.close();
        System.exit(0);
        return "Работа сервера завершена";
    }

    private void save(boolean answer) {
        try {
            if (answer) {
                collectionManager.getFileWorker().fileWriter(collectionManager);
                System.out.println(("Коллекция была успешно сохранена"));
            } else {
                System.out.println("Вы потеряли все изменения )=");
            }
        } catch (IOException e) {
            System.out.println("Возникла проблема при сохранении в файл");
        }
    }

    private void askForSave(Scanner scanner) {
        String question = "Вы хотите сохраниться? Введите да/нет";
        System.out.println(question);
        boolean isRunning = true;
        while (isRunning) {
            try {
                boolean answer = acceptAnswer(scanner);
                save(answer);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
