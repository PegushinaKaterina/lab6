package katya.server.util.workingWithCommand;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerCommandListener {


    public String readCommand(Scanner scanner) {
        try {
            System.out.print("Введите команду: ");
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
            return null;
        }
    }
}
