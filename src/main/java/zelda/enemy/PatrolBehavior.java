package zelda.enemy;

import java.awt.Polygon;
import java.awt.geom.Area;
import zelda.karacter.Direction;

public class PatrolBehavior extends Behavior {

    private final BlueSoldier soldier;
    private int ticks = 0;
    private final int maxTicks;
    private Polygon eyeView;

    public PatrolBehavior(BlueSoldier soldier, int maxTicks) {
        this.soldier = soldier;
        this.maxTicks = maxTicks;
        moveSoldier();
    }

    public void behave() {
        removeEyeView();

        int[] evXPositions;
        int[] evYPositions;

        switch (soldier.getDirection()) {
            case UP -> {
                evXPositions = new int[]{soldier.getX(), soldier.getX() - 30, soldier.getX() - 20, soldier.getX() + 35,
                        soldier.getX() + 45, soldier.getX() + 15};
                evYPositions = new int[]{soldier.getY(), soldier.getY() - 40, soldier.getY() - 50, soldier.getY() - 50,
                        soldier.getY() - 40, soldier.getY()};
            }
            case DOWN -> {
                evXPositions = new int[]{soldier.getX(), soldier.getX() - 30, soldier.getX() - 20, soldier.getX() + 35,
                        soldier.getX() + 45, soldier.getX() + 15};
                evYPositions = new int[]{soldier.getY() + soldier.getHeight(), soldier.getY() + soldier.getHeight() + 40,
                        soldier.getY() + soldier.getHeight() + 50, soldier.getY() + soldier.getHeight() + 50,
                        soldier.getY() + soldier.getHeight() + 40, soldier.getY() + soldier.getHeight()};
            }
            case LEFT -> {
                evXPositions = new int[]{soldier.getX(), soldier.getX() - 40, soldier.getX() - 50, soldier.getX() - 50,
                        soldier.getX() - 40, soldier.getX()};
                evYPositions = new int[]{soldier.getY() + 20, soldier.getY() + 50, soldier.getY() + 40,
                        soldier.getY() - 15, soldier.getY() - 25, soldier.getY() + 5};
            }
            case RIGHT -> {
                evXPositions = new int[]{soldier.getX() + soldier.getWidth(), soldier.getX() + soldier.getWidth() + 40,
                        soldier.getX() + soldier.getWidth() + 50, soldier.getX() + soldier.getWidth() + 50,
                        soldier.getX() + soldier.getWidth() + 40, soldier.getX() + soldier.getWidth()};
                evYPositions = new int[]{soldier.getY() + 20, soldier.getY() + 50, soldier.getY() + 40,
                        soldier.getY() - 15, soldier.getY() - 25, soldier.getY() + 5};
            }
            default -> {
                return;
            }
        }
        eyeView = new Polygon(evXPositions, evYPositions, evXPositions.length);
        addEyeView();
        checkCollision();

        if ("WalkState".equals(soldier.getStateString())) {
            int step = 1;
            ticks += step;

            if (ticks > maxTicks) {
                moveSoldier();
                ticks = 0;
            }
        }
    }

    private void removeEyeView() {
        soldier.getGame().getScene().removeEyeView(eyeView);
    }

    private void addEyeView() {
        soldier.getGame().getScene().addEyeView(eyeView);
    }

    private void checkCollision() {
        final Area area = new Area();
        area.add(new Area(eyeView));
        area.intersect(new Area(soldier.getGame().getLink().getRectangle()));

        if (!area.isEmpty()) {
            soldier.setBehavior(new AttackBehavior(soldier));
        }
    }

    private void moveSoldier() {
        switch (soldier.getDirection()) {
            case UP -> soldier.setDirection(Direction.DOWN);
            case DOWN -> soldier.setDirection(Direction.UP);
            case LEFT -> soldier.setDirection(Direction.RIGHT);
            case RIGHT -> soldier.setDirection(Direction.LEFT);
        }
    }
}
