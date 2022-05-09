package katya.common.state;

import katya.common.entites.HumanBeing;

import java.util.Scanner;

public class GeneratorHumanBeing {
    private HumanBeing humanBeing = null;
    private State state;

    public void changeState(State state){
        this.state = state;
    }

    public void generateHumanBeing(){
        state.generateHumanBeingFields();
        if (state.isCorrect()){
            this.humanBeing = new HumanBeing.HumanBeingBuilder()
                    .withName(state.getName())
                    .withCoordinates(state.getX(),state.getY())
                    .withRealHero(state.getRealHero())
                    .withHasToothpick(state.getHasToothpick())
                    .withImpactSpeed(state.getImpactSpeed())
                    .withSoundtrackName(state.getSoundtrackName())
                    .withMinutesOfWaiting(state.getMinutesOfWaiting())
                    .withWeaponType(state.getWeaponType())
                    .withCar(state.getCool())
                    .build();
        } else {
            state.errorHandler();
            throw new IllegalArgumentException();
        }
    }

    public HumanBeing getHumanBeing(){
        return humanBeing;
    }
}
