package org.kd.wishesmount;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimationManager;
import org.kd.common.SceneManager;

import java.util.Arrays;

public class DreamAnimationMgr extends AnimationManager {

    @Override
    public void create() {
        this.createMusic();
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
        var scene1 = new Scene1("scene1");

        Arrays.asList(scene1).forEach(s -> {
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

        if (frame == WishesHelper.KOLENDA_STARTING_FRAME)
            tune.play();
    }

    private void createMusic() {
        tune = Gdx.audio.newMusic(Gdx.files.internal("dream210/Foreign_Carols.mp3"));
        tune.setLooping(false);
        tune.setVolume(1f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        tune.dispose();
        sceneManager.disposeScenes();
    }
}
