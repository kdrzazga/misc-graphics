package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.kd.common.AnimationManager;
import org.kd.common.C64Colors;
import org.kd.common.Globals;
import org.kd.common.TravellingLogo;

import java.util.Arrays;

public class Win311Manager extends AnimationManager {

    @Override
    public void create() {
        super.create();
        this.createMusic("dream210/przybiezeli.mp3"); //TODO

        var scene1 = new Scene1Bios();
        var scene2 = new Scene2WinLoad();
        var scene3 = new Scene3ProgramMgr();

        Arrays.asList(scene1, scene2, scene3).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("BIOS");
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
    }

    @Override
    public void dispose() {
        batch.dispose();
        tune.dispose();
        sceneManager.disposeScenes();
    }
}
