package zelda.scene;

import java.awt.Rectangle;
import zelda.engine.Game;

public class CreditScene extends ZeldaScene {

    private static final String IMAGE_AFTITEL = "images/aftitel.png";
    private static final String SOUND_CREDIT = "sounds/credits.mp3";

    public CreditScene(Game game) {
        super(game, IMAGE_AFTITEL, "CreditScene");
        initializeMusic(game);
    }

    @Override
    public void handleSwitchScene(Rectangle exit) {
    }

    @Override
    public void handleSwitchScene(String entrance) {
    }

    private void initializeMusic(Game game) {
        if (!game.getSong().equals(SOUND_CREDIT)) {
            game.stopMusic();
            game.playMusic(SOUND_CREDIT, true);
        }
    }
}
