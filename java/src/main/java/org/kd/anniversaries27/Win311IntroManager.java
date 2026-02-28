package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import org.kd.win311.Win311Manager;

public class Win311IntroManager extends Win311Manager {

    public Win311IntroManager() {
        super("win311/EscapeRamos.mp3");
    }

    @Override
    public void create() {
        super.create(new Scene4Paint27());

        var scene5 = new Scene5YearBrowser();
        scene5.create();
        sceneManager.addScene(Scene5YearBrowser.ID, scene5);
        System.out.println("Scene5 for Anniversaries 27 created");
    }

    @Override
    public void render() {
        super.render();

        //Paintbrush loaded
        long frame = Gdx.graphics.getFrameId();

        if (frame == Scene5YearBrowser.START_FRAME) {
            sceneManager.switchScene(Scene5YearBrowser.ID);
            this.tune.setVolume(0.3f);
        }
    }
}
