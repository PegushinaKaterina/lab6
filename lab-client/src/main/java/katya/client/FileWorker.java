package katya.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class FileWorker {
    public static ArrayDeque<String> readScript(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден");
        } else if(!file.canRead()){
            throw new FileNotFoundException("Нет доступа на чтение");
        } else {
            Scanner scanner = new Scanner(file);
            try (scanner){
                ArrayDeque<String> deque = new ArrayDeque<String>();
                do {
                    String string = scanner.nextLine();
                    deque.add(string);
                } while (scanner.hasNextLine());
                return deque;
            }
        }
    }
}
