package katya.common.entites;

import java.io.Serializable;

public class Car implements Serializable {
    private final Boolean cool;

    public Car(Boolean cool) {
        this.cool = cool;
    }

    public Boolean getCool() {
        return cool;
    }

}
