package zelda.items;

import java.awt.Rectangle;
import zelda.engine.GObject;
import zelda.engine.Game;

public class GuiRupee extends GObject {

    private static final String IMAGE_PATH_RUPEE_GUI = "images/rupeegui2.png";
    private final static String[] rupeeAnimation = {"rupee"};

    public GuiRupee(Game game, int x, int y) {
        super(game, x, y, 11, 10, IMAGE_PATH_RUPEE_GUI);
        initializeSpriteLoc();
        setAnimation(rupeeAnimation);
        z = 2;
        screenAdjust = false;
        checkCollision = false;
        liquid = true;
    }

    private void initializeSpriteLoc() {
        spriteLoc.put("rupee", new Rectangle(0, 0, 11, 10));
    }
}
