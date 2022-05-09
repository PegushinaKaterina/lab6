package katya.common.entites;

import java.util.Date;

public class HumanBeing implements Comparable<HumanBeing> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    public static Double IMPACT_SPEED_MIN = -484.;
    private final String name;
    private final Coordinates coordinates;
    private final Boolean realHero;
    private final boolean hasToothpick;
    private final Double impactSpeed;
    private final String soundtrackName;
    private final Integer minutesOfWaiting;
    private final WeaponType weaponType;
    private final Car car;

    private HumanBeing(HumanBeingBuilder builder) {
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.realHero = builder.realHero;
        this.hasToothpick = builder.hasToothpick;
        this.impactSpeed = builder.impactSpeed;
        this.soundtrackName = builder.soundtrackName;
        this.minutesOfWaiting = builder.minutesOfWaiting;
        this.weaponType = builder.weaponType;
        this.car = builder.car;
    }

    public static class HumanBeingBuilder {
        private String name;
        private Coordinates coordinates;
        private Boolean realHero;
        private boolean hasToothpick;
        private Double impactSpeed;
        private String soundtrackName;
        private Integer minutesOfWaiting;
        private WeaponType weaponType;
        private Car car = new Car(null);

        public HumanBeingBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HumanBeingBuilder withCoordinates(int x, int y) {
            this.coordinates = new Coordinates(x, y);
            return this;
        }

        public HumanBeingBuilder withRealHero(Boolean realHero) {
            this.realHero = realHero;
            return this;
        }

        public HumanBeingBuilder withHasToothpick(boolean hasToothpick) {
            this.hasToothpick = hasToothpick;
            return this;
        }

        public HumanBeingBuilder withImpactSpeed(Double impactSpeed) {
            this.impactSpeed = impactSpeed;
            return this;
        }

        public HumanBeingBuilder withSoundtrackName(String soundtrackName) {
            this.soundtrackName = soundtrackName;
            return this;
        }

        public HumanBeingBuilder withMinutesOfWaiting(Integer minutesOfWaiting) {
            this.minutesOfWaiting = minutesOfWaiting;
            return this;
        }

        public HumanBeingBuilder withWeaponType(WeaponType weaponType) {
            this.weaponType = weaponType;
            return this;
        }

        public HumanBeingBuilder withCar(Boolean cool) {
            this.car = new Car(cool);
            return this;
        }

        public HumanBeing build(){
            return new HumanBeing(this);
        }


    }

    public static class Coordinates {
        public static final int X_MAX = 877;
        private final int x; //Максимальное значение поля: 877
        private final int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static class Car {
        private final Boolean cool;

        public Car(Boolean cool) {
            this.cool = cool;
        }

        public Boolean getCool() {
            return cool;
        }

    }

    public static enum WeaponType {
        PISTOL,
        SHOTGUN,
        BAT;

        public static String show() {
            StringBuilder stringWeaponType = new StringBuilder();
            for (WeaponType i : values()) {
                stringWeaponType.append(i);
                stringWeaponType.append("\n");
            }
            return stringWeaponType.toString();
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId(){
        return id;
    }

    public Date getCreationDate(){
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean getRealHero() {
        return realHero;
    }

    public boolean getHasToothpick() {
        return hasToothpick;
    }

    public double getImpactSpeed() {
        return impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public int getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public int compareTo(HumanBeing humanBeing) {
        if (name.compareTo(humanBeing.getName()) == 0) {
            return Long.compare(id, humanBeing.getId());
        } else {
            return name.compareTo(humanBeing.getName());
        }
    }

    @Override
    public String toString() {
        return "Человек с именем " + name + "\n" +
                "   Скорость удара: " + impactSpeed +
                ", время ожидания: " + minutesOfWaiting;
        /*
          String stringRealHero;
        if (realHero == true) {
            stringRealHero = "Это реальный человек";
        } else {
            stringRealHero = "Это не реальный человек";
        }
        String stringHasToothpick;
        if (hasToothpick == true) {
            stringHasToothpick = "у него есть зубочистка";
        } else {
            stringHasToothpick = "у него нет зубочистки";
        }
        String stringCar;
        if (car.getCool() == null) {
            stringCar = ", о его машине ничего не известно";
        } else if (car.getCool() == false) {
            stringCar = " и нет крутой машины";
        } else {
            stringCar = " и есть крутая машина";
        }
        return "Человек с именем " + name + ", id - " + id + "\n" +
                "   Дата создания: " + creationDate + ", " +
                "Координаты: " + "x - " + coordinates.getX() + ", y - " +coordinates.getY() + "\n" +
                "   " + stringRealHero + ", " +stringHasToothpick + stringCar + ".\n"+
                "   Скорость удара: " + impactSpeed +
                ", cаундтрек: " + soundtrackName +
                ", время ожидания: " + minutesOfWaiting +
                ", Тип оружия:" + weaponType + "\n";
                */
    }

}
