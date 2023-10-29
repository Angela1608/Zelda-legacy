package zelda.items;

import zelda.enemy.Behavior;

import static java.lang.System.currentTimeMillis;

public class BombBehavior extends Behavior {

    private static final long ANIMATION_INTERVAL = 50;
    private final Bomb bomb;
    private long lastAnimation = currentTimeMillis();
    private int ticks = 0;

    public BombBehavior(Bomb bomb) {
        this.bomb = bomb;
    }

    public void behave() {
        if (currentTimeMillis() > lastAnimation + ANIMATION_INTERVAL) {
            if (ticks == 49) {
                bomb.setAlive(false);
            }
            lastAnimation = currentTimeMillis();
            ticks++;
        }
    }
}
