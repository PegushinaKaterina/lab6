package katya.client.util.workingWithCommand;

import java.util.Arrays;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandListener {

    public CommandToSend readCommandFromConsole(Scanner scanner) {
        System.out.print("Введите команду: ");
        return readCommand(scanner);
    }

    public CommandToSend readCommandFromScript(Scanner scanner) {
        return readCommand(scanner);
    }

    private CommandToSend readCommand(Scanner scanner) {
        try {
            String[] commandString = scanner.nextLine().trim().split(" ");
            String commandName = commandString[0].toLowerCase(Locale.ROOT);
            String[] commandsArgs = Arrays.copyOfRange(commandString, 1, commandString.length);
            return new CommandToSend(commandName, commandsArgs);
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
            return null;
        }
    }
}
