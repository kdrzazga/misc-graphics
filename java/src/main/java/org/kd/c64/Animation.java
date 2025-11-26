package org.kd.c64;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animation extends ApplicationAdapter {
    SpriteBatch batch;
    AnimationManager animationManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        animationManager = new AnimationManager();
        var scene1Tiles = new Scene1Tiles();
        var sceneB = new SceneB();
        scene1Tiles.create();
        sceneB.create();
        animationManager.addScene("scene1Tiles", scene1Tiles);
        animationManager.addScene("sceneB", sceneB);
        // add other scenes
        animationManager.switchScene("scene1Tiles");
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        animationManager.update(delta);

        batch.begin();
        animationManager.render();
        batch.end();

        // Optional: switch scenes based on input or timers
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            animationManager.switchScene("sceneB");
        }
    }

    @Override
    public void dispose() {
        batch.dispose();

        animationManager.disposeScenes();
    }
}