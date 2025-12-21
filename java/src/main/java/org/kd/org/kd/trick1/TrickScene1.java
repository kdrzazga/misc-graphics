package org.kd.org.kd.trick1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public class TrickScene1 extends Scene {

    private ShapeRenderer shapeRenderer;
    public SpriteBatch batch2;

    public TrickScene1() {
        super("trick-scene1");
    }

    @Override
    public void create() {
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

// top‑of‑screen color (e.g. dark blue)
        Color topColor    = new Color(0f, 0f, 0.5f, 1f);
// bottom‑of‑screen color (e.g. light blue)
        Color bottomColor = new Color(0.4f, 0.7f, 1f, 1f);

// rect(x, y, width, height, topLeft, topRight, bottomRight, bottomLeft)
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
