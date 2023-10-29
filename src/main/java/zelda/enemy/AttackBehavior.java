package zelda.enemy;

import zelda.karacter.Direction;
import zelda.karacter.Karacter;
import zelda.link.Link;

public class AttackBehavior extends Behavior {

    private final Karacter soldier;
    private final Link link;

    public AttackBehavior(Karacter soldier) {
        this.soldier = soldier;
        link = soldier.getGame().getLink();
    }

    public void behave() {
        int dx = link.getX() - soldier.getX();
        int dy = link.getY() - soldier.getY();
        Direction newDirection = calculateNewDirection(dx, dy);
        if (newDirection != soldier.getDirection()) {
            soldier.setDirection(newDirection);
        }
        int newX = soldier.getX() + Integer.signum(dx);
        int newY = soldier.getY() + Integer.signum(dy);
        soldier.setX(newX);
        soldier.setY(newY);
    }

    private Direction calculateNewDirection(int dx, int dy) {
        if (Math.abs(dx) >= Math.abs(dy)) {
            return (dx > 0) ? Direction.RIGHT : Direction.LEFT;
        } else {
            return (dy > 0) ? Direction.DOWN : Direction.UP;
        }
    }
}
