package zelda.enemy;

import zelda.karacter.Direction;
import zelda.karacter.Karacter;
import zelda.karacter.KaracterState;

public class WalkState extends KaracterState {

    private static final String[] DOWN_ANIMATION = {"Stand down", "Walk down 1", "Walk down 2", "Walk down 3"};
    private static final String[] UP_ANIMATION = {"Stand up", "Walk up 1", "Walk up 2"};
    private static final String[] LEFT_ANIMATION = {"Stand left", "Walk left 1", "Walk left 2"};
    private static final String[] RIGHT_ANIMATION = {"Stand right", "Walk right 1", "Walk right 2"};
    private static final int WALK_SPEED = 2;
    private static final int ANIMATION_INTERVAL = 90;
    private static final int LEFT_DELTA_X = -5;
    private static final int RIGHT_DELTA_X = 5;
    private static final int CHARACTER_WIDTH_LEFT = 29;
    private static final int CHARACTER_WIDTH_RIGHT = 29;
    private static final int CHARACTER_WIDTH_UP_DOWN = 22;

    public WalkState(Karacter soldier) {
        super(soldier);
        name = "WalkState";
        karacter.setAnimationInterval(ANIMATION_INTERVAL);
    }

    @Override
    public void handleAnimation() {
        int animationCounter = karacter.getAnimationCounter();

        if (animationCounter >= 0 && animationCounter < karacter.getAnimation().length) {
            Direction dir = karacter.getDirection();
            int deltaX = 0;

            if (dir == Direction.LEFT && animationCounter == 1) {
                deltaX = LEFT_DELTA_X;
            } else if (dir == Direction.LEFT && animationCounter == 2) {
                deltaX = RIGHT_DELTA_X;
            }
            karacter.setX(karacter.getX() + deltaX);
        }
    }

    @Override
    public void handleInput() {
        switch (karacter.getDirection()) {
            case UP -> up();
            case DOWN -> down();
            case LEFT -> left();
            case RIGHT -> right();
        }
    }

    private void left() {
        setAnimation(LEFT_ANIMATION,
                Direction.LEFT,
                LEFT_DELTA_X,
                CHARACTER_WIDTH_LEFT);
    }

    private void right() {
        setAnimation(RIGHT_ANIMATION,
                Direction.RIGHT,
                RIGHT_DELTA_X,
                CHARACTER_WIDTH_RIGHT);
    }

    private void up() {
        setAnimation(UP_ANIMATION,
                Direction.UP,
                -WALK_SPEED,
                CHARACTER_WIDTH_UP_DOWN);
    }

    private void down() {
        setAnimation(DOWN_ANIMATION,
                Direction.DOWN, WALK_SPEED,
                CHARACTER_WIDTH_UP_DOWN);
    }

    private void setAnimation(String[] animation, Direction direction, int deltaX, int width) {
        if (karacter.getAnimation() != animation) {
            karacter.setAnimation(animation);
        }
        if (karacter.getDirection() != direction) {
            karacter.setDirection(direction);
        }
        karacter.setWidth(width);
        karacter.setX(karacter.getX() + deltaX);
    }
}
