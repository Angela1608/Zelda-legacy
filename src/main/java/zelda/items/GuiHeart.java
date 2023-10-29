package zelda.items;

import java.awt.Rectangle;
import lombok.Setter;
import zelda.engine.GObject;
import zelda.engine.Game;

@Setter
public class GuiHeart extends GObject {

    private static final String IMAGE_PATH_GUI_HEARTS = "images/guihearts2.png";
    private static final String[] FULL_ANIMATION = {"full"};
    private static final String[] EMPTY_ANIMATION = {"empty"};
    private static final GuiHeart[] HEARTS = new GuiHeart[5];
    private static int i = 0;
    private boolean full = true;

    public GuiHeart(Game game, int x, int y) {
        super(game, x, y, 11, 10, IMAGE_PATH_GUI_HEARTS);
        initializeSpriteLoc();
        sprite.setSprite(spriteLoc.get("full"));
        setAnimation(FULL_ANIMATION);
        z = 2;
        screenAdjust = false;
        checkCollision = false;
        liquid = true;
        if (i < 5) {
            HEARTS[i] = this;
            i++;
        }
    }

    @Override
    public void preAnimation() {
        int empty = 5 - game.getLink().getHealth();
        for (int j = 0; j < 5; j++) {
            HEARTS[j].setFull(j < (5 - empty));
        }
        if (full) {
            setAnimation(FULL_ANIMATION);
        } else {
            setAnimation(EMPTY_ANIMATION);
        }
    }

    public static void clear() {
        i = 0;
    }

    private void initializeSpriteLoc() {
        spriteLoc.put("full", new Rectangle(0, 0, 11, 10));
        spriteLoc.put("empty", new Rectangle(11, 0, 11, 10));
    }
}
