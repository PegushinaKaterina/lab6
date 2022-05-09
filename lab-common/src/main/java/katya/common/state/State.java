package katya.common.state;

import katya.common.entites.HumanBeing;

import java.util.ArrayDeque;
import java.util.Scanner;

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
    Scanner scanner;
    protected ArrayDeque<String> errors = new ArrayDeque<String>();
    public State(Scanner scanner){
        this.scanner = scanner;
    }

    protected abstract void generateHumanBeingFields();

    public boolean isCorrect() {
        return errors.isEmpty();
    }

    public void errorHandler() {
        while (!errors.isEmpty()) {
            System.out.println(errors.remove());
        }
    }

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
