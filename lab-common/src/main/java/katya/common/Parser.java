package katya.common;
import katya.common.entites.HumanBeing;
import katya.common.state.FileGeneratorHumanBeing;
import katya.common.state.GeneratorHumanBeing;

public class Parser {

    public static HumanBeing stringToHumanBeingParser(String string) {

        FileGeneratorHumanBeing fileGeneratorHumanBeing = new FileGeneratorHumanBeing(humanBeingString);
        GeneratorHumanBeing generatorHumanBeing = new GeneratorHumanBeing(fileGeneratorHumanBeing);
        try {
            generatorHumanBeing.generateHumanBeing();
            return generatorHumanBeing.getHumanBeing();
        } catch (IllegalArgumentException e){
            return null;
        }
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
