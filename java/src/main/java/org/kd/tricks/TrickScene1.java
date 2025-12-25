package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

import java.util.stream.IntStream;

public final class TrickScene1 extends Scene {

    public SpriteBatch batch2;

    private ShapeRenderer shapeRenderer;
    private float sineWidth = 20f;
    private float xExit = 0f;

    private Color topColor = new Color(0f, 0f, 0.5f, 1f);
    private Color bottomColor = new Color(0.4f, 0.7f, 1f, 1f);

    public TrickScene1() {
        super("trick-scene1");
    }

    @Override
    public void create() {
        this.shapeRenderer = new ShapeRenderer();
        this.batch2 = new SpriteBatch();
    }

    @Override
    public void update(float delta) {
        var frame = Gdx.graphics.getFrameId();

        System.out.print(" fr=" + frame + " ");
        double x = (frame + 400) / 1000f * 3.14;
        topColor.b = (float) Math.abs(Math.sin(x));

        sineWidth = (float) (23 + 12 * Math.cos(x / 3));
        conditionalExit();
    }

    @Override
    public void render() {
        var frame = Gdx.graphics.getFrameId();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.rect(
                0,                                 // x
                0,                                 // y
                Gdx.graphics.getWidth(),          // width
                Gdx.graphics.getHeight(),         // height
                topColor,      // top‑left
                topColor,      // top‑right
                bottomColor,   // bottom‑right
                bottomColor    // bottom‑left
        );

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (float y = 0; y < Gdx.graphics.getHeight(); y++) {
            shapeRenderer.setColor(Color.BLACK);
            float x2 = (float) (100 + 50 * Math.sin(y / Math.PI / sineWidth) + 20 * Math.sin(0.9 * y / 15));
            float xLeft = x2 + xExit;
            shapeRenderer.line(0f, y, xLeft, y);
            shapeRenderer.line(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - y, Gdx.graphics.getWidth() - x2 - xExit, Gdx.graphics.getHeight() - y);

        }

        if (frame > Global.TRICK1_GRADUAL_EXIT) {
            randomStars();
        }

        shapeRenderer.end();
    }

    private boolean conditionalExit() {
        var frame = Gdx.graphics.getFrameId();

        if (this.xExit > (float) Gdx.graphics.getWidth() / 2)
            return false;
        else if (frame > Global.TRICK1_GRADUAL_EXIT)
            this.xExit++;

        return true;
    }

    private void randomStars() {
        shapeRenderer.setColor(Color.WHITE);
        int w = Gdx.graphics.getWidth();
        var x = (float) (xExit * Math.random());
        int h = Gdx.graphics.getHeight();
        var y = (float) (h / 3 * Math.random());

        var amount = 33;

        IntStream.range(0, amount).forEach(j -> {
            double i = Math.min(w, h) / (float)amount * j;
            shapeRenderer.rect((float) (x/11 + i * Math.random()), (float) (y + i * Math.random()), 1, 1);
            shapeRenderer.rect(w - (float) (x + i/11 * Math.random()), (float) (y + i * Math.random()), 1, 1);
            shapeRenderer.rect(w - (float) (x + i/11 * Math.random()), h - (float) (y + i * Math.random()), 1, 1);
        });
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
