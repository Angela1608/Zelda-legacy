package zelda.link;

import zelda.karacter.Direction;

public class TransState extends LinkState {

    private static final String[] DOWN_ANIMATION = {"Link stand down", "Link stand down", "Link stand down",
            "Link stand down", "Link stand down", "Link stand down", "Link stand down", "Link stand down",
            "Link stand down", "Link stand down"};
    private static final String[] UP_ANIMATION = {"Link stand up", "Link stand up", "Link stand up", "Link stand up",
            "Link stand up", "Link stand up", "Link stand up", "Link stand up", "Link stand up", "Link stand up"};
    private static final String[] LEFT_ANIMATION = {"Link stand left", "Link stand left", "Link stand left",
            "Link stand left", "Link stand left", "Link stand left", "Link stand left", "Link stand left",
            "Link stand left", "Link stand left"};
    private static final String[] RIGHT_ANIMATION = {"Link stand right", "Link stand right", "Link stand right",
            "Link stand right", "Link stand right", "Link stand right", "Link stand right", "Link stand right",
            "Link stand right", "Link stand right"};
    private final Direction direction;
    private static final int MOVE_DISTANCE = 4;
    private static final int ANIMATION_INTERVAL = 90;

    public TransState(Link link, Direction direction) {
        super(link);
        name = "TransState";
        link.setAnimationInterval(10);
        this.direction = direction;
    }

    @Override
    public void handleAnimation() {
        int animationCounter = link.getAnimationCounter();
        if (animationCounter == link.getAnimation().length) {
            link.setAnimationInterval(ANIMATION_INTERVAL);
            link.setState(new WalkState(link));
        }
        switch (direction) {
            case UP -> down();
            case DOWN -> up();
            case LEFT -> right();
            case RIGHT -> left();
        }
    }

    private void left() {
        link.setAnimation(LEFT_ANIMATION);
        link.setX(link.getX() + MOVE_DISTANCE);
    }

    private void right() {
        link.setAnimation(RIGHT_ANIMATION);
        link.setX(link.getX() - MOVE_DISTANCE);
    }

    private void up() {
        link.setAnimation(UP_ANIMATION);
        link.setY(link.getY() + MOVE_DISTANCE);
    }

    private void down() {
        link.setAnimation(DOWN_ANIMATION);
        link.setY(link.getY() - MOVE_DISTANCE);
    }
}
