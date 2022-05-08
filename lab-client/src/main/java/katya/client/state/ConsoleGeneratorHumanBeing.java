package katya.client.state;

import katya.common.entites.HumanBeing;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConsoleGeneratorHumanBeing extends State {

    void setName() {

    }

    void setCoordinates() {

    }

    void setRealHero() {

    }

    void setHasToothpick() {

    }

    void setImpactSpeed() {

    }

    void setSoundtrackName() {

    }

    void setMinutesOfWaiting() {

    }

    void setWeaponType() {

    }

    void setCar() {

    }

    @Override
    void generateHumanBeingFields() {

    }

    @Override
    boolean isCorrect() {
        return false;
    }

    @Override
    void errorHandler() {

    }
}
    /*
    private Scanner scanner = new Scanner(System.in);

    private <T> T setValue(String message,
                           String description,
                           boolean nullable,
                           Function<String, T> function,
                           Predicate<Object> predicate,
                           String error) {
        System.out.println(message + ", " + description);
        boolean isRunning = true;
        T value = null;
        while (isRunning) {
            try {
                String string = scanner.nextLine();
                value = Parser.valueParser(string, description, nullable, function, predicate, error);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ".\nПовторите ввод");
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Введен недопустимый символ");
                System.exit(1);
            }
        }
        return value;
    }

    private String setValue(String message,
                            boolean nullable) {
        System.out.println(message);
        boolean isRunning = true;
        String value = null;
        while (isRunning) {
            try {
                String string = scanner.nextLine();
                value = Parser.valueParser(string, nullable);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ".\nПовторите ввод");
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Введен недопустимый символ");
                System.exit(1);
            }
        }
        return value;
    }


    private <T> T setValue(String message,
                           String description,
                           boolean nullable,
                           Function<String, T> function) {
        System.out.println(message + ", " + description);
        boolean isRunning = true;
        T value = null;
        while (isRunning) {
            try {
                String string = scanner.nextLine();
                value = Parser.valueParser(string, description, nullable, function);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ".\nПовторите ввод");
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("Введен недопустимый символ");
                System.exit(1);
            }
        }
        return value;
    }
    @Override
    public void setName() {
        super.name = setValue("Введите имя",
                false);
    }

    @Override
    public void setCoordinates() {

    }

    @Override
    public void setCoordinate() {
        super.x = setValue("Введите координату X",
                "значение должно быть целым числом не больше " + HumanBeing.Coordinates.X_MAX,
                false,
                Integer::parseInt,
                arg -> (int) arg < HumanBeing.Coordinates.X_MAX,
                "Координата X должна быть не больше " + HumanBeing.Coordinates.X_MAX);

        super.y = setValue("Введите координату Y",
                "значение должно быть целым числом",
                false,
                Integer::parseInt);
    }

    @Override
    public void setRealHero() {
        super.realHero = setValue("Это реальный герой или нет?",
                "значение должно быть Да или Нет",
                false,
                CheckBoolean::checkBoolean);
    }

    @Override
    public void setHasToothpick() {
        super.hasToothpick = setValue("У героя есть зубочистка?",
                "значение должно быть Да или Нет",
                false,
                CheckBoolean::checkBoolean);
    }

    @Override
    public void setImpactSpeed() {
        super.impactSpeed = setValue("Введите скорость удара",
                "значение должно быть вещественным числом и больше " + HumanBeing.IMPACT_SPEED_MIN,
                false,
                Double::parseDouble,
                arg -> (Double) arg > HumanBeing.IMPACT_SPEED_MIN,
                "Значение должно быть больше " + HumanBeing.IMPACT_SPEED_MIN);
    }

    @Override
    public void setSoundtrackName() {
        super.soundtrackName = setValue("Введите название саундтрека",
                false);
    }
    @Override
    public void setMinutesOfWaiting() {
        super.minutesOfWaiting = setValue("Введите время ожидания",
                "значение должно быть целым числом",
                false,
                Integer::valueOf);
    }
    @Override
    public void setWeaponType() {
        super.weaponType = setValue("Введите тип оружия",
                "допустимые значения: \n" + HumanBeing.WeaponType.show() + "регистр должен сохраняться",
                false,
                HumanBeing.WeaponType::valueOf);
    }
    @Override
    public void setCar() {
        super.cool = setValue("Машина крутая?",
                "значение должно быть Да или Нет. Если хотите оставить это значение, пустым нажмите enter",
                true,
                CheckBoolean::checkBoolean);
    }

    @Override
    public boolean isCorrect() {
        return false;
    }

    @Override
    public void errorHandler() {

    }

    @Override
    public HumanBeing generateHumanBeing() {
        return null
        }
        }

     */