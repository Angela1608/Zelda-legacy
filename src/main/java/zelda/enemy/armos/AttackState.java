package zelda.enemy.armos;

import zelda.karacter.Karacter;
import zelda.karacter.KaracterState;

public class AttackState extends KaracterState {

    private static final String[] ANIMATION_FRAMES = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final int ANIMATION_INTERVAL = 90;

    public AttackState(Karacter armosKnight) {
        super(armosKnight);
        name = "AttackState";
        armosKnight.setAnimationInterval(ANIMATION_INTERVAL);
    }

    public void handleInput() {
        switch (karacter.getDirection()) {
            case UP, DOWN, LEFT, RIGHT -> animation();
        }
    }

    public void animation() {
        karacter.setAnimation(ANIMATION_FRAMES);
    }
}
