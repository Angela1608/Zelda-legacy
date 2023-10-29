package zelda.engine;

import java.net.URL;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Music extends Sound {

    private String songName = "";
    private final boolean loop;

    public Music(Game game, URL mp3, String songname, boolean loop) {
        super(game, mp3);
        this.loop = loop;
        this.songName = songname;
    }

    public void run() {
        while (!player.isComplete()) {
            try {
                player.play();
                Thread.sleep(1000);
            } catch (Exception e) {
                log.error("Error occurred while playing: {}", e.getMessage(), e);
            }
        }
        if (loop) {
            game.playMusic(songName, true);
        }
        player.close();
    }

    public void stop() {
        player.close();
    }

    public String getSong() {
        return songName;
    }
}
