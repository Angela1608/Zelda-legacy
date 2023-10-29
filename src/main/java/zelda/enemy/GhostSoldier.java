package zelda.enemy;

import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.engine.Game;
import zelda.karacter.Direction;

import static java.lang.System.currentTimeMillis;

public class GhostSoldier extends Soldier implements Hittable {

    private static final String IMAGE_PATH = "images/boss.png";
    private static final String SOUND_PATH_ENEMY_HIT = "sounds/enemyHit.mp3";
    private static final String SOUND_PATH_ENEMY_DIE = "sounds/enemyDie.mp3";
    private static final int WEAPON_HIT_DELAY_MS = 800;
    private static final int SWORD_ARROW_DAMAGE = 3;
    private static final int BOMB_DAMAGE = 10;
    private static final int INITIAL_HEALTH = 20;

    public GhostSoldier(Game game, int x, int y, Direction direction) {
        super(game, x, y, direction, IMAGE_PATH);
        behavior = new AttackBehavior(this);
        health = INITIAL_HEALTH;
    }

    public void hitBy(Weapon weapon) {
        if (health >= 1) {
            game.playFx(SOUND_PATH_ENEMY_HIT);
        }
        handleWeaponHit(weapon);
        handleEnemyDeath();
    }

    private void handleEnemyDeath() {
        if (health <= 0) {
            alive = false;
            game.playFx(SOUND_PATH_ENEMY_DIE);
            randomGoodie();
        }
    }

    private void handleWeaponHit(Weapon weapon) {
        int damage = switch (weapon) {
            case SWORD, ARROW -> SWORD_ARROW_DAMAGE;
            case BOMB -> BOMB_DAMAGE;
        };
        if (health > 0 && currentTimeMillis() > lastHit + WEAPON_HIT_DELAY_MS) {
            lastHit = currentTimeMillis();
            health -= damage;
            if (weapon == Weapon.ARROW || weapon == Weapon.BOMB) {
                setState(new TransState(this, game.getLink().getDirection()));
            }
        }
    }
}
