package katya.server.generators;

import katya.common.entites.HumanBeing;
import katya.server.FileWorker;
import katya.server.entites.CollectionManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneratorCollectionHumanBeing {
    CollectionManager collectionHumanBeing;

    public GeneratorCollectionHumanBeing(FileWorker fileWorker) throws FileNotFoundException {
        collectionHumanBeing = new CollectionManager(fileWorker);
        Scanner scanner = new Scanner(fileWorker.getFile());
        int i = 1;
        try (scanner) {
            while (scanner.hasNextLine()){
                System.out.println("Человек №" + i + ":");
                try {
                    HumanBeing.generatorHumanBeing.generateHumanBeing();
                    System.out.println(collectionHumanBeing.add(HumanBeing.generatorHumanBeing.getHumanBeing()));

                }catch (IllegalArgumentException e){
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
