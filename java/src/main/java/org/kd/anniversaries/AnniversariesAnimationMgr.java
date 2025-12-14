package org.kd.anniversaries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimationManager;
import org.kd.common.SceneManager;

public class AnniversariesAnimationMgr extends AnimationManager {

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();

        var scene1 = new Scene1c64("scene1");

        scene1.create();
        sceneManager.addScene(scene1.id, scene1);
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

    }

    @Override
    public void dispose() {
        batch.dispose();
        sceneManager.disposeScenes();
    }
}
