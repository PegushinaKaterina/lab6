package katya.common.entites;

public class HumanBeingBuilder {
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

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public Double getImpactSpeed() {
        return impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public Integer getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Car getCar() {
        return car;
    }
}
