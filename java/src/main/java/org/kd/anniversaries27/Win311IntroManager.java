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

        var sceneMain = new MainScene();
        sceneMain.create();
        sceneManager.addScene(MainScene.ID, sceneMain);
        System.out.println("Main Scene for Anniversaries 27 created");
    }

    @Override
    public void render() {
        super.render();

        //Paintbrush loaded
        long frame = Gdx.graphics.getFrameId();

        if (frame == MainScene.START_FRAME) {
            sceneManager.switchScene(MainScene.ID);
            this.tune.setVolume(0.3f);
        }
    }
}
