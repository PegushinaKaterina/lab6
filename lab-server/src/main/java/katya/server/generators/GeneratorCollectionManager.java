package katya.server.generators;

import katya.common.entites.HumanBeing;
import katya.server.entites.CollectionManager;
import katya.server.util.workingWithCommand.FileWorker;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneratorCollectionManager {
    private final CollectionManager collectionManager;

    public GeneratorCollectionManager(FileWorker fileWorker) throws FileNotFoundException {
        collectionManager = new CollectionManager(fileWorker);
        Scanner scanner = new Scanner(fileWorker.getFile());
        HumanBeing.getGeneratorHumanBeing().changeState(new FileGeneratorHumanBeing(scanner));
        int i = 1;
        while (scanner.hasNextLine()) {
            System.out.println("Человек №" + i + ":");
            try {
                HumanBeing.getGeneratorHumanBeing().generateHumanBeing();
                System.out.println(collectionManager.add(HumanBeing.getGeneratorHumanBeing().getHumanBeing()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            i++;
        }
        scanner.close();
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
