package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.kd.common.AnimationManager;
import org.kd.common.Scene1Bios;

import java.util.Arrays;

public class Win311Manager extends AnimationManager {

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected final String musicPath;

    public Win311Manager(String musicPath) {
        this.musicPath = musicPath;
        camera = new OrthographicCamera();
        this.viewport = new FitViewport(1027, 768);
    }

    public void create(Scene4Paintbrush scene4) {
        super.create();
        this.createMusic(musicPath);

        var scene1 = new Scene1Bios();
        var scene2 = new Scene2WinLoad();
        var scene3 = new Scene3ProgramMgr();

        Arrays.asList(scene1, scene2, scene3, scene4).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });
        sceneManager.switchScene("BIOS");
        tune.setLooping(true);
    }

    @Override
    public void create() {
        var scene4 = new Scene4Paintbrush();
        this.create(scene4);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height, true);
    }
}
