package zelda.engine;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import lombok.Getter;

@Getter
public abstract class Scene implements Drawable {

    protected Sprite sprite;
    protected Game game;
    protected ArrayList<GObject> newGameObjects = new ArrayList<>();
    protected ArrayList<GObject> gameObjects = new ArrayList<>();
    protected ArrayList<Polygon> solids = new ArrayList<>();
    protected ArrayList<Rectangle> hitters = new ArrayList<>();
    protected ArrayList<Polygon> eyeViews = new ArrayList<>();
    protected String sceneName;
    protected final static int MOD = 1;

    public Scene(Game game, String img, String sceneName) {
        this.game = game;
        sprite = new Sprite(img);
        this.sceneName = sceneName;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(sprite.getImage(),
                0, 0, game.getWidth(), game.getHeight(), null);
    }

    public synchronized void handleInput() {
        gameObjects.addAll(newGameObjects);
        gameObjects.sort(new GObjectComparator());
        gameObjects.removeIf(obj -> !obj.isAlive());
        newGameObjects.clear();
    }

    public void moveScene(int toX, int toY) {
        boolean moved = false;
        do {
            moved = false;
            if (sprite.getX() < toX) {
                int newX = sprite.getX() + MOD;
                if ((newX + sprite.getWidth()) <= sprite.getImageWidth()) {
                    game.getLink().setX(game.getLink().getX() - MOD);
                    modShapes(-MOD, 0);
                    sprite.setX(newX);
                    moved = true;
                }
            }
            if (sprite.getX() > toX) {
                int newX = sprite.getX() - MOD;
                if (newX > 0) {
                    game.getLink().setX(game.getLink().getX() + MOD);
                    modShapes(MOD, 0);
                    sprite.setX(newX);
                    moved = true;
                }
            }
            if (sprite.getY() < toY) {
                int newY = sprite.getY() + MOD;
                if ((newY + sprite.getHeight()) <= sprite.getImageHeight()) {
                    game.getLink().setY(game.getLink().getY() - MOD);
                    modShapes(0, -MOD);
                    sprite.setY(newY);
                    moved = true;
                }
            }
            if (sprite.getY() > toY) {
                int newY = sprite.getY() - MOD;
                if (newY > 0) {
                    game.getLink().setY(game.getLink().getY() + MOD);
                    modShapes(0, MOD);
                    sprite.setY(newY);
                    moved = true;
                }
            }
        }
        while (moved);
    }

    public void modShapes(int modX, int modY) {
        for (Polygon poly : solids) {
            poly.translate(modX, modY);
        }
        for (GObject obj : gameObjects) {
            if (obj.isScreenAdjust()) {
                obj.setX(obj.getX() + modX);
                obj.setY(obj.getY() + modY);
            }
        }
    }

    public void addNewGObject(GObject gObject) {
        newGameObjects.add(gObject);
    }

    public ArrayList<GObject> getGObjects() {
        return gameObjects;
    }

    public void addHitter(Rectangle rect) {
        hitters.add(rect);
    }

    public void removeHitter(Rectangle rect) {
        hitters.remove(rect);
    }

    public void addEyeView(Polygon poly) {
        eyeViews.add(poly);
    }

    public void removeEyeView(Polygon poly) {
        eyeViews.remove(poly);
    }

    public String getName() {
        return sceneName;
    }
}
