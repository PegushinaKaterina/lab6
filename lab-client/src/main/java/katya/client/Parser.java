package katya.client;
import katya.common.entites.HumanBeing;
import katya.common.Validator;
import katya.common.state.FileGeneratorHumanBeing;
import katya.common.state.GeneratorHumanBeing;

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
}
