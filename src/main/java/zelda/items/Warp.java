package zelda.items;

import java.awt.Rectangle;
import zelda.engine.GObject;
import zelda.engine.Game;

public class Warp extends GObject {

    private static final String IMAGE_PATH_ITEMS = "images/items.png";
    private static final String[] WARP_ANIMATION = {"Warp1", "Warp2", "Warp3"};
    private static final String[] EMPTY_ANIMATION = {"Warp4"};

    public Warp(Game game, int x, int y) {
        super(game, x, y, 1, 1, IMAGE_PATH_ITEMS);
        initializeSpriteLoc();
        sprite.setSprite(spriteLoc.get("Warp1"));
        setAnimation(WARP_ANIMATION);
        liquid = true;
    }

    public void setActive() {
        if (animation == WARP_ANIMATION) {
            setAnimation(EMPTY_ANIMATION);
        } else {
            setAnimation(WARP_ANIMATION);
        }
    }

    private void initializeSpriteLoc() {
        spriteLoc.put("Warp1", new Rectangle(40, 0, 16, 16));
        spriteLoc.put("Warp2", new Rectangle(60, 0, 16, 16));
        spriteLoc.put("Warp3", new Rectangle(80, 0, 16, 16));
        spriteLoc.put("Warp4", new Rectangle(0, 58, 16, 16));
    }
}
