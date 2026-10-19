package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import org.kd.common.Helper;
import org.kd.common.Scene;
import org.kd.win311.Win311Manager;

public class Win311IntroManager extends Win311Manager {

    private final long outroDurationFrames = 1320; // outro used to end at frame 38000, 1320 frames after 1952

    private Scene5YearBrowser yearBrowser;

    public Win311IntroManager() {
        super("win311/EscapeRamos.mp3");
    }

    @Override
    public void create() {
        super.create(new Scene4Paint27());
    }

    @Override
    public void render() {
        super.render();

        //Paintbrush loaded
        long frame = Gdx.graphics.getFrameId();

        if (frame == Scene5YearBrowser.START_FRAME) {
            yearBrowser = new Scene5YearBrowser();
            runScene(yearBrowser, 0.3f);
        } else if (yearBrowser != null && frame == yearBrowser.getEndFrame()) {
            System.out.println(Helper.countElapsedTime() + " OUTRO scene started [frame " + frame + "]");
            var scene6 = new Scene6Outro(outroDurationFrames);
            runScene(scene6, 1f);
        }
    }

    private void runScene(Scene scene, float volume) {
        scene.create();
        sceneManager.addScene(scene.id, scene);
        sceneManager.switchScene(scene.id);
        this.tune.setVolume(volume);
    }
}
