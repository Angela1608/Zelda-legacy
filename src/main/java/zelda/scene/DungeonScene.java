package zelda.scene;

import java.awt.*;
import zelda.engine.Game;

public class DungeonScene extends ZeldaScene {

    public static final String IMAGE_KERKER = "images/kerker.png";
    public static final String SOUND_CASTLE = "sounds/castle.mp3";
    private final Rectangle zeldaExit = new Rectangle(351, 158, 20, 10);

    public DungeonScene(Game game, String entrance) {
        super(game, IMAGE_KERKER, "DungeonScene");
        initializeExits();
        initializeSolids();
        initializeGameObjects();
        initializeMusic(game, entrance);
    }

    @Override
    public void handleSwitchScene(Rectangle exit) {
        if (exit == zeldaExit) {
            game.setScene(new CreditScene(game));
        }
    }

    @Override
    public void handleSwitchScene(String entrance) {
        if (entrance.equals("ArmosScene")) {
            game.getLink().setXHardCore(81);
            game.getLink().setYHardCore(120);
        }
    }

    private void initializeExits() {
        exits.add(zeldaExit);
    }

    private Polygon createFirstWall() {
        int[] expos = {71, 106, 105, 111, 112, 179, 179, 202, 203, 176, 178, 256, 258, 235, 234, 288, 273, 106};
        int[] eypos = {96, 97, 122, 122, 204, 203, 202, 201, 164, 162, 125, 123, 163, 164, 201, 199, 89, 88};
        return new Polygon(expos, eypos, eypos.length);
    }

    private Polygon createSecondWall() {
        int[] dxpos = {67, 66, 437, 438, 401, 401, 379, 376, 401, 401, 323, 323, 340, 341, 291, 288, 448, 434,
                474, 467, 38, 35, 71};
        int[] dypos = {123, 280, 281, 205, 204, 202, 202, 167, 162, 126, 126, 160, 162, 200, 201, 87, 85, 168,
                170, 329, 319, 94, 96};
        return new Polygon(dxpos, dypos, dypos.length);
    }

    private Polygon createTable() {
        int[] fxpos = {129, 129, 159, 160, 130};
        int[] fypos = {228, 252, 252, 229, 227};
        return new Polygon(fxpos, fypos, fypos.length);
    }

    private void initializeSolids() {
        solids.add(createSecondWall());
        solids.add(createFirstWall());
        solids.add(createTable());
    }

    private void initializeGameObjects() {
        gameObjects.add(game.getLink());
    }

    private void initializeMusic(Game game, String entrance) {
        if (!game.getSong().equals(SOUND_CASTLE)) {
            game.stopMusic();
            game.playMusic(SOUND_CASTLE, true);
        }
        handleSwitchScene(entrance);
    }
}
