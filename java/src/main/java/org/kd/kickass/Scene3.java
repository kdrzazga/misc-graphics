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
    private float blueValue, circleSize;
    private int circleX;

    @Override
    public void show() {
        System.out.println(Helper.countElapsedTime());
        blueValue = 0f;
        batch = new SpriteBatch();
        float X = Gdx.graphics.getWidth() / 2f - 250f;
        float Y = Gdx.graphics.getHeight() / 2f - 250f;

        circle = new AnimatedSpriteV("kickass/blueFireCircle.png", 13, 0.03f, Math.round(X)
                , Math.round(Y));
        circleX = Math.round(Gdx.graphics.getWidth() / 2 - circle.getWidth() / 2);
        circleSize = 1f;
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, blueValue, 1);
        update();
        batch.begin();
        circle.draw(batch);
        batch.end();
    }

    private void update() {
        var fr = Gdx.graphics.getFrameId() - START_FRAME;
        float scale = (float) (circleSize + 0.1f + Math.sin(fr / 100f));
        if (fr>400){
            var value = fr - 400;
            if (scale - 0.01f*value >=0) scale -= 0.01f*value;
        }
        circle.scale(scale);

        Float Y = Gdx.graphics.getHeight() / 2 - circle.getHeight() / 2;

        if (fr > 300) {
            blueValue += 0.00073f;
            if (fr % 3 == 0 && circleX > 200) circleX--;
        }
        if (fr > 350) {
            circleSize *= 0.995f;
        }

        circle.setPosition(Math.round(circleX), Math.round(Y));
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
