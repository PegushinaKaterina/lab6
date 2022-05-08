package katya.client.state;

import katya.common.entites.HumanBeing;

public abstract class State {
    protected String name;
    protected int x;
    protected int y;
    protected Boolean realHero;
    protected boolean hasToothpick;
    protected Double impactSpeed;
    protected String soundtrackName;
    protected Integer minutesOfWaiting;
    protected HumanBeing.WeaponType weaponType;
    protected Boolean cool;

    protected abstract void generateHumanBeingFields();

    abstract boolean isCorrect();

    abstract void errorHandler();

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public boolean getHasToothpick() {
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

    public HumanBeing.WeaponType getWeaponType() {
        return weaponType;
    }

    public Boolean getCool() {
        return cool;
    }
}
