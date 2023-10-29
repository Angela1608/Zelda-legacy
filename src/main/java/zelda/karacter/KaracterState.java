package zelda.karacter;

public abstract class KaracterState {

    protected Karacter karacter;
    protected String name;

    public KaracterState(Karacter karacter) {
        this.karacter = karacter;
        karacter.resetAnimationCounter();
    }

    public void handleInput() {
    }

    public void handleAnimation() {
    }

    @Override
    public String toString() {
        return name;
    }
}
