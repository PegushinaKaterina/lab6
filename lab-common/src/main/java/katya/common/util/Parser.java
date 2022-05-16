package katya.common.util;

import katya.common.entites.HumanBeing;

import java.util.Scanner;

public class Parser {
    public static String[] stringToHumanBeingStringParserFromScript(Scanner scanner) {
        String[] stringHumanBeing = new String[10];
        for (int i = 0; i < 10; i++) {
            if (scanner.hasNextLine()) {
                stringHumanBeing[i] = scanner.nextLine();
            } else {
                throw new IllegalArgumentException("Неверное количество аргументов, данная команда требует 10 аргументов");
            }
        }
        return stringHumanBeing;
    }

    public static String[] stringToHumanBeingStringParserFromFile(Scanner scanner){
        String string = scanner.nextLine();
        String[] stringHumanBeing = string.split(",");
        for (int i = 0; i < stringHumanBeing.length; i++) {
            stringHumanBeing[i] = stringHumanBeing[i].trim();
        }
        return stringHumanBeing;
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
