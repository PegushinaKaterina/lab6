package katya.server;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandListener {


    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        try (scanner){
            System.out.print("Введите команду: ");
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
            return null;
        }
    }
}
