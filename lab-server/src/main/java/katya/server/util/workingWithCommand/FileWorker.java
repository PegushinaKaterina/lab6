package katya.server.util.workingWithCommand;

import katya.common.util.Parser;
import katya.server.entites.CollectionManager;

import java.io.*;


public class FileWorker {
    private final File file;

    public FileWorker(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден");
        } else {
            if (!file.canRead()) {
                throw new FileNotFoundException("Нет доступа на чтение");
            } else if (!file.canWrite()) {
                throw new FileNotFoundException("Нет доступа на запись");
            }
        }
    }

    public File getFile() {
        return file;
    }

    public void fileWriter(CollectionManager collectionManager) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
            for (int i = 0; i < collectionManager.getCollectionHumanBeing().size(); i++) {
                String string = Parser.humanBeingToStringParser(collectionManager.getCollectionHumanBeing().get(i));
                outputStreamWriter.write(string);
            }
        }
    }
}

