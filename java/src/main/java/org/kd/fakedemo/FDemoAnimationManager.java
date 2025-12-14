package org.kd.fakedemo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimationManager;
import org.kd.common.SceneManager;

import java.util.Arrays;

public class FDemoAnimationManager extends AnimationManager {

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
        var scene1Tiles = new Scene1C64("scene1");
        var scene2 = new Scene2("scene2");

        Arrays.asList(scene1Tiles, scene2).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });

        sceneManager.switchScene("scene1");
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sceneManager.update(delta);

        batch.begin();
        sceneManager.render();
        batch.end();

        long frame = Gdx.graphics.getFrameId();
        if (frame == 283) sceneManager.switchScene("scene2");
    }

    @Override
    public void dispose() {
        batch.dispose();

        sceneManager.disposeScenes();
    }
}