package zelda.engine;

import java.net.URL;
import javazoom.jl.player.Player;
import lombok.extern.slf4j.Slf4j;

import static java.lang.System.currentTimeMillis;

@Slf4j
public abstract class Sound implements Runnable {

    private static long lastPlayed = currentTimeMillis();
    private static String lastSong = "";
    protected Game game;
    protected Player player;
    protected Thread th;
    protected URL mp3;

    public Sound(Game game, URL mp3) {
        this.game = game;
        this.mp3 = mp3;
        th = new Thread(this, mp3.getFile());
    }

    public void play() {
        long playInterval = 1000;
        if (currentTimeMillis() > lastPlayed + playInterval
                || !lastSong.equals(mp3.getFile())) {
            try {
                player = new Player(mp3.openStream());
                th.start();
            } catch (Exception e) {
                log.error("Error occurred while playing: {}", e.getMessage(), e);
            }
            lastSong = mp3.getFile();
            lastPlayed = currentTimeMillis();
        }
    }

    public abstract void run();
}
