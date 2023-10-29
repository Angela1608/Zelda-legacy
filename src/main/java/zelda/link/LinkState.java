package zelda.link;

import zelda.engine.Game;
import zelda.karacter.KaracterState;

/**
 * Represents the base class for Link's different states in the game.
 * This class extends the generic character state and provides a foundation
 * for specific Link states.
 */
public class LinkState extends KaracterState {

    protected Link link;
    protected Game game;

    public LinkState(Link link) {
        super(link);
        this.link = link;
        this.game = link.getGame();
    }
}
