package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import org.kd.common.Helper;
import org.kd.common.Scene;
import org.kd.win311.Win311Manager;

public class Win311IntroManager extends Win311Manager {

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
            var scene5 = new Scene5YearBrowser();
            runScene(scene5, 0.3f);
        } else if (frame == Scene6Outro.START_FRAME) {
            System.out.println(Helper.countElapsedTime() + " OUTRO scene started [frame " + frame + "]");
            var scene6 = new Scene6Outro();
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
