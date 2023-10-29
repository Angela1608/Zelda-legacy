package zelda.link;

import zelda.karacter.Direction;
import zelda.scene.HouseScene;

public class DeathState extends LinkState {

    private static final String[] DEATH_RIGHT_ANIMATION = {"Link hit right", "Link death right", "Link death right 2"};
    private static final String[] DEATH_LEFT_ANIMATION = {"Link hit left", "Link death left", "Link death left 2"};
    private static final int ANIMATION_INTERVAL = 700;
    private static final int DEFAULT_HEALTH = 2;
    private long oldAnimationInterval;

    public DeathState(Link link, Direction direction) {
        super(link);
        name = "DeathState";
        link.setAnimationInterval(ANIMATION_INTERVAL);
        setDeathAnimation(link, direction);
    }

    @Override
    public void handleAnimation() {
        int animationCounter = link.getAnimationCounter();

        if (animationCounter == link.getAnimation().length) {
            link.setAnimationInterval(oldAnimationInterval);
            link.setState(new StandState(link));
            link.setHealth(DEFAULT_HEALTH);
            game.setScene(new HouseScene(game, "GameStart"));
        }
    }

    private void setDeathAnimation(Link link, Direction direction) {
        switch (direction) {
            case UP, LEFT -> link.setAnimation(DEATH_LEFT_ANIMATION);
            case DOWN, RIGHT -> link.setAnimation(DEATH_RIGHT_ANIMATION);
        }
    }
}
