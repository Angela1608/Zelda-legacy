package zelda.engine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import zelda.link.Link;
import zelda.menu.MainMenu;
import zelda.scene.ArmosScene;
import zelda.scene.BattleScene;
import zelda.scene.CastleBasementScene;
import zelda.scene.CastleScene;
import zelda.scene.DungeonScene;
import zelda.scene.ForrestScene;
import zelda.scene.HiddenScene;
import zelda.scene.HouseScene;
import zelda.scene.HyruleScene;

@Data
@Slf4j
public class Game {

    private volatile boolean running = true;
    private volatile boolean paused = false;
    private volatile boolean debug = false;
    private int gameSpeed = 10;
    private int width = 500;
    private int height = 400;
    private Link link;
    private volatile Scene scene;
    private Music music;
    private SoundFx fx;
    private volatile boolean aPressed = false;
    private volatile boolean sPressed = false;
    private volatile boolean dPressed = false;
    private volatile boolean wPressed = false;
    private volatile boolean jPressed = false;
    private volatile boolean kPressed = false;
    private volatile boolean lPressed = false;
    private volatile boolean enterPressed = false;
    private long lastHit = System.currentTimeMillis();
    private long lastHit2 = System.currentTimeMillis();
    private static final String SERIALIZED_CONTENT = "Zelda.ser";
    private static final String SAVE_SUCCESS_MESSAGE = "Game data saved successfully.";
    private static final String FILE_NOT_FOUND_MESSAGE = "Serialized content not found.";
    private static final String SAVE_ERROR_MESSAGE = "Error occurred while saving game data.";
    private static final String LOAD_SUCCESS_MESSAGE = "Game data loaded successfully.";
    private static final String LOAD_FILE_NOT_FOUND_MESSAGE = "Zelda.ser file not found.";
    private static final String LOAD_ERROR_MESSAGE = "Error occurred while loading game data.";
    private static final String CLOSE_ERROR_MESSAGE = "Error occurred while closing stream.";

    public Game() {
        link = new Link(this, 100, 100);
        scene = new MainMenu(this);
    }

    public void quit() {
        if (music != null)
            music.stop();
        save();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        System.exit(0);
    }

    public void playMusic(String mp3file, boolean loop) {
        URL mp3 = ClassLoader.getSystemResource(mp3file);
        music = new Music(this, mp3, mp3file, loop);
        music.play();
    }

    public void stopMusic() {
        music.stop();
    }

    public String getSong() {
        if (music == null) {
            return "";
        }
        return music.getSong();
    }

    public void playFx(String mp3file) {
        URL mp3 = ClassLoader.getSystemResource(mp3file);
        fx = new SoundFx(this, mp3);
        fx.play();
    }

    public void load() {
        ObjectInputStream in = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(SERIALIZED_CONTENT);
            if (inputStream != null) {
                in = new ObjectInputStream(inputStream);
                SaveData data = (SaveData) in.readObject();
                Scene scn = initScene(data.getSceneName());
                setScene(scn);
                link.setHealth(data.getHealth());
                link.setRupee(data.getRupee());
                log.info(SAVE_SUCCESS_MESSAGE);
            } else {
                log.info(FILE_NOT_FOUND_MESSAGE);
            }
        } catch (IOException | ClassNotFoundException ex) {
            log.error(SAVE_ERROR_MESSAGE, ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error(CLOSE_ERROR_MESSAGE, ex);
            }
        }
    }

    public Scene initScene(String sceneName) {
        Map<String, String> sceneMap = new HashMap<>();
        sceneMap.put("HouseScene", "GameStart");
        sceneMap.put("HyruleScene", "HouseScene");
        sceneMap.put("HiddenScene", "HyruleSceneHatch");
        sceneMap.put("ForrestScene", "HouseScene");
        sceneMap.put("DungeonScene", "GameStart");
        sceneMap.put("CastleScene", "HyruleScene");
        sceneMap.put("CastleBasementScene", "CastleScene");
        sceneMap.put("ArmosScene", "CastleBasementScene");
        sceneMap.put("BattleScene", "warp");
        String parentScene = sceneMap.get(sceneName);
        if (parentScene != null) {
            switch (sceneName) {
                case "HouseScene" -> {
                    return new HouseScene(this, parentScene);
                }
                case "HyruleScene" -> {
                    return new HyruleScene(this, parentScene);
                }
                case "HiddenScene" -> {
                    return new HiddenScene(this, parentScene);
                }
                case "ForrestScene" -> {
                    return new ForrestScene(this, parentScene);
                }
                case "DungeonScene" -> {
                    return new DungeonScene(this, parentScene);
                }
                case "CastleScene" -> {
                    return new CastleScene(this, parentScene);
                }
                case "CastleBasementScene" -> {
                    return new CastleBasementScene(this, parentScene);
                }
                case "ArmosScene" -> {
                    return new ArmosScene(this, parentScene);
                }
                case "BattleScene" -> {
                    return new BattleScene(this, parentScene);
                }
            }
        }
        return null;
    }

    public void save() {
        ObjectOutputStream out = null;
        try {
            URL resourceUrl = getClass().getClassLoader().getResource("Zelda.ser");

            if (resourceUrl != null) {
                out = new ObjectOutputStream(new FileOutputStream(resourceUrl.getFile()));
                SaveData data = new SaveData(link, scene);
                out.writeObject(data);
                log.info(SAVE_SUCCESS_MESSAGE);
            } else {
                log.info(FILE_NOT_FOUND_MESSAGE);
            }
        } catch (IOException ex) {
            log.error(SAVE_ERROR_MESSAGE, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ex) {
                log.error(CLOSE_ERROR_MESSAGE, ex);
            }
        }
    }

    public synchronized Scene getScene() {
        return scene;
    }

    public synchronized void setScene(Scene scene) {
        this.scene = scene;
    }
}
