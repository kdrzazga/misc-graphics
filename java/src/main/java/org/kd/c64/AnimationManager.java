package org.kd.c64;

import java.util.HashMap;
import java.util.Map;

public class AnimationManager {
    private Map<String, Scene> scenes = new HashMap<>();
    private Scene currentScene;

    public void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public void switchScene(String name) {
        if (currentScene != null) {
            currentScene.dispose();
        }
        currentScene = scenes.values().stream().filter( s -> s.id.equals(name)).findFirst().get();
            currentScene.create();
    }

    public void update(float delta) {
        if (currentScene != null) {
            currentScene.update(delta);
        }
    }

    public void render() {
        if (currentScene != null) {
            currentScene.render();
        }
    }

    public void disposeScenes() {
        scenes.values().forEach(scene -> scene.dispose());
    }
}
