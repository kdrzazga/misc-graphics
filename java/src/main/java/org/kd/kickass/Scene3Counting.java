package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.BaseScene2;
import org.kd.common.C64Helper;
import org.kd.common.Helper;

public class Scene3Counting  extends BaseScene2 {

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
        circleX = Math.round(Gdx.graphics.getWidth() / 2f - circle.getWidth() / 2f);
        circleSize = 1f;
    }

    @Override
    public void render(float v) {
        var fr = Gdx.graphics.getFrameId() - START_FRAME;

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, blueValue, 1);
        update();
        batch.begin();
        if (fr < 300) circle.draw(batch);
        batch.end();
    }

    private void update() {
        var fr = Gdx.graphics.getFrameId() - START_FRAME;
        float scale = (float) (circleSize + 0.1f + Math.sin(fr / 20f));
        if (fr > 299) {
            blueValue += 0.03f;
        }
        circle.scale(scale);

        Float Y = Gdx.graphics.getHeight() / 2 - circle.getHeight() / 2;

        circle.setPosition(circleX, Math.round(Y));

        System.out.print(fr + " ");
    }

}
