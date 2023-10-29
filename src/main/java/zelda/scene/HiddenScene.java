package zelda.scene;

import java.awt.Polygon;
import java.awt.Rectangle;
import zelda.enemy.WhiteSoldier;
import zelda.engine.Game;
import zelda.items.Rupee;
import zelda.karacter.Direction;

public class HiddenScene extends ZeldaScene {

    private static final String IMAGE_HIDDENPATH = "images/hiddenpath.png";
    public static final String SOUND_CAVE = "sounds/cave.mp3";
    private final Rectangle exitDown = new Rectangle(116, 449, 20, 20);

    public HiddenScene(Game game, String entrance) {
        super(game, IMAGE_HIDDENPATH, "HiddenScene");
        exits.add(exitDown);
        createMuur();
        createFirstMuur();
        createSecondMuur();
        createThirdMuur();
        createUitgang();
        initializeSolids();
        initializeGameObjects();
        handleMusic(game, entrance);
    }

    @Override
    public void handleSwitchScene(Rectangle exit) {
        if (exit == exitDown) {
            game.setScene(new HyruleScene(game, "HiddenScene"));
        }
    }

    @Override
    public void handleSwitchScene(String entrance) {
        if (entrance.equals("HyruleSceneHatch")) {
            moveScene(13, 0);
            game.getLink().setXHardCore(396);
            game.getLink().setYHardCore(141);
        }
        if (entrance.equals("HyruleSceneStairs")) {
            moveScene(1, 79);
            game.getLink().setXHardCore(116);
            game.getLink().setYHardCore(346);
        }
    }

    private void initializeSolids() {
        solids.add(createMuur());
        solids.add(createFirstMuur());
        solids.add(createSecondMuur());
        solids.add(createThirdMuur());
        solids.add(createUitgang());
    }

    private void initializeGameObjects() {
        gameObjects.add(game.getLink());
        gameObjects.add(new Rupee(game, 365, 322));
        gameObjects.add(new Rupee(game, 373, 322));
        gameObjects.add(new Rupee(game, 381, 322));
        gameObjects.add(new Rupee(game, 389, 322));
        gameObjects.add(new Rupee(game, 397, 322));
        gameObjects.add(new Rupee(game, 405, 322));
        gameObjects.add(new Rupee(game, 413, 322));
        gameObjects.add(new Rupee(game, 365, 336));
        gameObjects.add(new Rupee(game, 373, 336));
        gameObjects.add(new Rupee(game, 381, 336));
        gameObjects.add(new Rupee(game, 389, 336));
        gameObjects.add(new Rupee(game, 397, 336));
        gameObjects.add(new Rupee(game, 405, 336));
        gameObjects.add(new Rupee(game, 413, 336));
        gameObjects.add(new WhiteSoldier(game, 123, 117, Direction.UP));
        gameObjects.add(new WhiteSoldier(game, 121, 337, Direction.LEFT));
        gameObjects.add(new WhiteSoldier(game, 325, 331, Direction.LEFT));
    }

    private void handleMusic(Game game, String entrance) {
        if (!game.getSong().
                equals(SOUND_CAVE)) {
            game.stopMusic();
            game.playMusic(SOUND_CAVE, true);
            handleSwitchScene(entrance);
        }
    }

    private Polygon createMuur() {
        int[] hxpos = {385, 446, 446, 112, 112, 111, 95, 96, 112, 113, 113, 72, 72, 115, 113, 52, 43, 480, 480};
        int[] hypos = {190, 189, 112, 110, 187, 319, 319, 406, 406, 428, 424, 424, 454, 454, 467, 467, 53, 54, 204};
        return new Polygon(hxpos, hypos, hypos.length);
    }

    private Polygon createFirstMuur() {
        int[] axpos = {143, 141, 184, 183, 145, 142, 157, 159, 319, 320, 432, 431, 431, 142, 140, 144, 144, 383,
                384, 489, 489, 240};
        int[] aypos = {467, 455, 454, 426, 423, 406, 406, 350, 351, 412, 414, 320, 315, 313, 191, 191, 142, 142,
                188, 299, 461, 460};
        return new Polygon(axpos, aypos, aypos.length);
    }

    private Polygon createSecondMuur() {
        int[] bxpos = {352, 353, 375, 375, 360, 360};
        int[] bypos = {314, 371, 372, 359, 358, 318};
        return new Polygon(bxpos, bypos, bypos.length);
    }

    private Polygon createThirdMuur() {
        int[] cxpos = {423, 424, 415, 415, 409, 409, 429};
        int[] cypos = {320, 365, 364, 358, 358, 372, 371};
        return new Polygon(cxpos, cypos, cypos.length);
    }

    private Polygon createUitgang() {
        int[] dxpos = {143, 114, 143};
        int[] dypos = {467, 467, 466};
        return new Polygon(dxpos, dypos, dypos.length);
    }
}
