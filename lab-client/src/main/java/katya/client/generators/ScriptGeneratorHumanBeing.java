package katya.client.generators;

import katya.common.entites.Coordinates;
import katya.common.entites.HumanBeing;
import katya.common.entites.WeaponType;
import katya.common.state.State;
import katya.common.util.CheckBoolean;
import katya.common.util.Parser;
import katya.common.util.Validator;

import java.util.Scanner;

public class ScriptGeneratorHumanBeing extends State {
    private String[] stringHumanBeing;

    public ScriptGeneratorHumanBeing(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void generateHumanBeingFields() {
        try {
            stringHumanBeing = Parser.stringToHumanBeingStringParserFromScript(super.scanner);
            setName();
            setCoordinates();
            setRealHero();
            setHasToothpick();
            setImpactSpeed();
            setSoundtrackName();
            setMinutesOfWaiting();
            setWeaponType();
            setCar();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    @Override
    public boolean isCorrect() {
        return errors.isEmpty();
    }

    public void errorHandler() {
        while (!errors.isEmpty()) {
            System.out.println(errors.remove());
        }
    }

    public void setName() {
        try {
            super.name = new Validator<String>(stringHumanBeing[0])
                    .withCheckingNull(false)
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setCoordinates() {
        try {
            super.x = new Validator<Integer>(stringHumanBeing[1])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение координаты X должно быть целым числом")
                    .withCheckingPredicate(arg -> (int) arg < Coordinates.X_MAX,
                            "Значение координаты X должно быть не больше " + Coordinates.X_MAX)
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
        try {
            super.y = new Validator<Integer>(stringHumanBeing[2])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение координаты Y должно быть целым числом")
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setRealHero() {
        try {
            super.realHero = new Validator<Boolean>(stringHumanBeing[3])
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckBoolean::checkBoolean, "значение \"Это реальный герой\" должно быть Да или Нет")
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setHasToothpick() {
        try {
            super.hasToothpick = new Validator<Boolean>(stringHumanBeing[4])
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckBoolean::checkBoolean, "значение \"У человека есть зубочистка\" должно быть Да или Нет")
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setImpactSpeed() {
        try {
            super.impactSpeed = new Validator<Double>(stringHumanBeing[5])
                    .withCheckingNull(false)
                    .withCheckingFunction(Double::parseDouble, "значение скорости удара должно быть вещественным числом")
                    .withCheckingPredicate(arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                            "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN)
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setSoundtrackName() {
        try {
            super.soundtrackName = new Validator<String>(stringHumanBeing[6])
                    .withCheckingNull(false)
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setMinutesOfWaiting() {
        try {
            super.minutesOfWaiting = new Validator<Integer>(stringHumanBeing[7])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение времени ожидания должно быть целым числом")
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setWeaponType() {
        try {
            super.weaponType = new Validator<WeaponType>(stringHumanBeing[8])
                    .withCheckingNull(false)
                    .withCheckingFunction(WeaponType::valueOf,
                            "тип оружия должен быть из списка: \n" + WeaponType.show() + "Регистр должен сохраняться")
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }

    public void setCar() {
        try {
            super.cool = new Validator<Boolean>(stringHumanBeing[9])
                    .withCheckingNull(true)
                    .withCheckingFunction(CheckBoolean::checkBoolean,
                            "значение \"У человека есть крутая машина\" должно быть Да или Нет, или быть пустым")
                    .getValue();
        } catch (IllegalArgumentException e) {
            super.errors.add(e.getMessage());
        }
    }
}
