package zelda.scene;

import java.awt.Polygon;
import java.awt.Rectangle;
import zelda.enemy.BlueSoldier;
import zelda.engine.Game;
import zelda.items.Bush;
import zelda.items.Guard;
import zelda.karacter.Direction;

public class HouseScene extends ZeldaScene {

    public static final String IMAGE_LINK_HOUSE = "images/link-house.png";
    public static final String SOUND_OVERWORLD = "sounds/overworld.mp3";
    private final Rectangle exitUp = new Rectangle(155, 0, 300, 20);
    private final Rectangle exitLeft = new Rectangle(0, 180, 20, 50);
    private final Rectangle exitLeft2 = new Rectangle(0, 250, 20, 90);

    public HouseScene(Game game, String entrance) {
        super(game, IMAGE_LINK_HOUSE, "HouseScene");
        exits.add(exitUp);
        exits.add(exitLeft);
        exits.add(exitLeft2);
        createHouse();
        createRight();
        createHousecliff();
        createSmallcliff();
        createDown();
        createTrees();
        initializeSolids();
        initializeGameObjects();
        handleMusic(game);
        handleSwitchScene(entrance);
    }

    @Override
    public void handleSwitchScene(Rectangle exit) {
        if (exit == exitUp) {
            game.setScene(new HyruleScene(game, "HouseScene"));
        }
        if (exit == exitLeft) {
            game.setScene(new ForrestScene(game, "HouseSceneLeft1"));
        }
        if (exit == exitLeft2) {
            game.setScene(new ForrestScene(game, "HouseSceneLeft2"));
        }
    }

    @Override
    public void handleSwitchScene(String entrance) {
        if (entrance.equals("HyruleScene")) {
            moveScene(12, 0);
            game.getLink().setXHardCore(320);
            game.getLink().setYHardCore(34);
        }
        if (entrance.equals("GameStart")) {
            moveScene(0, 100);
            game.getLink().setXHardCore(185);
            game.getLink().setYHardCore(177);
        }
        if (entrance.equals("ForrestScene1")) {
            moveScene(0, 100);
            game.getLink().setXHardCore(29);
            game.getLink().setYHardCore(100);
        }
        if (entrance.equals("ForrestScene2")) {
            moveScene(0, 100);
            game.getLink().setXHardCore(29);
            game.getLink().setYHardCore(165);
        }
    }

    private void handleMusic(Game game) {
        if (!game.getSong().

                equals(SOUND_OVERWORLD)) {
            try {
                game.stopMusic();
            } catch (Exception ignored) {
            }
            game.playMusic(SOUND_OVERWORLD, true);
        }
    }

    private void initializeGameObjects() {
        gameObjects.add(new Bush(game, 160, 50));
        gameObjects.add(new Bush(game, 272, 51));
        gameObjects.add(new Bush(game, 305, 71));
        gameObjects.add(new Bush(game, 144, 270));
        gameObjects.add(new Bush(game, 128, 270));
        gameObjects.add(new Bush(game, 112, 270));
        gameObjects.add(new Bush(game, 144, 284));
        gameObjects.add(new Bush(game, 128, 284));
        gameObjects.add(new Bush(game, 112, 284));
        gameObjects.add(new Bush(game, 229, 271));
        gameObjects.add(new Bush(game, 229, 287));
        gameObjects.add(new Bush(game, 245, 271));
        gameObjects.add(new Bush(game, 245, 287));
        gameObjects.add(new Bush(game, 34, 272));
        gameObjects.add(new Bush(game, 50, 288));
        gameObjects.add(new Bush(game, 34, 302));
        gameObjects.add(new Bush(game, 50, 272));
        gameObjects.add(new Bush(game, 34, 288));
        gameObjects.add(new Bush(game, 50, 302));
        gameObjects.add(new Bush(game, 419, 333));
        gameObjects.add(new Bush(game, 419, 349));
        gameObjects.add(new Bush(game, 435, 349));
        gameObjects.add(new Bush(game, 435, 365));
        gameObjects.add(new Bush(game, 451, 365));
        gameObjects.add(game.getLink());
        gameObjects.add(new BlueSoldier(game, 300, 90, Direction.LEFT, 20));
        gameObjects.add(new BlueSoldier(game, 325, 300, Direction.DOWN, 30));
        gameObjects.add(new Guard(game, 483, 408, Direction.RIGHT));
        gameObjects.add(new Guard(game, 483, 376, Direction.RIGHT));
        gameObjects.add(new Guard(game, 9, 415, Direction.LEFT));
        gameObjects.add(new Guard(game, 9, 385, Direction.LEFT));
        gameObjects.add(new Guard(game, 233, 480, Direction.UP));
        gameObjects.add(new Guard(game, 206, 480, Direction.UP));
    }

    private void initializeSolids() {
        solids.add(createTrees());
        solids.add(createHousecliff());
        solids.add(createSmallcliff());
        solids.add(createRight());
        solids.add(createDown());
        solids.add(createHouse());
    }

    private Polygon createHouse() {
        int[] hxpos = {149, 146, 145, 151, 177, 178, 182, 182, 202, 202, 208, 208, 232, 238, 240, 237, 150};
        int[] hypos = {177, 180, 265, 271, 273, 275, 275, 272, 271, 275, 275, 272, 272, 268, 183, 177, 177};
        return new Polygon(hxpos, hypos, hypos.length);
    }

    private Polygon createRight() {
        int[] rxpos = {449, 450, 385, 384, 418, 416, 492, 511, 510};
        int[] rypos = {0, 27, 90, 180, 215, 292, 370, 373, 1};
        return new Polygon(rxpos, rypos, rypos.length);
    }

    private Polygon createHousecliff() {
        int[] hcxpos = {1, 55, 81, 84, 119, 251, 303, 304, 275, 125, 112, 110, 126, 276, 278, 240, 127, 108, 108,
                120, 122, 73, 49, 49, 1, 1};
        int[] hcypos = {232, 232, 206, 157, 122, 122, 173, 352, 383, 382, 359, 328, 340, 340, 175, 139, 139, 158,
                176, 177, 219, 271, 271, 251, 249, 233};
        return new Polygon(hcxpos, hcypos, hcypos.length);
    }

    private Polygon createSmallcliff() {
        int[] scxpos = {0, 47, 56, 56, 63, 64, 68, 69, 66, 49, 0, 0};
        int[] scypos = {383, 382, 378, 369, 362, 353, 348, 330, 328, 342, 342, 382};
        return new Polygon(scxpos, scypos, scypos.length);
    }

    private Polygon createDown() {
        int[] dxpos = {1, 71, 87, 124, 137, 171, 206, 207, 256, 303, 512, 512, 1, 1};
        int[] dypos = {441, 440, 424, 424, 442, 440, 476, 486, 488, 436, 433, 487, 488, 442};
        return new Polygon(dxpos, dypos, dypos.length);
    }

    private Polygon createTrees() {
        int[] txpos = {2, 12, 18, 25, 31, 32, 29, 28, 36, 37, 33, 40, 40, 45, 51, 56, 55, 54, 54, 55, 62, 58, 64, 64,
                74, 85, 96, 102, 104, 107, 112, 125, 129, 128, 125, 125, 129, 134, 134, 130, 136, 137, 143, 146, 152,
                152, 149, 149, 157, 157, 153, 158, 161, 0};
        int[] typos = {190, 190, 180, 183, 183, 178, 176, 171, 168, 166, 160, 150, 132, 132, 136, 136, 131, 128, 124,
                121, 118, 113, 100, 86, 87, 86, 83, 94, 96, 96, 84, 88, 88, 83, 79, 75, 73, 72, 68, 65, 54, 36, 38, 41,
                40, 33, 30, 25, 25, 22, 16, 11, 0, 0};
        return new Polygon(txpos, typos, typos.length);
    }
}
