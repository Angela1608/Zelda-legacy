package zelda.karacter;

import lombok.Getter;
import lombok.Setter;
import zelda.engine.GObject;
import zelda.engine.Game;

@Getter
@Setter
public abstract class Karacter extends GObject {

    protected Direction direction;
    protected KaracterState state;
    protected int health = 1;

    public Karacter(Game game, int x, int y, int width,
                    int height, Direction direction, String image) {
        super(game, x, y, width, height, image);
        this.direction = direction;
    }

    public String getStateString() {
        return state.toString();
    }
}
