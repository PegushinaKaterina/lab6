package katya.server;

import katya.common.Parser;
import katya.server.entites.CollectionManager;

import java.io.*;

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
    public File getFile(){
        return file;
    }

    public void fileWriter(CollectionManager collectionHumanBeing) throws IOException {
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

