package zelda.enemy;

import zelda.karacter.Direction;
import zelda.karacter.Karacter;
import zelda.karacter.KaracterState;

public class TransState extends KaracterState {

    private final static String[] DOWN_ANIMATION = {"Stand down", "Stand down", "Stand down", "Stand down",
            "Stand down", "Stand down", "Stand down", "Stand down", "Stand down", "Stand down"};
    private final static String[] UP_ANIMATION = {"Stand up", "Stand up", "Stand up", "Stand up", "Stand up",
            "Stand up", "Stand up", "Stand up", "Stand up", "Stand up"};
    private final static String[] LEFT_ANIMATION = {"Stand left", "Stand left", "Stand left", "Stand left",
            "Stand left", "Stand left", "Stand left", "Stand left", "Stand left", "Stand left"};
    private final static String[] RIGHT_ANIMATION = {"Stand right", "Stand right", "Stand right", "Stand right",
            "Stand right", "Stand right", "Stand right", "Stand right", "Stand right", "Stand right"};
    private static final int MOVEMENT_OFFSET = 4;
    private static final int TRANS_STATE_ANIMATION_INTERVAL = 40;
    private static final int WALK_STATE_ANIMATION_INTERVAL = 90;
    private final Direction direction;

    public TransState(Karacter karacter, Direction direction) {
        super(karacter);
        name = "TransState";
        karacter.setAnimationInterval(TRANS_STATE_ANIMATION_INTERVAL);
        this.direction = direction;
    }

    @Override
    public void handleAnimation() {
        int animationCounter = karacter.getAnimationCounter();

        if (animationCounter == karacter.getAnimation().length) {
            karacter.setAnimationInterval(WALK_STATE_ANIMATION_INTERVAL);
            karacter.setState(new WalkState(karacter));
            return;
        }
        switch (direction) {
            case UP -> moveDown();
            case DOWN -> moveUp();
            case LEFT -> moveRight();
            case RIGHT -> moveLeft();
        }
    }

    private void moveLeft() {
        karacter.setAnimation(LEFT_ANIMATION);
        karacter.setX(karacter.getX() + MOVEMENT_OFFSET);
    }

    private void moveRight() {
        karacter.setAnimation(RIGHT_ANIMATION);
        karacter.setX(karacter.getX() - MOVEMENT_OFFSET);
    }

    private void moveUp() {
        karacter.setAnimation(UP_ANIMATION);
        karacter.setY(karacter.getY() + MOVEMENT_OFFSET);
    }

    private void moveDown() {
        karacter.setAnimation(DOWN_ANIMATION);
        karacter.setY(karacter.getY() - MOVEMENT_OFFSET);
    }
}
