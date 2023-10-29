package zelda.scene;

import java.awt.Polygon;
import java.awt.Rectangle;
import zelda.enemy.BlueSoldier;
import zelda.enemy.GhostSoldier;
import zelda.engine.Game;
import zelda.items.Warp;
import zelda.karacter.Direction;

import static zelda.scene.ArmosScene.getLeftTreePolygon;
import static zelda.scene.ArmosScene.getRightTreePolygon;

public class BattleScene extends ZeldaScene {

    private static final String IMAGE_BATTLE_DARK = "images/battle-dark.png";
    private static final String SOUND_BOSS_BGM = "sounds/boss-bgm.mp3";
    private final Rectangle warpExit = new Rectangle(232, 458, 16, 16);

    public BattleScene(Game game, String entrance) {
        super(game, IMAGE_BATTLE_DARK, "BattleScene");
        initializeExits();
        initializeSolids();
        initializeGameObjects();
        initializeMusic(game, entrance);
    }

    @Override
    public void handleSwitchScene(Rectangle exit) {
        if (exit == warpExit) {
            game.setScene(new ForrestScene(game, "BattleScene"));
        }
    }

    @Override
    public void handleSwitchScene(String entrance) {
        if (entrance.equals("warp")) {
            moveScene(1, 19);

            game.getLink().setXHardCore(233);
            game.getLink().setYHardCore(200);
        }
    }

    private void initializeExits() {
        exits.add(warpExit);
    }

    private Polygon createLeftTreeline() {
        return getLeftTreePolygon();
    }

    private Polygon createRightTreeLine() {
        return getRightTreePolygon();
    }

    private Polygon createDeadTree() {
        int[] cxpos = {223, 228, 241, 255, 257, 255, 240, 226, 222, 226};
        int[] cypos = {494, 484, 479, 482, 492, 505, 509, 507, 496, 484};
        return new Polygon(cxpos, cypos, cypos.length);
    }

    private void initializeSolids() {
        solids.add(createLeftTreeline());
        solids.add(createRightTreeLine());
        solids.add(createDeadTree());
    }

    private void initializeGameObjects() {
        gameObjects.add(new Warp(game, 232, 458));
        gameObjects.add(game.getLink());
        gameObjects.add(new BlueSoldier(game, 330, 145, Direction.LEFT, 10));
        gameObjects.add(new BlueSoldier(game, 150, 340, Direction.DOWN, 10));
        gameObjects.add(new BlueSoldier(game, 135, 135, Direction.RIGHT, 10));
        gameObjects.add(new BlueSoldier(game, 280, 400, Direction.LEFT, 20));
        gameObjects.add(new GhostSoldier(game, 240, 98, Direction.DOWN));
    }

    private void initializeMusic(Game game, String entrance) {
        if (!game.getSong().equals(SOUND_BOSS_BGM)) {
            game.stopMusic();
            game.playMusic(SOUND_BOSS_BGM, true);
        }
        handleSwitchScene(entrance);
    }
}
