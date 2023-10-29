package zelda.link;

public class StandState extends LinkState {

    private static final String[] STAND_DOWN_ANIMATION = {"Link stand down"};
    private static final String[] STAND_UP_ANIMATION = {"Link stand up"};
    private static final String[] STAND_LEFT_ANIMATION = {"Link stand left"};
    private static final String[] STAND_RIGHT_ANIMATION = {"Link stand right"};

    public StandState(Link link) {
        super(link);
        name = "StandState";
        setAnimationForDirection(link);
    }

    @Override
    public void handleInput() {
        if (game.isJPressed()) {
            link.setState(new SwordState(link));
        } else if (game.isLPressed()) {
            link.dropBomb();
        } else if (game.isKPressed()) {
            link.shootArrow();
        } else {
            if (link.moveInput())
                link.setState(new WalkState(link));
        }
    }

    private void setAnimationForDirection(Link link) {
        switch (link.getDirection()) {
            case UP -> link.setAnimation(STAND_UP_ANIMATION);
            case DOWN -> link.setAnimation(STAND_DOWN_ANIMATION);
            case LEFT -> link.setAnimation(STAND_LEFT_ANIMATION);
            case RIGHT -> link.setAnimation(STAND_RIGHT_ANIMATION);
        }
    }
}
