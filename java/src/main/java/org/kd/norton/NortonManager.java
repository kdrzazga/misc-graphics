package org.kd.norton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.kd.common.AnimationManager;
import org.kd.common.Scene1Bios;

import java.util.Arrays;

public class NortonManager extends AnimationManager {

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected final String musicPath;

    public NortonManager(String musicPath) {
        this.musicPath = musicPath;
        camera = new OrthographicCamera();
        this.viewport = new FitViewport(1027, 768);
    }

    public void create() {
        super.create();
        this.createMusic(musicPath);

        var scene1 = new Scene1Bios();
        var scene2 = new Scene2Norton();

        Arrays.asList(scene1, scene2).forEach(s -> {
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
        batch.setProjectionMatrix(camera.combined);

        float delta = Gdx.graphics.getDeltaTime();
        long frame = Gdx.graphics.getFrameId();
        sceneManager.update(delta);
        sceneManager.render();

        if (frame == Scene2Norton.START_FRAME)
            sceneManager.switchScene(Scene2Norton.ID);
    }
}
