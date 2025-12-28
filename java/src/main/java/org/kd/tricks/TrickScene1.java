package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

import java.util.stream.IntStream;

public final class TrickScene1 extends Scene {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    public SpriteBatch batch2;

    private ShapeRenderer shapeRenderer;
    private float sineWidth = 20f;
    private float xExit = 0f;

    private Color topColor = new Color(0f, 0f, 0.5f, 1f);
    private Color bottomColor = new Color(0.4f, 0.7f, 1f, 1f);

    public TrickScene1(int x1, int y1, int x2, int y2) {
        super("trick-scene1");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
        int width = this.x2 - x1;
        int height = this.y2 - this.y1;

        var frame = Gdx.graphics.getFrameId();
        drawGradientRectangle(x1, y1, width, height);
        drawEdgeWaves(frame);
    }

    private void drawEdgeWaves(long frame) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (float yy = y1; yy < this.y2; yy++) {
            shapeRenderer.setColor(Color.BLACK);
            float xx = (float) (100 + 50 * Math.sin(yy / Math.PI / sineWidth) + 20 * Math.sin(0.9 * yy / 15));
            float xLeft = xx + xExit;
            shapeRenderer.line(x1, yy, xLeft + x1, yy);
            shapeRenderer.line(this.x2, this.y2 - yy + this.y1, this.x2 - xLeft, this.y2 - yy + this.y1 - 1);
        }

        if (frame > Global.TRICK1_GRADUAL_EXIT) {
            //randomStars();
        }

        shapeRenderer.end();
    }

    private void drawGradientRectangle(int x, int y, int width, int height) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.rect(
                x, y, width, height,
                topColor,      // top‑left
                topColor,      // top‑right
                bottomColor,   // bottom‑right
                bottomColor    // bottom‑left
        );

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
        var y = (float) ((float) h / 3 * Math.random());

        var amount = 33;

        IntStream.range(0, amount).forEach(j -> {
            double i = Math.min(w, h) / (float) amount * j;
            shapeRenderer.rect((float) (x / 11 + i * Math.random()), (float) (y + i * Math.random()), 1, 1);
            shapeRenderer.rect(w - (float) (x + i / 11 * Math.random()), (float) (y + i * Math.random()), 1, 1);
            shapeRenderer.rect(w - (float) (x + i / 11 * Math.random()), h - (float) (y + i * Math.random()), 1, 1);
        });
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
