package zelda.items;

import java.awt.Rectangle;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.karacter.Direction;

public class Guard extends GObject {

    private static final String IMAGE_PATH_ITEMS = "images/items.png";
    private static final String[] DOWN_ANIMATION = {"DOWN"};
    private static final String[] UP_ANIMATION = {"UP"};
    private static final String[] LEFT_ANIMATION = {"LEFT"};
    private static final String[] RIGHT_ANIMATION = {"RIGHT"};

    public Guard(Game game, int x, int y, Direction direction) {
        super(game, x, y, 25, 25, IMAGE_PATH_ITEMS);
        initializeSpriteLoc();
        setDirectionAnimation(direction);
    }

    private void initializeSpriteLoc() {
        spriteLoc.put("DOWN", new Rectangle(0, 25, 25, 30));
        spriteLoc.put("LEFT", new Rectangle(25, 25, 21, 28));
        spriteLoc.put("UP", new Rectangle(50, 25, 25, 28));
        spriteLoc.put("RIGHT", new Rectangle(75, 25, 21, 28));
    }

    private void setDirectionAnimation(Direction direction) {
        switch (direction) {
            case UP -> {
                sprite.setSprite(spriteLoc.get("UP"));
                setAnimation(UP_ANIMATION);
            }
            case DOWN -> {
                sprite.setSprite(spriteLoc.get("DOWN"));
                setAnimation(DOWN_ANIMATION);
            }
            case LEFT -> {
                width = 21;
                sprite.setSprite(spriteLoc.get("LEFT"));
                setAnimation(LEFT_ANIMATION);
            }
            case RIGHT -> {
                sprite.setSprite(spriteLoc.get("RIGHT"));
                setAnimation(RIGHT_ANIMATION);
            }
        }
    }
}
