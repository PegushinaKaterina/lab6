package katya.client;

import katya.client.state.FileGeneratorHumanBeing;
import katya.client.state.GeneratorHumanBeing;
import katya.common.entites.HumanBeing;

public class Parser {

    public static HumanBeing stringToHumanBeingParser(String string) {
        String[] humanBeingString = string.split(",");
        for (int i = 0; i < humanBeingString.length; i++) {
            humanBeingString[i] = humanBeingString[i].trim();
        }
        int quantityOfArgs = 10;
        Validator.validateQuantityOfArgs(humanBeingString, quantityOfArgs);
        FileGeneratorHumanBeing fileGeneratorHumanBeing = new FileGeneratorHumanBeing(humanBeingString);
        GeneratorHumanBeing generatorHumanBeing = new GeneratorHumanBeing(fileGeneratorHumanBeing);
        generatorHumanBeing.generateHumanBeing();
        return generatorHumanBeing.getHumanBeing();
    }

    public static String humanBeingToStringParser(HumanBeing humanBeing) {
        String string = "";
        string += humanBeing.getName() + "," + humanBeing.getCoordinates().getX() +
                "," + humanBeing.getCoordinates().getY() + "," + humanBeing.getRealHero() +
                "," + humanBeing.getHasToothpick() + "," + humanBeing.getImpactSpeed() +
                "," + humanBeing.getSoundtrackName() + "," + humanBeing.getMinutesOfWaiting() +
                "," + humanBeing.getWeaponType() + "," +
                (humanBeing.getCar().getCool() == null ? " " : humanBeing.getCar().getCool()) + "\n";
        return string;
    }
}
