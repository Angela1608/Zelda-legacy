package zelda.engine;

import java.awt.Graphics2D;

public interface Drawable {

    /**
     * Draws the object on the provided Graphics2D context.
     *
     * @param g The Graphics2D context on which the object should be drawn.
     */
    void draw(Graphics2D g);
}
