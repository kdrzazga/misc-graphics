package org.kd.anniversaries;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.SceneManager;

import java.util.Arrays;

public class AnniversariesAnimationMgr extends ApplicationAdapter {
    SpriteBatch batch;
    SceneManager sceneManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();

        var scene1 = new Scene1c64("scene1");
        Arrays.asList(scene1).forEach(s -> {
            s.create();
            sceneManager.addScene(s.id, s);
        });

        sceneManager.switchScene("scene1");
    }

    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sceneManager.update(delta);

        batch.begin();
        sceneManager.render();
        batch.end();

        long frame = Gdx.graphics.getFrameId();


/*
        if (frame == 9160) {
            sceneManager.switchScene("scene2");
        }*/
    }



    @Override
    public void dispose() {
        batch.dispose();
        sceneManager.disposeScenes();
    }
}
