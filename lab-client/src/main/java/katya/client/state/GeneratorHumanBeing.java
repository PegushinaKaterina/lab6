package katya.client.state;

import katya.common.entites.HumanBeing;

public class GeneratorHumanBeing {
    private HumanBeing humanBeing = null;
    State state;

    public GeneratorHumanBeing(State state){
        this.state = state;
    }

    public void changeState(State state){
        this.state = state;
    }

    public HumanBeing generateHumanBeing(){
        state.generateHumanBeingFields();
        if (state.isCorrect()){
            humanBeing = new HumanBeing.HumanBeingBuilder()
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
            throw new IllegalArgumentException();
        }
        return humanBeing;
    }
}
