package org.kd.tsmc40;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.BaseScene2;

public class Scene2City extends BaseScene2 {
    final static long START_FRAME = 2090;

    private AnimatedSpriteV city;

    private SpriteBatch batch;

    @Override
    public void show() {
        batch = new SpriteBatch();
        float X = Gdx.graphics.getWidth() / 2f;// - 250f/2f;
        int Y = 0;//Math.round(Gdx.graphics.getHeight()) - 1;

        String pic ="tsmc40/citytrip.png";
        city = new AnimatedSpriteV(pic, 35, 0.05f, 0, Y);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);

        batch.begin();

        city.draw(batch);
        System.out.print("drawing ");
        batch.end();
    }
}
