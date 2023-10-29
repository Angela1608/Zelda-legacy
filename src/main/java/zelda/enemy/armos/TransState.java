package zelda.enemy.armos;

import zelda.karacter.Direction;
import zelda.karacter.Karacter;
import zelda.karacter.KaracterState;

public class TransState extends KaracterState {

    private static final String[] HIT_ANIMATION = {
            "hit 1", "hit 2", "hit 3", "hit 4", "hit 5", "hit 6", "hit 7", "hit 8", "hit 9", "hit 10"};
    private static final int FIRST_ANIMATION_INTERVAL = 10;
    private static final int SECOND_ANIMATION_INTERVAL = 90;
    private static final int MOVE_AMOUNT = 8;
    private final Direction direction;

    public TransState(Karacter armosKnight, Direction direction) {
        super(armosKnight);
        name = "TransState";
        karacter.setAnimationInterval(FIRST_ANIMATION_INTERVAL);
        this.direction = direction;
    }

    @Override
    public void handleAnimation() {
        int animationCounter = karacter.getAnimationCounter();
        if (animationCounter == karacter.getAnimation().length) {
            karacter.setAnimationInterval(SECOND_ANIMATION_INTERVAL);
            karacter.setState(new AttackState(karacter));
        }
        move();
    }

    private void move() {
        karacter.setAnimation(HIT_ANIMATION);
        int newX = karacter.getX();
        int newY = karacter.getY();
        switch (direction) {
            case UP -> newY -= MOVE_AMOUNT;
            case DOWN -> newY += MOVE_AMOUNT;
            case LEFT -> newX -= MOVE_AMOUNT;
            case RIGHT -> newX += MOVE_AMOUNT;
        }
        karacter.setX(newX);
        karacter.setY(newY);
    }
}
