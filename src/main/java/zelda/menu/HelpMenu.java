package zelda.menu;

import java.awt.Rectangle;
import lombok.extern.slf4j.Slf4j;
import zelda.engine.Game;
import zelda.engine.Scene;

@Slf4j
public class HelpMenu extends Scene {

    public static final String HELP_MENU_IMAGE_PATH = "images/help-menu.png";
    public static final String HELP_MENU_SOUND_PATH = "sounds/help-menu.mp3";
    public static final long HELP_MENU_INPUT_INTERVAL = 100;

    private long lastInput = System.currentTimeMillis();

    public HelpMenu(Game game) {
        super(game, HELP_MENU_IMAGE_PATH, "HelpMenu");
        sprite.setSprite(new Rectangle(0, 0, game.getWidth(), game.getHeight()));
        handleMusic(game);
    }

    @Override
    public void handleInput() {
        if (System.currentTimeMillis() > lastInput + HELP_MENU_INPUT_INTERVAL) {
            if (game.isEnterPressed()) {
                game.setScene(new MainMenu(game));
            }
            lastInput = System.currentTimeMillis();
        }
    }

    private void handleMusic(Game game) {
        try {
            game.stopMusic();
        } catch (Exception e) {
           log.error("Error playing music", e);
        }
        game.playMusic(HELP_MENU_SOUND_PATH, false);
    }
}
