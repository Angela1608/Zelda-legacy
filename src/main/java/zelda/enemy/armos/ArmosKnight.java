package zelda.enemy.armos;

import java.awt.Rectangle;
import lombok.Getter;
import lombok.Setter;
import zelda.collision.Hittable;
import zelda.collision.Weapon;
import zelda.enemy.AttackBehavior;
import zelda.enemy.Behavior;
import zelda.engine.Game;
import zelda.karacter.Direction;
import zelda.karacter.Karacter;

import static java.lang.System.currentTimeMillis;

@Getter
@Setter
public class ArmosKnight extends Karacter implements Hittable {

    private static final String IMAGE_PATH = "images/armos.png";
    private static final String HIT_SOUND_PATH = "sounds/enemyHit.mp3";
    private static final String DEATH_SOUND_PATH = "sounds/enemyDie.mp3";
    private static final int MAX_HEALTH = 25;
    private static final int HIT_COOLDOWN_MS = 800;
    private static final long INPUT_INTERVAL_MS = 40;
    private static final int SPRITE_WIDTH = 32;
    private static final int SPRITE_HEIGHT = 52;
    private static final int NUM_SPRITES = 10;
    private static final int SWORD_DAMAGE = 3;
    private static final int BOMB_DAMAGE = 10;
    private static final int ARROW_DAMAGE = 1;
    private Behavior behavior;
    private long lastInput = currentTimeMillis();
    private long lastHit = currentTimeMillis();

    public ArmosKnight(Game game, int x, int y, Direction direction) {
        super(game, x, y, SPRITE_WIDTH, SPRITE_HEIGHT, direction, IMAGE_PATH);
        for (int i = 1; i <= 10; i++) {
            spriteLoc.put(Integer.toString(i), new Rectangle((i - 1) * 32, 0, SPRITE_WIDTH, SPRITE_HEIGHT));
            spriteLoc.put("hit " + i, new Rectangle((i - 1) * 32, 64, SPRITE_WIDTH, SPRITE_HEIGHT));
        }
        sprite.setSprite(spriteLoc.get("1"));
        state = new AttackState(this);
        behavior = new AttackBehavior(this);
    }

    public void hitBy(Weapon weapon) {
        if (health >= MAX_HEALTH) {
            game.playFx(HIT_SOUND_PATH);
        }
        if (health > 0 && currentTimeMillis() > lastHit + HIT_COOLDOWN_MS) {
            lastHit = currentTimeMillis();
            int damage = switch (weapon) {
                case SWORD -> SWORD_DAMAGE;
                case BOMB -> BOMB_DAMAGE;
                case ARROW -> ARROW_DAMAGE;
            };
            health -= damage;
            setState(new TransState(this, game.getLink().getDirection()));
        }
        if (health <= 0) {
            alive = false;
            game.playFx(DEATH_SOUND_PATH);
            randomGoodie();
        }
    }

    @Override
    public void preAnimation() {
        state.handleAnimation();
    }

    @Override
    public void doInLoop() {
        if (currentTimeMillis() > lastInput + INPUT_INTERVAL_MS) {
            state.handleInput();
            behavior.behave();
            lastInput = currentTimeMillis();
        }
    }
}
