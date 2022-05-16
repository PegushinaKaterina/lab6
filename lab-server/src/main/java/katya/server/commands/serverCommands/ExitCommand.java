package katya.server.commands.serverCommands;

import katya.common.util.Parser;
import katya.server.util.workingWithCommand.CommandManager;
import katya.server.entites.CollectionManager;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class ExitCommand extends AbstractServerCommand{
    private final Scanner scanner;
    private final Parser parser;
    private final CollectionManager collectionManager;

    public ExitCommand(Scanner scanner, Parser parser, CollectionManager collectionManager) {
        super(new AbstractServerCommand.AbstractCommandBuilder()
                .withName("exit")
                .withDescription("завершить программу"));
        this.scanner = scanner;
        this.parser = parser;
        this.collectionManager = collectionManager;
    }

    @Override
    public String executeCommand() {
        chooseSaving();
        CommandManager.changeStatus();
        return "Работа сервера завершена";
    }

    private void chooseSaving() {
        System.out.println(("Do you want to save changes? [y/n]"));
        try {
            String s = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if ("n".equals(s)) {
                System.out.println(("You lost all of your data )="));
            } else if ("y".equals(s)) {
                collectionManager.getFileWorker().fileWriter(collectionManager);
                System.out.println(("Collection was successfully saved"));
            } else {
                System.out.println(("You entered not valid symbol, try again"));
                chooseSaving();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
