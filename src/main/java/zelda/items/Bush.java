package zelda.items;

import java.awt.Rectangle;
import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.engine.GObject;
import zelda.engine.Game;

public class Bush extends GObject implements Hittable {

    public static final String IMAGE_PATH = "images/items.png";
    public static final String SOUND_PATH = "sounds/bushCut.mp3";
    private static final String[] BUSH_ANIMATION = {"bush"};
    private static final String[] STUMP_ANIMATION = {"stump"};
    private boolean liquid = false;

    public Bush(Game game, int x, int y) {
        super(game, x, y, 16, 14, IMAGE_PATH);
        initializeSpriteLocations();
        setInitialAnimation();
    }

    private void initializeSpriteLocations() {
        spriteLoc.put("bush", new Rectangle(0, 0, 16, 15));
        spriteLoc.put("stump", new Rectangle(17, 0, 16, 15));
    }

    private void setInitialAnimation() {
        setAnimation(BUSH_ANIMATION);
        sprite.setSprite(spriteLoc.get("bush"));
    }

    public void hitBy(Weapon weapon) {
        if (weapon == Weapon.SWORD || weapon == Weapon.BOMB) {
            setAnimation(STUMP_ANIMATION);

            if (!liquid) {
                game.playFx(SOUND_PATH);
                randomGoodie();
            }
            liquid = true;
        }
    }
}
