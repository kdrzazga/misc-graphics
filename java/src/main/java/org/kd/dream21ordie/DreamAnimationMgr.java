package org.kd.dream21ordie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.SceneManager;

import java.util.Arrays;

public class DreamAnimationMgr extends ApplicationAdapter {
    SpriteBatch batch;
    SceneManager sceneManager;
    private Music kolendaRamosa;

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
    }

    private void createMusic() {
        kolendaRamosa = Gdx.audio.newMusic(Gdx.files.internal("winter/WsrodNocnejRamos.mp3"));
        kolendaRamosa.setLooping(false);
        kolendaRamosa.setVolume(1f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        kolendaRamosa.dispose();
        sceneManager.disposeScenes();
    }
}
