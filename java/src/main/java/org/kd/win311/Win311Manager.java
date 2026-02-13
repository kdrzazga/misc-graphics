package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.kd.common.AnimationManager;

import java.util.Arrays;

public class Win311Manager extends AnimationManager {

    @Override
    public void create() {
        super.create();
        this.createMusic("win311/EscapeRamos.mp3"); //TODO

        var scene1 = new Scene1Bios();
        var scene2 = new Scene2WinLoad();
        var scene3 = new Scene3ProgramMgr();
        var scene4 = new Scene4Paintbrush();

        Arrays.asList(scene1, scene2, scene3, scene4).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("BIOS");
        tune.setLooping(true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        long frame = Gdx.graphics.getFrameId();
        sceneManager.update(delta);
        sceneManager.render();

        if (frame == Scene2WinLoad.START_FRAME)
            sceneManager.switchScene(Scene2WinLoad.ID);
        if (frame == Scene3ProgramMgr.START_FRAME)
            sceneManager.switchScene(Scene3ProgramMgr.ID);
        if (frame == Scene3ProgramMgr.PLAY_MUSIC_FRAME)
            tune.play();
        if (frame == Scene4Paintbrush.START_FRAME)
            sceneManager.switchScene(Scene4Paintbrush.ID);
    }

    @Override
    public void dispose() {
        batch.dispose();
        tune.dispose();
        sceneManager.disposeScenes();
    }
}
