package zelda.engine;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Sprite {

    private BufferedImage image;
    private int x;
    private int y;
    private int width;
    private int height;

    public Sprite(String img) {
        URL imageUrl = ClassLoader.getSystemResource(img);
        try {
            image = ImageIO.read(imageUrl);
        } catch (IOException e) {
            log.error("Error occurred while loading image from {}: {}", img, e.getMessage(), e);
        }
    }

    public void setSprite(Rectangle rect) {
        this.x = (int) rect.getX();
        this.y = (int) rect.getY();
        this.width = (int) rect.getWidth();
        this.height = (int) rect.getHeight();
    }

    public synchronized BufferedImage getImage() {
        return image.getSubimage(x, y, width, height);
    }

    public int getImageWidth() {
        return image.getWidth();
    }

    public int getImageHeight() {
        return image.getHeight();
    }
}
