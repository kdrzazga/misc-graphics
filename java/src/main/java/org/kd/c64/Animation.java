package org.kd.c64;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;

public class Animation extends ApplicationAdapter {
    SpriteBatch batch;
    C64AnimationManager animationManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        animationManager = new C64AnimationManager();
        var scene1Tiles = new Scene1Tiles("scene1");
        var scene2 = new Scene2("scene2");

        Arrays.asList(scene1Tiles, scene2).forEach(s -> {
            s.create();
            animationManager.addScene(s.id, s);
        });

        animationManager.switchScene("scene1");
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
            animationManager.switchScene("scene2");
        }
    }

    @Override
    public void dispose() {
        batch.dispose();

        animationManager.disposeScenes();
    }
}