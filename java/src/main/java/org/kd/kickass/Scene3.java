package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.tricks.TravellingLogo;

public class Scene3 implements Screen {

    final static long START_FRAME = 4100;
    private TravellingLogo karatekas;
    private SpriteBatch batch;

    @Override
    public void show() {
        batch = new SpriteBatch();
        float X = Gdx.graphics.getWidth() / 2f;// - 250f/2f;
        float Y = Gdx.graphics.getHeight() - 60;

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        update();
    }

    private void update() {
        var delta = Gdx.graphics.getDeltaTime();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
