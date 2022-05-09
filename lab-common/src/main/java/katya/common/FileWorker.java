package katya.common;

import katya.common.state.ConsoleGeneratorHumanBeing;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;

public class FileWorker {
    private final File file;

    public FileWorker(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден");
        } else {
            if(!file.canRead()){
                throw new FileNotFoundException("Нет доступа на чтение");
            }
            else if (!file.canWrite()){
                throw new FileNotFoundException("Нет доступа на запись");
            }
        }
    }

    public ArrayDeque<String> fileReader() throws FileNotFoundException{
        ArrayDeque<String> deque = new ArrayDeque<String>();
        Scanner scanner = new Scanner(file);
        try (scanner) {
            do {
                String string = scanner.nextLine();
                deque.add(string);
            } while (scanner.hasNextLine());
            return deque;
        }

    }

    public void fileWriter(CollectionHumanBeing collectionHumanBeing) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        try (fileOutputStream; outputStreamWriter){
            for (int i = 0; i < collectionHumanBeing.getCollectionHumanBeing().size(); i++) {
                String string = Parser.humanBeingToStringParser(collectionHumanBeing.getCollectionHumanBeing().get(i));
                outputStreamWriter.write(string);
            }
        }
    }
}
