package org.kd.org.kd.trick1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public class TrickScene1 extends Scene {

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
        shapeRenderer.setColor(Color.BLACK);
        for (float y = 0; y < Gdx.graphics.getHeight(); y++) {
            float x2 = (float) (100 + 50 * Math.sin(y / Math.PI / sineWidth) + 20 * Math.sin(0.9 * y / 15));
            shapeRenderer.line(0f, y, x2 + xExit, y);
            shapeRenderer.line(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - y, Gdx.graphics.getWidth() - x2 - xExit, Gdx.graphics.getHeight() - y);
            //System.out.println(x2 + " " + y);
        }

        shapeRenderer.end();
    }

    private boolean conditionalExit() {
        var frame = Gdx.graphics.getFrameId();

        if (this.xExit > (float) Gdx.graphics.getWidth() / 2)
            return false;
        else if (frame > 1000)
            this.xExit++;

        return true;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
