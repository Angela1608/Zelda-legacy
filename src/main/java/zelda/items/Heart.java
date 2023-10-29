package zelda.items;

import java.awt.Rectangle;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.link.Link;

public class Heart extends GObject {

    private static final String IMAGE_PATH_HEART = "images/heart1.png";
    private static final String[] heartAnimation = {"heart"};

    public Heart(Game game, int x, int y) {
        super(game, x, y, 11, 10, IMAGE_PATH_HEART);
        initializeSpriteLoc();
        sprite.setSprite(spriteLoc.get("heart"));
        setAnimation(heartAnimation);
        liquid = true;
    }

    @Override
    public void collision(GObject obj) {
        if (obj instanceof Link) {
            alive = false;
        }
    }

    private void initializeSpriteLoc() {
        spriteLoc.put("heart", new Rectangle(0, 0, 11, 10));
    }
}
