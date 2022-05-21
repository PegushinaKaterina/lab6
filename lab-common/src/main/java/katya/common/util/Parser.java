package katya.common.util;

import katya.common.entites.HumanBeing;

import java.util.Scanner;

public final class Parser {
    private static final int QUANTITY_OF_ARGS = 10;
    private Parser() {
    }

    public static String[] stringToHumanBeingStringParserFromScript(Scanner scanner) {
        String[] stringHumanBeing = new String[QUANTITY_OF_ARGS];
        for (int i = 0; i < QUANTITY_OF_ARGS; i++) {
            if (scanner.hasNextLine()) {
                stringHumanBeing[i] = scanner.nextLine();
            } else {
                throw new IllegalArgumentException("Неверное количество аргументов, данная команда требует 10 аргументов");
            }
        }
        return stringHumanBeing;
    }

    public static String[] stringToHumanBeingStringParserFromFile(Scanner scanner) {
        String string = scanner.nextLine();
        String[] stringHumanBeing = string.split(",");
        for (int i = 0; i < stringHumanBeing.length; i++) {
            stringHumanBeing[i] = stringHumanBeing[i].trim();
        }
        return stringHumanBeing;
    }

    public static String humanBeingToStringParser(HumanBeing humanBeing) {
        String string = "";
        string += humanBeing.getName() + "," + humanBeing.getCoordinates().getX() + ","
                + humanBeing.getCoordinates().getY() + "," + humanBeing.getRealHero() + ","
                + humanBeing.getHasToothpick() + "," + humanBeing.getImpactSpeed() + ","
                + humanBeing.getSoundtrackName() + "," + humanBeing.getMinutesOfWaiting() + ","
                + humanBeing.getWeaponType() + ","
                + (humanBeing.getCar().getCool() == null ? "null" : humanBeing.getCar().getCool()) + "\n";
        return string;
    }
}
