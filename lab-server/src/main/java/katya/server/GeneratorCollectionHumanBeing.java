package katya.server;

import katya.common.FileWorker;
import katya.common.Parser;
import katya.common.entites.HumanBeing;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;

public class GeneratorCollectionHumanBeing {
    CollectionHumanBeing collectionHumanBeing;

    public GeneratorCollectionHumanBeing(FileWorker fileWorker) throws FileNotFoundException {
        collectionHumanBeing = new CollectionHumanBeing(fileWorker);
        ArrayDeque<String> deque = fileWorker.fileReader();
        HumanBeing humanBeing = null;
        int i = 1;
        while (!deque.isEmpty()) {
            System.out.println("Человек №" + i + ":");
            humanBeing = Parser.stringToHumanBeingParser(deque.remove());
            if (humanBeing != null) {
                System.out.println("Генерация прошла успешно");
                collectionHumanBeing.add(humanBeing);
            }
            i++;
        }
    }

    public CollectionHumanBeing getCollectionHumanBeing() {
        return collectionHumanBeing;
    }
}
