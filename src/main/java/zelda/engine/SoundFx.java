package zelda.engine;

import java.net.URL;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoundFx extends Sound {

    public SoundFx(Game game, URL mp3) {
        super(game, mp3);
    }

    public void run() {
        while (!player.isComplete()) {
            try {
                player.play();
                Thread.sleep(1000);
            } catch (Exception ee) {
                log.error("Error occurred while playing: {}", ee.getMessage(), ee);
            }
        }
        player.close();
    }
}
