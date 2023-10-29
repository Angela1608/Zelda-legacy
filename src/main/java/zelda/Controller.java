package zelda;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import zelda.engine.GObject;
import zelda.engine.Game;

public class Controller implements Runnable, KeyListener {

	private final Game game;
	private final View view;

	public Controller(Game game, View view, JFrame frame) {
		this.game = game;
		this.view = view;
		frame.addMouseListener(new PolyCreator(game));
		frame.addKeyListener(this);
		Thread thread = new Thread(this, "GameLoop");
		thread.start();
	}

	public void run() {
		while (game.isRunning()) {
			try {
				if(!game.isPaused()) {
					game.getScene().handleInput();
					game.getLink().handleInput();
					for(GObject obj : game.getScene().getGObjects()) {
						obj.doInLoop();
					}
				}
                try {
                    view.draw();
                } catch(Exception ignored){}
				Thread.sleep(game.getGameSpeed());
			} catch (InterruptedException ignored){}
		}
		view.exitFullScreen();
		game.quit();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game.setRunning(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			game.setPaused(!game.isPaused());
		}
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> game.setAPressed(true);
            case KeyEvent.VK_D -> game.setDPressed(true);
            case KeyEvent.VK_W -> game.setWPressed(true);
            case KeyEvent.VK_S -> game.setSPressed(true);
            case KeyEvent.VK_J -> game.setJPressed(true);
            case KeyEvent.VK_K -> game.setKPressed(true);
            case KeyEvent.VK_L -> game.setLPressed(true);
            case KeyEvent.VK_ENTER -> game.setEnterPressed(true);
        }
	}

	public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> game.setAPressed(false);
            case KeyEvent.VK_D -> game.setDPressed(false);
            case KeyEvent.VK_W -> game.setWPressed(false);
            case KeyEvent.VK_S -> game.setSPressed(false);
            case KeyEvent.VK_J -> game.setJPressed(false);
            case KeyEvent.VK_K -> game.setKPressed(false);
            case KeyEvent.VK_L -> game.setLPressed(false);
            case KeyEvent.VK_ENTER -> game.setEnterPressed(false);
        }
	}

	public void keyTyped(KeyEvent e){}
}
