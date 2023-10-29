package zelda.collision;

public interface Hittable {

    /**
     * Handles the action of getting hit by the specified weapon.
     *
     * @param weapon The weapon used to hit the object.
     */
    void hitBy(Weapon weapon);
}
