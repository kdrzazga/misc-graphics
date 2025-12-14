package org.kd.winter25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimationManager;
import org.kd.common.SceneManager;

import java.util.Arrays;

public class WinterAnimationMgr extends AnimationManager {

    @Override
    public void create() {
        this.createMusic();
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
        var scene1 = new Scene1c64("scene1");
        var sceneOutro = new Scene2Outro();

        Arrays.asList(scene1, sceneOutro).forEach(s -> {
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

        if (frame > 100 && frame < 333 + 7 * 50) {
            tune.play();
        }

        if (frame == 9160) {
            sceneManager.switchScene("scene2");
        }
    }

    private void createMusic() {
        tune = Gdx.audio.newMusic(Gdx.files.internal("winter/WsrodNocnejRamos.mp3"));
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
