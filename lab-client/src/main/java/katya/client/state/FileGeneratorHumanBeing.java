import katya.client.state.CheckBoolean;
import katya.client.state.State;
import katya.common.entites.HumanBeing;

import java.util.ArrayDeque;
import java.util.function.Function;
import java.util.function.Predicate;

public class FileGeneratorHumanBeing extends State {
    ArrayDeque<String> errors = new ArrayDeque<String>();
    String[] stringHumanBeing;

    public FileGeneratorHumanBeing(String[] stringHumanBeing) {
        this.stringHumanBeing = stringHumanBeing;
    }


    @Override
    void generateHumanBeingFields() {
        setName();
        setCoordinates();
        setRealHero();
        setHasToothpick();
        setImpactSpeed();
        setSoundtrackName();
        setMinutesOfWaiting();
        setWeaponType();
        setCar();
    }

    @Override
    public boolean isCorrect() {
        if (errors.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void errorHandler() {
        while (!errors.isEmpty()) {
            System.out.println(errors.remove());
        }
    }

    private static class ValueSetter<T> {
        private String string;
        private T value;

        public ValueSetter(String string) {
            this.string = string;
        }

        public ValueSetter<T> withCheckingNull(boolean nullable) {
            if ("".equals(string)) {
                if (nullable) {
                    value = null;
                } else {
                    throw new IllegalArgumentException("Значение не может быть пустой строкой");
                }
            }
            return this;
        }

        public ValueSetter<T> withCheckingFunction(Function<String, T> function, String description) {
            if (!"".equals(string)) {
                try {
                    value = function.apply(string);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Ошибка при обработке значения, " + description);
                }
            }
            return this;
        }

        public ValueSetter<T> withCheckingPredicate(Predicate<Object> predicate, String error) {
            if (!"".equals(string)) {
                if (!predicate.test(value)) {
                    throw new IllegalArgumentException(error);
                }
            }
            return this;
        }

        public T getValue() {
            if (value.getClass().equals(String.class) && !"".equals(string)) {
                value = (T) string;
            }
            return value;
        }
    }

    public void setName() {
        try {
            super.name = new ValueSetter<String>(stringHumanBeing[0])
                    .withCheckingNull(false)
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setCoordinates() {
        try {
            super.x = new ValueSetter<Integer>(stringHumanBeing[1])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение координаты X должно быть целым числом")
                    .withCheckingPredicate(arg -> (int) arg < HumanBeing.Coordinates.X_MAX,
                            "Значение координаты X должно быть не больше " + HumanBeing.Coordinates.X_MAX)
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            super.y = new ValueSetter<Integer>(stringHumanBeing[2])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение координаты Y должно быть целым числом")
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setRealHero() {
        try {
            super.realHero = new ValueSetter<Boolean>(stringHumanBeing[3])
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckBoolean::checkBoolean, "значение \"Это реальный герой\" должно быть Да или Нет")
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setHasToothpick() {
        try {
            super.hasToothpick = new ValueSetter<Boolean>(stringHumanBeing[4])
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckBoolean::checkBoolean, "значение \"У человека есть зубочистка\" должно быть Да или Нет")
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setImpactSpeed() {
        try {
            super.impactSpeed = new ValueSetter<Double>(stringHumanBeing[5])
                    .withCheckingNull(false)
                    .withCheckingFunction(Double::parseDouble, "значение скорости удара должно быть вещественным числом")
                    .withCheckingPredicate(arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                            "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN)
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setSoundtrackName() {
        try {
            super.soundtrackName = new ValueSetter<String>(stringHumanBeing[6])
                    .withCheckingNull(false)
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setMinutesOfWaiting() {
        try {
            super.minutesOfWaiting = new ValueSetter<Integer>(stringHumanBeing[7])
                    .withCheckingNull(false)
                    .withCheckingFunction(Integer::parseInt, "значение времени ожидания должно быть целым числом")
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setWeaponType() {
        try {
            super.weaponType = new ValueSetter<HumanBeing.WeaponType>(stringHumanBeing[8])
                    .withCheckingNull(false)
                    .withCheckingFunction(HumanBeing.WeaponType::valueOf,
                            "тип оружия должен быть из списка: \n" + HumanBeing.WeaponType.show() + "Регистр должен сохраняться")
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public void setCar() {
        try {
            super.cool = new ValueSetter<Boolean>(stringHumanBeing[9])
                    .withCheckingNull(true)
                    .withCheckingFunction(CheckBoolean::checkBoolean,
                            "значение \"У человека есть крутая машина\" должно быть Да или Нет, или быть пустым")
                    .getValue();
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
}


    /*

    public static <T> T setValue(String string,
                                 String description,
                                 boolean nullable,
                                 Function<String, T> function,
                                 Predicate<Object> predicate,
                                 String error) {

        if ("".equals(string)) {
            if (nullable) {
                return null;
            } else {
                throw new IllegalArgumentException("Значение не может быть пустой строкой");
            }
        }
        T value = null;
        try {
            value = function.apply(string);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка при обработке значения, " + description);
        }
        try {
            Validator.validate(value, predicate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(error);
        }
        return value;
    }









    @Override
    public HumanBeing generateHumanBeing() {
        return null;
    }

    public static <T> T setValue(String string,
                                    String description,
                                    boolean nullable,
                                    Function<String, T> function,
                                    Predicate<Object> predicate,
                                    String error) {

        if ("".equals(string)) {
            if (nullable) {
                return null;
            } else {
                throw new IllegalArgumentException("Значение не может быть пустой строкой");
            }
        }
        T value = null;
        try {
            value = function.apply(string);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка при обработке значения, " + description);
        }
        try {
            Validator.validate(value, predicate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(error);
        }
        return value;
    }

    public static <T> T setValue(String string,
                                    String description,
                                    boolean nullable,
                                    Function<String, T> function) {
        if ("".equals(string)) {
            if (nullable) {
                return null;
            } else {
                throw new IllegalArgumentException("Значение не может быть пустой строкой");
            }
        }
        T value = null;
        try {
            value = function.apply(string);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ошибка при обработке значения, " + description);
        }
        return value;
    }

    public static String setValue(String string,
                                     boolean nullable) {
        if ("".equals(string)) {
            if (nullable) {
                return null;
            } else {
                throw new IllegalArgumentException("Значение не может быть пустой строкой");
            }
        }
        return string;
    }
    @Override
    public void setName() {
        try {
            super.name = setValue(stringHumanBeing[0],
                    false);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    @Override
    public void setCoordinates() {
        try {
            super.x = setValue(stringHumanBeing[1],
                    "значение координаты X должно быть целым числом",
                    false,
                    Integer::parseInt,
                    arg -> (int) arg < HumanBeing.Coordinates.X_MAX,
                    "Значение координаты X должно быть не больше " + HumanBeing.Coordinates.X_MAX);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            super.y = setValue(stringHumanBeing[2],
                    "значение координаты Y должно быть целым числом",
                    false,
                    Integer::parseInt);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setRealHero() {
        try {
            super.realHero = setValue(stringHumanBeing[3],
                    "значение \"Это реальный герой\" должно быть Да или Нет",
                    false,
                    CheckBoolean::checkBoolean);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setHasToothpick() {
        try {
            super.hasToothpick = setValue(stringHumanBeing[4],
                    "значение \"У человека есть зубочистка\" должно быть Да или Нет",
                    false,
                    CheckBoolean::checkBoolean);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setImpactSpeed() {
        try {
            super.impactSpeed = setValue(stringHumanBeing[5],
                    "значение скорости удара должно быть вещественным числом",
                    false,
                    Double::parseDouble,
                    arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                    "Значение скорости удара должно быть больше " + HumanBeing.IMPACT_SPEED_MIN);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setSoundtrackName() {
        try {
            super.soundtrackName = setValue(stringHumanBeing[6],
                    false);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setMinutesOfWaiting() {
        try {
            super.minutesOfWaiting = setValue(stringHumanBeing[7],
                    "значение времени ожидания должно быть целым числом",
                    false,
                    Integer::parseInt);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setWeaponType() {
        try {
            super.weaponType = setValue(stringHumanBeing[8],
                    "тип оружия должен быть из списка: \n" + HumanBeing.WeaponType.show() + "Регистр должен сохраняться",
                    false,
                    HumanBeing.WeaponType::valueOf);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }
    @Override
    public void setCar() {
        try {
            super.cool = setValue(stringHumanBeing[9],
                    "значение \"У человека есть крутая машина\" должно быть Да или Нет, или быть пустым",
                    true,
                    CheckBoolean::checkBoolean);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }


}


