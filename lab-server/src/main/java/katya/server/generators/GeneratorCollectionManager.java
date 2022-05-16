package katya.server.generators;

import katya.common.entites.HumanBeing;
import katya.server.entites.CollectionManager;
import katya.server.util.workingWithCommand.FileWorker;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneratorCollectionManager {
    private CollectionManager collectionHumanBeing;

    public GeneratorCollectionManager(FileWorker fileWorker) throws FileNotFoundException {
        collectionHumanBeing = new CollectionManager(fileWorker);
        Scanner scanner = new Scanner(fileWorker.getFile());
        int i = 1;
        try (scanner) {
            while (scanner.hasNextLine()) {
                System.out.println("Человек №" + i + ":");
                try {
                    HumanBeing.getGeneratorHumanBeing().generateHumanBeing();
                    System.out.println(collectionHumanBeing.add(HumanBeing.getGeneratorHumanBeing().getHumanBeing()));

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                i++;
            }
        }
    }

    public CollectionManager getCollectionHumanBeing() {
        return collectionHumanBeing;
    }
}
