package zelda.engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import zelda.items.Heart;
import zelda.items.Rupee;

@Getter
@Setter
public abstract class GObject implements Drawable {

    protected Game game;
    protected boolean alive = true;
    protected int x;
    protected int y;
    protected int z = 0;
    protected int width;
    protected int height;
    protected boolean checkCollision = true;
    protected boolean liquid = false;
    protected boolean screenAdjust = true;
    protected Sprite sprite;
    protected static HashMap<String, Rectangle> spriteLoc = new HashMap<>();
    protected String[] animation;
    protected int animationCounter = 0;
    protected long animationInterval;
    protected long lastAnimation = System.currentTimeMillis();

    public GObject(Game game, int x, int y, int width, int height, String image) {
        animationInterval = 90;
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sprite = new Sprite(image);
    }

    public void doInLoop() {
    }

    protected void preAnimation() {
    }

    protected void postAnimation() {
    }

    protected void collision(GObject hitObject) {
    }

    protected void wallCollision() {
    }

    public void animate() {
        if (System.currentTimeMillis() > lastAnimation + animationInterval) {
            preAnimation();
            if (animationCounter == animation.length) {
                animationCounter = 0;
            }
            try {
                sprite.setSprite(spriteLoc.get(animation[animationCounter]));
            } catch (Exception e) {
                animationCounter = 0;
            }
            animationCounter += 1;
            lastAnimation = System.currentTimeMillis();
            postAnimation();
        }
    }

    public void draw(Graphics2D g) {
        Image img = sprite.getImage();
        g.drawImage(img, x, y, sprite.getWidth(), sprite.getHeight(), null);
    }

    private boolean isCollision(int newX, int newY) {
        Rectangle rect = new Rectangle(newX, newY, width, height);
        boolean collision = false;
        for (Polygon poly : game.getScene().getSolids()) {
            final Area area = new Area();
            area.add(new Area(rect));
            area.intersect(new Area(poly));
            if (!area.isEmpty()) {
                collision = true;
                wallCollision();
            }
        }
        for (GObject obj : game.getScene().getGObjects()) {
            if (obj.isCheckCollision()) {
                final Area area = new Area();
                area.add(new Area(rect));
                area.intersect(new Area(obj.getRectangle()));
                if (!area.isEmpty() && this != obj) {
                    collision(obj);
                    obj.collision(this);
                    if (!obj.isLiquid()) {
                        collision = true;
                    }
                }
            }
        }
        return collision;
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        if (!checkCollision || !isCollision(newX, y)) {
            x = newX;
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        if (!checkCollision || !isCollision(x, newY)) {
            y = newY;
        }
    }

    public void setYHardCore(int y) {
        this.y = y;
    }

    public void setXHardCore(int x) {
        this.x = x;
    }

    public boolean isAlive() {
        return alive;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public void resetAnimationCounter() {
        animationCounter = 0;
    }

    public boolean isCheckCollision() {
        return checkCollision;
    }

    public boolean isLiquid() {
        return liquid;
    }

    public boolean isScreenAdjust() {
        return screenAdjust;
    }

    public void randomGoodie() {
        int r = (int) (Math.random() * 200);
        if (r < 50) {
            if (r < 25) {
                game.getScene().addNewGObject(new Heart(game, x, y));
            } else {
                game.getScene().addNewGObject(new Rupee(game, x, y));
            }
        }
    }
}
