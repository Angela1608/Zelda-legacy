package zelda;

import javax.swing.JFrame;
import zelda.engine.Game;

public class Main extends JFrame {

    public Main() {
        setIgnoreRepaint(true);
        Game game = new Game();
        if (game.isDebug()) {
            setLocationRelativeTo(null);
            setSize(game.getWidth(), game.getHeight());
        }
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        View view = new View(game, this);
        Controller ctl = new Controller(game, view, this);
    }

    public static void main(String[] args) {
        new Main();
    }
}