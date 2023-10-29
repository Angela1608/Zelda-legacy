package zelda.menu;

import java.awt.Rectangle;
import zelda.engine.Game;
import zelda.engine.Scene;
import zelda.scene.HouseScene;

public class MainMenu extends Scene {

	public static final String IMAGE_MAIN_MENU = "images/main-menu.png";
    public static final String SOUND_MAIN_MENU = "sounds/main-menu.mp3";
    private Fairy fairy = new Fairy(game, 210, 215);
    private static int CURRENT = 0;
    private static final int NEW_GAME = 0;
    private static final int LOAD_GAME = 1;
    private static final int HELP = 2;
    private long inputInterval = 100;
    private long lastInput = System.currentTimeMillis();

    public MainMenu(Game game) {
        super(game, IMAGE_MAIN_MENU, "MainMenu");
        sprite.setSprite(new Rectangle(0, 0, game.getWidth(), game.getHeight()));
        gameObjects.add(fairy);
        setupMusic(game);
    }

    @Override
    public void handleInput() {
        if (System.currentTimeMillis() > lastInput + inputInterval) {
            checkEnter();
            checkInput();
            setFairy();
            lastInput = System.currentTimeMillis();
        }
    }

    private void setupMusic(Game game) {
        try {
            game.stopMusic();
        } catch (Exception ignored) {
        }
        game.playMusic(SOUND_MAIN_MENU, false);
    }

    private void checkEnter() {
        if (game.isEnterPressed()) {
			switch (CURRENT) {
				case NEW_GAME -> game.setScene(new HouseScene(game, "GameStart"));
				case LOAD_GAME -> game.load();
				case HELP -> game.setScene(new HelpMenu(game));
			}
        }
    }

    private void checkInput() {
        if (game.isSPressed()) {
            if (CURRENT == HELP) {
                CURRENT = NEW_GAME;
            } else {
                CURRENT += 1;
            }
        }
        if (game.isWPressed()) {
            if (CURRENT == NEW_GAME) {
                CURRENT = HELP;
            } else {
                CURRENT -= 1;
            }
        }
    }

    private void setFairy() {
		switch (CURRENT) {
			case NEW_GAME -> fairy.setY(220);
			case LOAD_GAME -> fairy.setY(285);
			case HELP -> fairy.setY(348);
		}
    }
}
