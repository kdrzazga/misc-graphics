package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.C64Helper;
import org.kd.common.Helper;

public class Scene3Counting implements Screen {

    final static long START_FRAME = Scene2Karateka.START_FRAME + 780;
    private AnimatedSpriteV circle;
    private SpriteBatch batch;
    private float blueValue, circleSize;
    private int circleX;
    private BitmapFont font;

    @Override
    public void show() {
        font = C64Helper.createFont(30, "Big Daddy LED TFB.ttf");

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
        float scale = (float) (circleSize + 0.1f + Math.sin(fr / 20f));
        /*if (fr > 400) {
            var value = fr - 400;
            if (scale - 0.01f * value >= 0) scale -= 0.01f * value;
        }*/
        circle.scale(scale);

        Float Y = Gdx.graphics.getHeight() / 2 - circle.getHeight() / 2;

        circle.setPosition(Math.round(circleX), Math.round(Y));

        System.out.print(fr + " ");
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
