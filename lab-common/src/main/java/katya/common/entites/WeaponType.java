package katya.common.entites;

import java.io.Serializable;

public enum WeaponType implements Serializable {
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
