package org.kd.common;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AnimationManager extends ApplicationAdapter {
    protected SpriteBatch batch;
    protected SceneManager sceneManager;
    protected Music tune;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
    }

    protected void createMusic(String path) {
        tune = Gdx.audio.newMusic(Gdx.files.internal(path));
        tune.setLooping(false);
        tune.setVolume(1f);
    }
}
