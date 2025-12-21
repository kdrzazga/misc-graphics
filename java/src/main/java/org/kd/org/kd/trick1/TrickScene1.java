package org.kd.org.kd.trick1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public class TrickScene1 extends Scene {

    public SpriteBatch batch2;

    private ShapeRenderer shapeRenderer;
    private Color topColor = new Color(0f, 0f, 0.5f, 1f);
    private Color bottomColor = new Color(0.4f, 0.7f, 1f, 1f);

    public TrickScene1() {
        super("trick-scene1");
    }

    @Override
    public void create() {
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {
        var frame = Gdx.graphics.getFrameId();
        double x = frame / 1000f * 3.14;
        topColor.b = (float) Math.abs(Math.sin(x));
    }

    @Override
    public void render() {
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
    }

    @Override
    public void dispose() {

    }
}
