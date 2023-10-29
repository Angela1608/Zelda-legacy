package zelda.engine;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import zelda.link.Link;

@Getter
@Setter
public class SaveData implements Serializable {

    private int health;
    private int rupee;
    private String sceneName;

    public SaveData(Link link, Scene scene) {
        health = link.getHealth();
        rupee = link.getRupee();
        sceneName = scene.getName();
    }
}
