package katya.server.commands;

import katya.server.CollectionHumanBeing;
import katya.common.Validator;
import katya.common.entites.HumanBeing;

public class CountByImpactSpeedCommand extends AbstractCommand {
    private final CollectionHumanBeing collectionHumanBeing;

    public CountByImpactSpeedCommand(CollectionHumanBeing collectionHumanBeing) {
        super(new AbstractCommandBuilder()
                .withName("count_by_impact_speed")
                .withQuantityOfArgs(1)
                .withDescription("вывести количество элементов, значение поля СКОРОСТЬ УДАРА которых равно заданному. ")
                .withDescriptionOfArgs("Значение поля СКОРОСТЬ УДАРА - вещественное число, которое больше чем -484")
                .withGeneratesHumanBeing(false));
        this.collectionHumanBeing = collectionHumanBeing;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            Double impactSpeed = new Validator<Double>(commandArgs[0])
                    .withCheckingNull(false)
                    .withCheckingFunction(Double::parseDouble, "значение скорости удара должно быть вещественным числом")
                    .withCheckingPredicate(arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                            "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN)
                    .getValue();
            System.out.println(collectionHumanBeing.countByImpactSpeed(impactSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
