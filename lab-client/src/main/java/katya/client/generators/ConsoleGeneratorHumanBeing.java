package katya.client.generators;

import katya.common.Validator;
import katya.common.entites.HumanBeing;
import katya.common.state.State;
import katya.common.util.CheckBoolean;

import java.util.Scanner;

public class ConsoleGeneratorHumanBeing extends State {

    public ConsoleGeneratorHumanBeing(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void generateHumanBeingFields() {
            setValue("Введите имя",
                    this::setName);
            setValue("Введите координату X" +
                            "значение должно быть целым числом не больше " + HumanBeing.Coordinates.X_MAX,
                    this::setX);
            setValue("Введите координату Y" + "значение должно быть целым числом",
                    this::setY);
            setValue("Это реальный герой или нет?" + "значение должно быть Да или Нет",
                    this::setRealHero);
            setValue("У героя есть зубочистка?" + "значение должно быть Да или Нет",
                    this::setHasToothpick);
            setValue("Введите скорость удара" +
                            "значение должно быть вещественным числом и больше " + HumanBeing.IMPACT_SPEED_MIN,
                    this::setImpactSpeed);
            setValue("Введите название саундтрека",
                    this::setSoundtrackName);
            setValue("Введите время ожидания" + "значение должно быть целым числом",
                    this::setMinutesOfWaiting);
            setValue("Введите тип оружия" +
                            "допустимые значения: \n" + HumanBeing.WeaponType.show() + "регистр должен сохраняться",
                    this::setWeaponType);
            setValue("Машина крутая?" +
                            "значение должно быть Да или Нет. Если хотите оставить это значение, пустым нажмите enter",
                    this::setCar);
    }

    public void setValue(String message, Runnable runnable) {
        System.out.println(message);
        boolean isRunning = true;
        while (isRunning) {
            try {
                runnable.run();
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "повторите ввод");
            }
        }
    }

    public void setName() throws IllegalArgumentException {
        super.name = new Validator<String>(super.scanner)
                .withCheckingNull(false)
                .getValue();
    }

    public void setX() throws IllegalArgumentException {
        super.x = new Validator<Integer>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt, "значение координаты X должно быть целым числом")
                .withCheckingPredicate(arg -> (int) arg < HumanBeing.Coordinates.X_MAX,
                        "Значение координаты X должно быть не больше " + HumanBeing.Coordinates.X_MAX)
                .getValue();
    }

    public void setY() throws IllegalArgumentException {
        super.y = new Validator<Integer>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt, "значение координаты Y должно быть целым числом")
                .getValue();
    }

    public void setRealHero() throws IllegalArgumentException {
        super.realHero = new Validator<Boolean>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(CheckBoolean::checkBoolean, "значение \"Это реальный герой\" должно быть Да или Нет")
                .getValue();
    }

    public void setHasToothpick() throws IllegalArgumentException {
        super.hasToothpick = new Validator<Boolean>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(CheckBoolean::checkBoolean, "значение \"У человека есть зубочистка\" должно быть Да или Нет")
                .getValue();
    }

    public void setImpactSpeed() throws IllegalArgumentException {
        super.impactSpeed = new Validator<Double>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(Double::parseDouble, "значение скорости удара должно быть вещественным числом")
                .withCheckingPredicate(arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                        "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN)
                .getValue();

    }

    public void setSoundtrackName() throws IllegalArgumentException {
        super.soundtrackName = new Validator<String>(super.scanner)
                .withCheckingNull(false)
                .getValue();
    }

    public void setMinutesOfWaiting() throws IllegalArgumentException {
        super.minutesOfWaiting = new Validator<Integer>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(Integer::parseInt, "значение времени ожидания должно быть целым числом")
                .getValue();
    }

    public void setWeaponType() throws IllegalArgumentException {
        super.weaponType = new Validator<HumanBeing.WeaponType>(super.scanner)
                .withCheckingNull(false)
                .withCheckingFunction(HumanBeing.WeaponType::valueOf,
                        "тип оружия должен быть из списка: \n" + HumanBeing.WeaponType.show() + "Регистр должен сохраняться")
                .getValue();

    }

    public void setCar() throws IllegalArgumentException {
        super.cool = new Validator<Boolean>(super.scanner)
                .withCheckingNull(true)
                .withCheckingFunction(CheckBoolean::checkBoolean,
                        "значение \"У человека есть крутая машина\" должно быть Да или Нет, или быть пустым")
                .getValue();
    }
}