package zelda.link;

import java.awt.Rectangle;
import java.awt.geom.Area;
import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.engine.GObject;
import zelda.karacter.Direction;

public class SwordState extends LinkState {

    private final static String[] downAnimation = {"Link sword down 1", "Link sword down 2", "Link sword down 3",
            "Link sword down 4", "Link sword down 5", "Link sword down 6"};
    private final static String[] upAnimation = {"Link sword up 1", "Link sword up 2", "Link sword up 3",
            "Link sword up 4", "Link sword up 5", "Link sword up 6", "Link sword up 7", "Link sword up 8",
            "Link sword up 9"};
    private final static String[] leftAnimation = {"Link sword left 1", "Link sword left 2", "Link sword left 3",
            "Link sword left 4", "Link sword left 5", "Link sword left 6", "Link sword left 7", "Link sword left 8",
            "Link sword left 9"};
    private final static String[] rightAnimation = {"Link sword right 1", "Link sword right 2", "Link sword right 3",
            "Link sword right 4", "Link sword right 5", "Link sword right 6", "Link sword right 7", "Link sword right 8"};
    private static final String SWORD_SLASH_SOUND_UP = "sounds/swordSlash1.mp3";
    private static final String SWORD_SLASH_SOUND_DOWN = "sounds/swordSlash2.mp3";
    private static final String SWORD_SLASH_SOUND_LEFT = "sounds/swordSlash3.mp3";
    private static final String SWORD_SLASH_SOUND_RIGHT = "sounds/swordSlash4.mp3";
    private static final int SWORD_UP_OFFSET_X = 10;
    private static final int SWORD_UP_OFFSET_Y = 10;
    private static final int SWORD_UP_WIDTH = 30;
    private static final int SWORD_UP_HEIGHT = 10;
    private static final int SWORD_DOWN_OFFSET_X = 0;
    private static final int SWORD_DOWN_OFFSET_Y = 0;
    private static final int SWORD_DOWN_WIDTH = 25;
    private static final int SWORD_DOWN_HEIGHT = 10;
    private static final int SWORD_LEFT_OFFSET_X = 10;
    private static final int SWORD_LEFT_OFFSET_Y = 0;
    private static final int SWORD_LEFT_WIDTH = 20;
    private static final int SWORD_LEFT_HEIGHT = 30;
    private static final int SWORD_RIGHT_OFFSET_X = 0;
    private static final int SWORD_RIGHT_OFFSET_Y = 0;
    private static final int SWORD_RIGHT_WIDTH = 13;
    private static final int SWORD_RIGHT_HEIGHT = 28;
    private static final int UP_MOVE_1 = 1;
    private static final int UP_MOVE_2 = 2;
    private static final int UP_MOVE_3 = 6;
    private static final int UP_MOVE_4 = 1;
    private static final int UP_MOVE_5 = 2;
    private static final int UP_MOVE_6 = 2;
    private static final int UP_MOVE_7 = 3;
    private static final int LEFT_MOVE_1_X = 3;
    private static final int LEFT_MOVE_1_Y = -1;
    private static final int LEFT_MOVE_2 = 2;
    private static final int LEFT_MOVE_3_X = -5;
    private static final int LEFT_MOVE_4_X = -4;
    private static final int LEFT_MOVE_4_Y = 2;
    private static final int LEFT_MOVE_5 = 1;
    private static final int LEFT_MOVE_6 = 6;
    private static final int DOWN_MOVE_1_X = -4;
    private static final int DOWN_MOVE_2_X = -1;
    private static final int DOWN_MOVE_3_X = 1;
    private Rectangle sword;
    private final int oldX;
    private final int oldY;
    private final long oldAnimationInterval;

    public SwordState(Link link) {
        super(link);
        name = "SwordState";
        oldX = link.getX();
        oldY = link.getY();
        oldAnimationInterval = link.getAnimationInterval();
        link.setCheckCollision(false);
        setUpSwordAnimation(link);
        game.getScene().addHitter(sword);
    }

    @Override
    public void handleInput() {
        for (GObject obj : link.getGame().getScene().getGObjects()) {
            final Area area = new Area();
            area.add(new Area(sword));
            area.intersect(new Area(obj.getRectangle()));
            if ((obj instanceof Hittable hittable) && !area.isEmpty() && link != obj) {
                hittable.hitBy(Weapon.SWORD);
            }
        }
    }

    @Override
    public void handleAnimation() {
        int animationCounter = link.getAnimationCounter();
        if (animationCounter == link.getAnimation().length) {
            resetLinkState();
        } else {
            Direction dir = link.getDirection();
            if (dir == Direction.UP) {
                handleUpAnimation(animationCounter);
            } else if (dir == Direction.LEFT) {
                handleLeftAnimation(animationCounter);
            } else if (dir == Direction.DOWN) {
                handleDownAnimation(animationCounter);
            }
        }
    }

    private void resetLinkState() {
        link.setY(oldY);
        link.setX(oldX);
        link.setAnimationInterval(oldAnimationInterval);
        link.setCheckCollision(true);
        link.setState(new StandState(link));
        game.getScene().removeHitter(sword);
    }

    private void handleUpAnimation(int animationCounter) {
        switch (animationCounter) {
            case 0 -> link.setY(link.getY() + UP_MOVE_1);
            case 2 -> link.setY(link.getY() - UP_MOVE_2);
            case 3 -> link.setY(link.getY() - UP_MOVE_3);
            case 4 -> link.setY(link.getY() - UP_MOVE_4);
            case 6 -> {
                link.setY(link.getY() + UP_MOVE_5);
                link.setX(link.getX() - UP_MOVE_6);
            }
            case 7 -> {
                link.setY(link.getY() + UP_MOVE_5);
                link.setX(link.getX() - UP_MOVE_7);
            }
            case 8 -> {
                link.setY(link.getY() + UP_MOVE_7);
                link.setX(link.getX() - UP_MOVE_2);
            }
        }
    }

    private void handleLeftAnimation(int animationCounter) {
        switch (animationCounter) {
            case 0 -> {
                link.setY(link.getY() + LEFT_MOVE_1_Y);
                link.setX(link.getX() + LEFT_MOVE_1_X);
            }
            case 1, 3 -> link.setX(link.getX() - LEFT_MOVE_2);
            case 2 -> {
                link.setY(link.getY() + LEFT_MOVE_1_Y);
                link.setX(link.getX() + LEFT_MOVE_3_X);
            }
            case 4 -> {
                link.setY(link.getY() + LEFT_MOVE_4_Y);
                link.setX(link.getX() + LEFT_MOVE_4_X);
            }
            case 6 -> link.setX(link.getX() + LEFT_MOVE_5);
            case 8 -> link.setX(link.getX() + LEFT_MOVE_6);
        }
    }

    private void handleDownAnimation(int animationCounter) {
        switch (animationCounter) {
            case 0 -> link.setX(link.getX() + DOWN_MOVE_1_X);
            case 1 -> link.setX(link.getX() + DOWN_MOVE_2_X);
            case 2 -> link.setX(link.getX() + DOWN_MOVE_3_X);
        }
    }

    private void setUpSwordAnimation(Link link) {
        switch (link.getDirection()) {
            case UP -> {
                link.setAnimation(upAnimation);
                link.setAnimationInterval(20);
                sword = new Rectangle(oldX - SWORD_UP_OFFSET_X,
                        oldY - SWORD_UP_OFFSET_Y, SWORD_UP_WIDTH, SWORD_UP_HEIGHT);
                game.playFx(SWORD_SLASH_SOUND_UP);
            }
            case DOWN -> {
                link.setAnimation(downAnimation);
                link.setAnimationInterval(30);
                sword = new Rectangle(oldX + SWORD_DOWN_OFFSET_X,
                        oldY + link.getHeight() + SWORD_DOWN_OFFSET_Y, SWORD_DOWN_WIDTH, SWORD_DOWN_HEIGHT);
                game.playFx(SWORD_SLASH_SOUND_DOWN);
            }
            case LEFT -> {
                link.setAnimation(leftAnimation);
                link.setAnimationInterval(20);
                sword = new Rectangle(oldX - SWORD_LEFT_OFFSET_X,
                        oldY + SWORD_LEFT_OFFSET_Y, SWORD_LEFT_WIDTH, SWORD_LEFT_HEIGHT);
                game.playFx(SWORD_SLASH_SOUND_LEFT);
            }
            case RIGHT -> {
                link.setAnimation(rightAnimation);
                link.setAnimationInterval(20);
                sword = new Rectangle(oldX + link.getWidth() + SWORD_RIGHT_OFFSET_X,
                        oldY + SWORD_RIGHT_OFFSET_Y, SWORD_RIGHT_WIDTH, SWORD_RIGHT_HEIGHT);
                game.playFx(SWORD_SLASH_SOUND_RIGHT);
            }
        }
    }
}
