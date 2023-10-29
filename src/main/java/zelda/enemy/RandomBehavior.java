package zelda.enemy;

import java.util.Random;
import zelda.karacter.Direction;

import static java.lang.System.currentTimeMillis;

public class RandomBehavior extends Behavior {

    private static final long INPUT_INTERVAL = 5000;
    private static final Direction[] DIRECTIONS =
            {Direction.UP, Direction.LEFT, Direction.RIGHT, Direction.DOWN};

    private final WhiteSoldier soldier;
    private long lastInput = currentTimeMillis();
    private final Random random = new Random();

    public RandomBehavior(WhiteSoldier soldier) {
        this.soldier = soldier;
    }

    public void behave() {
        if (currentTimeMillis() > lastInput + INPUT_INTERVAL) {
            int randomIndex = random.nextInt(DIRECTIONS.length);
            Direction randomDirection = DIRECTIONS[randomIndex];
            soldier.setDirection(randomDirection);
            lastInput = currentTimeMillis();
        }
    }
}
