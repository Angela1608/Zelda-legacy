package zelda.menu;

import java.awt.Rectangle;
import zelda.engine.GObject;
import zelda.engine.Game;

public class Fairy extends GObject {

    private static final String[] FLY_ANIMATION = {"Fly1", "Fly2"};
    private static final int SPRITE_WIDTH = 14;
    private static final int SPRITE_HEIGHT = 16;
    private static final String FAIRY_IMAGE_PATH = "images/fairy.png";

    public Fairy(Game game, int x, int y) {
        super(game, x, y, SPRITE_WIDTH, SPRITE_HEIGHT, FAIRY_IMAGE_PATH);
        initializeSpriteLocations();
        setInitialSprite();
        setAnimationInterval(250);
        setAnimation(FLY_ANIMATION);
    }

    private void initializeSpriteLocations() {
        spriteLoc.put("Fly1", new Rectangle(0, 0, SPRITE_WIDTH, SPRITE_HEIGHT));
        spriteLoc.put("Fly2", new Rectangle(20, 0, SPRITE_WIDTH, SPRITE_HEIGHT));
    }

    private void setInitialSprite() {
        sprite.setSprite(spriteLoc.get("Fly1"));
    }
}
