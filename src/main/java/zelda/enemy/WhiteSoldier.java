package zelda.enemy;

import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.engine.Game;
import zelda.karacter.Direction;

import static java.lang.System.currentTimeMillis;

public class WhiteSoldier extends Soldier implements Hittable {

    private static final String ENEMY_HIT_SOUND_PATH = "sounds/enemyHit.mp3";
    private static final String IMAGE_PATH = "images/white-soldier.png";
    private static final String ENEMY_DIE_SOUND_PATH = "sounds/enemyDie.mp3";
    private static final int HIT_COOLDOWN = 800;
    private static final int SWORD_DAMAGE = 3;
    private static final int ARROW_DAMAGE = 2;

    public WhiteSoldier(Game game, int x, int y, Direction direction) {
        super(game, x, y, direction, IMAGE_PATH);
        behavior = new RandomBehavior(this);
    }

    public void hitBy(Weapon weapon) {
        long currentTime = currentTimeMillis();
        if (health > 0 && currentTime > lastHit + HIT_COOLDOWN) {
            lastHit = currentTime;
            switch (weapon) {
                case SWORD -> handleSwordHit();
                case BOMB -> handleBombHit();
                case ARROW -> handleArrowHit();
            }
        }
        if (health <= 0) {
            handleEnemyDeath();
        }
    }

    private void handleSwordHit() {
        health -= SWORD_DAMAGE;
        setState(new TransState(this, game.getLink().getDirection()));
        setBehavior(new AttackBehavior(this));
        playHitSound();
    }

    private void handleBombHit() {
        health = 0;
    }

    private void handleArrowHit() {
        health -= ARROW_DAMAGE;
        setBehavior(new AttackBehavior(this));
        playHitSound();
    }

    private void handleEnemyDeath() {
        alive = false;
        game.playFx(ENEMY_DIE_SOUND_PATH);
        randomGoodie();
    }

    private void playHitSound() {
        if (health >= 1) {
            game.playFx(ENEMY_HIT_SOUND_PATH);
        }
    }
}
