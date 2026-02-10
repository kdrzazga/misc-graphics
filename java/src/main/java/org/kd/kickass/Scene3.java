package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.Helper;

public class Scene3 implements Screen {

    final static long START_FRAME = 4330;
    private AnimatedSpriteV circle;
    private SpriteBatch batch;

    @Override
    public void show() {
        System.out.println(Helper.countElapsedTime());
        batch = new SpriteBatch();
        float X = Gdx.graphics.getWidth() / 2f - 250f;
        float Y = Gdx.graphics.getHeight() / 2f - 250f;

        circle = new AnimatedSpriteV("kickass/blueFireCircle.png", 13, 0.03f, Math.round(X)
                , Math.round(Y));
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        update();
        batch.begin();
        circle.draw(batch);
        batch.end();
    }

    private void update() {
        var fr = Gdx.graphics.getFrameId() - START_FRAME;
        float scale = (float) (1.1f + Math.sin(fr / 100f));
        circle.scale(scale);
        int X = Math.round(Gdx.graphics.getWidth() / 2f - circle.getWidth() / 2f);
        int Y = Math.round(Gdx.graphics.getHeight() / 2f - circle.getHeight() / 2f);
        circle.setPosition(X, Y);
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
