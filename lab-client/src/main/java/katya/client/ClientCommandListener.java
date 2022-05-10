package katya.client;

import katya.client.generators.ConsoleGeneratorHumanBeing;
import katya.client.generators.ScriptGeneratorHumanBeing;
import katya.client.util.CommandToSend;
import katya.common.Validator;
import katya.common.entites.HumanBeing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ClientCommandListener {

    public CommandToSend readCommandFromConsole(Scanner scanner) {
            System.out.print("Введите команду: ");
            return readCommand(scanner);
    }

    public CommandToSend readCommandFromScript(Scanner scanner) {
            return readCommand(scanner);
    }

    public CommandToSend readCommand(Scanner scanner){
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
