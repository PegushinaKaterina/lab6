package katya.common.state;

import katya.common.entites.WeaponType;

import java.util.ArrayDeque;
import java.util.Scanner;

public abstract class State {
    private String name;
    private int x;
    private int y;
    private Boolean realHero;
    private boolean hasToothpick;
    private Double impactSpeed;
    private String soundtrackName;
    private Integer minutesOfWaiting;
    private WeaponType weaponType;
    private Boolean cool;
    private Scanner scanner;
    private ArrayDeque<String> errors = new ArrayDeque<>();

    public State(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void generateHumanBeingFields();

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

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Boolean getCool() {
        return cool;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public void setImpactSpeed(Double impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }

    public void setMinutesOfWaiting(Integer minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setCool(Boolean cool) {
        this.cool = cool;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setErrors(ArrayDeque<String> errors) {
        this.errors = errors;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ArrayDeque<String> getErrors() {
        return errors;
    }
}
