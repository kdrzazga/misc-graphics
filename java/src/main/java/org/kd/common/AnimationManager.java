package org.kd.common;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AnimationManager extends ApplicationAdapter {
    protected SpriteBatch batch;
    protected SceneManager sceneManager;
    protected Music tune;
}
