package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;
import org.kd.common.tricks.WavedEdgeTrick;

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

    private WavedEdgeTrick wavedEdgeTrick;


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
        this.wavedEdgeTrick = new WavedEdgeTrick(x1, y1, x2, y2, this.batch2, this.shapeRenderer, this.sineWidth, this.xExit);
        this.wavedEdgeTrick.setInitialFrame(Gdx.graphics.getFrameId());
    }

    @Override
    public void update(float delta) {
        this.wavedEdgeTrick.update();
    }

    @Override
    public void render() {
        this.wavedEdgeTrick.render();
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
