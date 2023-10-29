package zelda.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.engine.Scene;
import zelda.items.GuiHeart;
import zelda.items.GuiRupee;

public abstract class ZeldaScene extends Scene {

    protected ArrayList<Rectangle> exits = new ArrayList<>();
    private boolean adjust = false;
    private final int XSen;
    private final int YSen;

    public ZeldaScene(Game game, String img, String sceneName) {
        super(game, img, sceneName);
        XSen = game.getWidth() / 2;
        YSen = game.getHeight() / 2;
        sprite.setSprite(new Rectangle(0, 0, game.getWidth(), game.getHeight()));
        GuiHeart.clear();
        GuiHeart heart;
        for (int i = 0; i < 5; i++) {
            heart = new GuiHeart(game, (game.getWidth() - 130) + i * 12, 50);
            gameObjects.add(heart);
        }
        GuiRupee rupee = new GuiRupee(game, 100, game.getHeight() / 11);
        gameObjects.add(rupee);
    }

    @Override
    public void handleInput() {
        super.handleInput();
        checkLinkIsInExit();
        if (game.getLink().moveInput()) {
            adjust = true;
        }
        if (adjust) {
            if (!game.getLink().getStateString().equals("SwordState")
                    && !game.getLink().getStateString().equals("BowState")) {
                adjustScene(game.getLink().getX(), game.getLink().getY());
            }
        }
        inputHook();
    }

    public void inputHook() {
    }

    private void checkLinkIsInExit() {
        for (Rectangle exit : exits) {
            if (exit.intersects(game.getLink().getRectangle())) {
                handleSwitchScene(exit);
            }
        }
    }

    public void adjustScene(int moveToX, int moveToY) {
        if (moveToX > (sprite.getWidth() - XSen)) {
            int newX = sprite.getX() + MOD;
            if ((newX + sprite.getWidth()) <= sprite.getImageWidth()) {
                game.getLink().setX(game.getLink().getX() - MOD);
                modShapes(-MOD, 0);
                sprite.setX(newX);
            }
        }
        if (moveToX < XSen) {
            int newX = sprite.getX() - MOD;
            if (newX > 0) {
                game.getLink().setX(game.getLink().getX() + MOD);
                modShapes(MOD, 0);
                sprite.setX(newX);
            }
        }

        if (moveToY > (sprite.getHeight() - YSen)) {
            int newY = sprite.getY() + MOD;
            if ((newY + sprite.getHeight()) <= sprite.getImageHeight()) {
                game.getLink().setY(game.getLink().getY() - MOD);
                modShapes(0, -MOD);
                sprite.setY(newY);
            }
        }
        if (moveToY < YSen) {
            int newY = sprite.getY() - MOD;
            if (newY > 0) {
                game.getLink().setY(game.getLink().getY() + MOD);
                modShapes(0, MOD);
                sprite.setY(newY);
            }
        }
    }

    @Override
    public void modShapes(int modX, int modY) {
        for (Polygon poly : solids) {
            poly.translate(modX, modY);
        }
        for (Rectangle rect : exits) {
            rect.translate(modX, modY);
        }
        for (GObject obj : gameObjects) {
            if (obj.isScreenAdjust()) {
                obj.setXHardCore(obj.getX() + modX);
                obj.setYHardCore(obj.getY() + modY);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(), 0, 0, game.getWidth(), game.getHeight(), null);
        g2.setColor(Color.white);
        Font f = new Font("Serif", Font.BOLD, 12);
        g2.setFont(f);
        g2.drawString("-- LIFE --", game.getWidth() - 122, game.getHeight() / 9);
        g2.drawString(String.valueOf(game.getLink().getRupee()), 102, game.getHeight() / 7);
    }

    public ArrayList<Rectangle> getExits() {
        return exits;
    }

    public abstract void handleSwitchScene(Rectangle exit);

    public abstract void handleSwitchScene(String entrance);
}
