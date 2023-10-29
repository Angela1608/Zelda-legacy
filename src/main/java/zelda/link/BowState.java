package zelda.link;

import zelda.items.Arrow;
import zelda.karacter.Direction;

public class BowState extends LinkState {

    private static final String[] downAnimation = {"Link bow down 1", "Link bow down 2", "Link bow down 3"};
    private static final String[] upAnimation = {"Link bow up 1", "Link bow up 2", "Link bow up 3"};
    private static final String[] leftAnimation = {"Link bow left 1", "Link bow left 2", "Link bow left 3"};
    private static final String[] rightAnimation = {"Link bow right 1", "Link bow right 2"};
    private static final int ARROW_X_OFFSET = 7;
    private static final int ARROW_Y_OFFSET = 9;
    private static final int UP_ANIMATION_X_OFFSET_FIRST = -2;
    private static final int UP_ANIMATION_X_OFFSET_SECOND = -3;
    private static final int LEFT_ANIMATION_X_OFFSET_FIRST = 2;
    private static final int LEFT_ANIMATION_X_OFFSET_SECOND = -2;
    private static final int LEFT_ANIMATION_Y_OFFSET_FIRST = 1;
    private final int oldX;
    private final int oldY;

    public BowState(Link link) {
        super(link);
        name = "BowState";
        oldX = link.getX();
        oldY = link.getY();
        link.setCheckCollision(false);
        initializeBowAnimation(link);
    }

    @Override
    public void handleAnimation() {
        int animationCounter = link.getAnimationCounter();
        if (animationCounter == link.getAnimation().length) {
            restoreOriginalPosition();
        } else {
            Direction dir = link.getDirection();
            if (dir == Direction.UP) {
                handleUpAnimation(animationCounter);
            } else if (dir == Direction.LEFT) {
                handleLeftAnimation(animationCounter);
            }
        }
    }

    private void restoreOriginalPosition() {
        link.setY(oldY);
        link.setX(oldX);
        link.setCheckCollision(true);
        link.setState(new StandState(link));
    }

    private void handleUpAnimation(int animationCounter) {
        switch (animationCounter) {
            case 0 -> link.setX(link.getX() + UP_ANIMATION_X_OFFSET_FIRST);
            case 1 -> link.setX(link.getX() + UP_ANIMATION_X_OFFSET_SECOND);
        }
    }

    private void handleLeftAnimation(int animationCounter) {
        switch (animationCounter) {
            case 0 -> {
                link.setX(link.getX() + LEFT_ANIMATION_X_OFFSET_FIRST);
                link.setY(link.getY() + LEFT_ANIMATION_Y_OFFSET_FIRST);
            }
            case 1 -> link.setX(link.getX() + LEFT_ANIMATION_X_OFFSET_SECOND);
        }
    }

    private void initializeBowAnimation(Link link) {
        switch (link.getDirection()) {
            case UP -> setBowAnimation(link, upAnimation, 0, -ARROW_Y_OFFSET);
            case DOWN -> setBowAnimation(link, downAnimation, 0, ARROW_Y_OFFSET);
            case LEFT -> setBowAnimation(link, leftAnimation, -ARROW_X_OFFSET, 0);
            case RIGHT -> setBowAnimation(link, rightAnimation, ARROW_X_OFFSET, 0);
        }
    }

    private void setBowAnimation(Link link, String[] animation, int arrowOffsetX, int arrowOffsetY) {
        link.setAnimation(animation);
        game.getScene().addNewGObject(new Arrow(game, link.getX()
                + ARROW_X_OFFSET + arrowOffsetX, link.getY() + ARROW_Y_OFFSET + arrowOffsetY));
    }
}
