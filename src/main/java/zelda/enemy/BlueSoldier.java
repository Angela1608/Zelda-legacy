package zelda.enemy;

import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.engine.Game;
import zelda.karacter.Direction;

import static java.lang.System.currentTimeMillis;

public class BlueSoldier extends Soldier implements Hittable {

    private static final String IMAGE_PATH = "images/blue-soldier.png";
    private static final String HIT_SOUND_PATH = "sounds/enemyHit.mp3";
    private static final String DEATH_SOUND_PATH = "sounds/enemyDie.mp3";
    private static final int HIT_COOLDOWN_MS = 800;
    private static final int DAMAGE_POINTS = 3;

    public BlueSoldier(Game game, int x, int y, Direction direction, int ticks) {
        super(game, x, y, direction, IMAGE_PATH);
        behavior = new PatrolBehavior(this, ticks);
    }

    public void hitBy(Weapon weapon) {
        if (health >= 1) {
            game.playFx(HIT_SOUND_PATH);
        }

        switch (weapon) {
            case SWORD -> handleSwordHit();
            case BOMB -> health = 0;
            case ARROW -> handleArrowHit();
        }
        if (health <= 0) {
            handleEnemyDeath();
        }
    }

    private void handleSwordHit() {
        if (health > 0 && currentTimeMillis() > lastHit + HIT_COOLDOWN_MS) {
            lastHit = currentTimeMillis();
            health -= DAMAGE_POINTS;
            setState(new TransState(this, game.getLink().getDirection()));
            setBehavior(new AttackBehavior(this));
        }
    }

    private void handleArrowHit() {
        if (health > 0 && currentTimeMillis() > lastHit + HIT_COOLDOWN_MS) {
            lastHit = currentTimeMillis();
            health -= DAMAGE_POINTS;
            setBehavior(new AttackBehavior(this));
        }
    }

    private void handleEnemyDeath() {
        alive = false;
        game.playFx(DEATH_SOUND_PATH);
        randomGoodie();
    }
}
