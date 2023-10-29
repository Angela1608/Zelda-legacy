package zelda.link;

import zelda.karacter.Direction;

public class WalkState extends LinkState {

	private static final String[] WALK_DOWN_ANIMATION = {"Link walk down 1", "Link walk down 2", "Link walk down 3",
			"Link walk down 6", "Link walk down 5", "Link walk down 4"};
	private static final String[] WALK_UP_ANIMATION = {"Link walk up 3", "Link walk up 2", "Link walk up 1",
			"Link walk up 4", "Link walk up 6", "Link walk up 5"};
	private static final String[] WALK_LEFT_ANIMATION = {"Link walk left 3", "Link walk left 2", "Link walk left 1",
			"Link walk left 4", "Link walk left 5", "Link walk left 6"};
	private static final String[] WALK_RIGHT_ANIMATION = {"Link walk right 3", "Link walk right 2", "Link walk right 1",
			"Link walk right 4", "Link walk right 5", "Link walk right 6"};
	private static final int WALK_SPEED = 4;

	public WalkState(Link link) {
		super(link);
		name = "WalkState";
		link.setAnimationInterval(90);
	}

	@Override
	public void handleInput() {
		if (game.isJPressed()) {
			link.setState(new SwordState(link));
		} else {
			if (link.noMoveInput()) {
				link.setState(new StandState(link));
			} else if (game.isLPressed()) {
				link.dropBomb();
			} else if (game.isKPressed()) {
				link.shootArrow();
			} else {
				if (game.isAPressed())
					moveLeft();

				if (game.isDPressed())
					moveRight();

				if (game.isWPressed())
					moveUp();

				if (game.isSPressed())
					moveDown();
			}
		}
	}

	private void moveLeft() {
		setAnimationAndDirection(WALK_LEFT_ANIMATION, Direction.LEFT);
		link.setX(link.getX() - WALK_SPEED);
	}

	private void moveRight() {
		setAnimationAndDirection(WALK_RIGHT_ANIMATION, Direction.RIGHT);
		link.setX(link.getX() + WALK_SPEED);
	}

	private void moveUp() {
		setAnimationAndDirection(WALK_UP_ANIMATION, Direction.UP);
		link.setY(link.getY() - WALK_SPEED);
	}

	private void moveDown() {
		setAnimationAndDirection(WALK_DOWN_ANIMATION, Direction.DOWN);
		link.setY(link.getY() + WALK_SPEED);
	}

	private void setAnimationAndDirection(String[] animation, Direction direction) {
		if (link.getAnimation() != animation)
			link.setAnimation(animation);
		if (link.getDirection() != direction)
			link.setDirection(direction);
	}
}
