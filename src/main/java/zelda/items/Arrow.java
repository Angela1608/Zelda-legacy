package zelda.items;

import java.awt.Rectangle;
import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.karacter.Direction;
import zelda.link.Link;

public class Arrow extends GObject {

    private static final String IMAGE_PATH = "images/arrows.png";
    private static final String SOUND_PATH = "sounds/bowArrow.mp3";
    private static final String[] ARROW_RIGHT = {"arrowRight"};
    private static final String[] ARROW_LEFT = {"arrowLeft"};
    private static final String[] ARROW_DOWN = {"arrowDown"};
    private static final String[] ARROW_UP = {"arrowUp"};
    private static final String[] ARROW_HIT_DOWN =
            {"arrowDown1", "arrowDown2", "arrowDown3", "arrowDown1", "arrowDown2", "arrowDown3"};
    private static final String[] ARROW_HIT_UP =
            {"arrowUp1", "arrowUp2", "arrowUp3", "arrowUp1", "arrowUp2", "arrowUp3"};
    private static final String[] ARROW_HIT_LEFT =
            {"arrowLeft1", "arrowLeft2", "arrowLeft3", "arrowLeft1", "arrowLeft2", "arrowLeft3"};
    private static final String[] ARROW_HIT_RIGHT =
            {"arrowRight1", "arrowRight2", "arrowRight3", "arrowRight1", "arrowRight2", "arrowRight3"};
    private static final int SPEED = 3;
    private static final int ARROW_WIDTH = 4;
    private static final int ARROW_HEIGHT = 13;
    private static final int ANIMATION_INTERVAL = 140;
    private boolean hit = false;
    private final Direction direction;

    public Arrow(Game game, int x, int y) {
        super(game, x, y, ARROW_WIDTH, ARROW_HEIGHT, IMAGE_PATH);
        initializeSpriteLoc();
        setAnimationInterval(ANIMATION_INTERVAL);
        direction = game.getLink().getDirection();
        initializeArrowDirection();
        game.playFx(SOUND_PATH);
    }

    @Override
    public void doInLoop() {
        if (x > game.getScene().getSprite().getWidth()
                || y > game.getScene().getSprite().getWidth() || x < 0 || y < 0) {
            alive = false;
            return;
        }
        moveArrow();
    }

    private void moveArrow() {
        switch (direction) {
            case UP -> setY(getY() - SPEED);
            case DOWN -> setY(getY() + SPEED);
            case LEFT -> setX(getX() - SPEED);
            case RIGHT -> setX(getX() + SPEED);
        }
    }

    @Override
    public void preAnimation() {
        if (hit) {
            liquid = true;
            if (animationCounter == ARROW_HIT_DOWN.length) {
                setAlive(false);
            }
        }
    }

    @Override
    public void wallCollision() {
        arrowHitSomething();
    }

    @Override
    public void collision(GObject obj) {
        if (obj instanceof Hittable hittable && !(obj instanceof Link) && !(obj instanceof Bush)) {
            hittable.hitBy(Weapon.ARROW);
            alive = false;
            arrowHitSomething();
        }
        if (obj instanceof Guard) {
            arrowHitSomething();
        }
        if (obj instanceof Bush) {
            handleBushCollision();
        }
    }

    private void handleBushCollision() {
        switch (direction) {
            case UP -> setYHardCore(getY() - SPEED);
            case DOWN -> setYHardCore(getY() + SPEED);
            case LEFT -> setXHardCore(getX() - SPEED);
            case RIGHT -> setXHardCore(getX() + SPEED);
        }
    }

    private void arrowHitSomething() {
        switch (direction) {
            case UP -> this.setAnimation(ARROW_HIT_UP);
            case DOWN -> this.setAnimation(ARROW_HIT_DOWN);
            case LEFT -> this.setAnimation(ARROW_HIT_LEFT);
            case RIGHT -> this.setAnimation(ARROW_HIT_RIGHT);
        }
        hit = true;
    }

    private void initializeSpriteLoc() {
        spriteLoc.put("arrowRight", new Rectangle(75, 0, 17, 6));
        spriteLoc.put("arrowLeft", new Rectangle(50, 0, 17, 6));
        spriteLoc.put("arrowDown", new Rectangle(0, 0, 6, 17));
        spriteLoc.put("arrowUp", new Rectangle(25, 0, 6, 17));
        spriteLoc.put("arrowDown1", new Rectangle(0, 25, 7, 12));
        spriteLoc.put("arrowDown2", new Rectangle(25, 25, 7, 12));
        spriteLoc.put("arrowDown3", new Rectangle(50, 25, 7, 12));
        spriteLoc.put("arrowUp1", new Rectangle(0, 50, 7, 12));
        spriteLoc.put("arrowUp2", new Rectangle(25, 50, 7, 12));
        spriteLoc.put("arrowUp3", new Rectangle(50, 50, 7, 12));
        spriteLoc.put("arrowRight1", new Rectangle(0, 75, 12, 7));
        spriteLoc.put("arrowRight2", new Rectangle(25, 75, 12, 7));
        spriteLoc.put("arrowRight3", new Rectangle(50, 75, 12, 7));
        spriteLoc.put("arrowLeft1", new Rectangle(0, 100, 12, 7));
        spriteLoc.put("arrowLeft2", new Rectangle(25, 100, 12, 7));
        spriteLoc.put("arrowLeft3", new Rectangle(50, 100, 12, 7));
    }

    private void initializeArrowDirection() {
        switch (direction) {
            case UP -> {
                sprite.setSprite(spriteLoc.get("arrowUp"));
                this.setAnimation(ARROW_UP);
                this.setHeight(ARROW_HEIGHT);
                this.setWidth(ARROW_WIDTH);
            }
            case DOWN -> {
                sprite.setSprite(spriteLoc.get("arrowDown"));
                this.setAnimation(ARROW_DOWN);
                this.setHeight(ARROW_HEIGHT);
                this.setWidth(ARROW_WIDTH);
            }
            case LEFT -> {
                sprite.setSprite(spriteLoc.get("arrowLeft"));
                this.setAnimation(ARROW_LEFT);
            }
            case RIGHT -> {
                sprite.setSprite(spriteLoc.get("arrowRight"));
                this.setAnimation(ARROW_RIGHT);
            }
        }
    }
}
