package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GradientRectangleTrick {
    public SpriteBatch batch2;
    protected long initialFrame;
    protected final int x1;
    protected final int y1;
    protected final int x2;
    protected final int y2;
    protected ShapeRenderer shapeRenderer;

    protected Color topColor = new Color(0f, 0f, 0.5f, 1f);
    protected Color bottomColor = new Color(0.4f, 0.7f, 1f, 1f);
    private float frequency;

    public GradientRectangleTrick(int x1, int y1, int x2, int y2, SpriteBatch batch2, ShapeRenderer shapeRenderer) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.batch2 = batch2;
        this.shapeRenderer = shapeRenderer;
        frequency = 1000f;
    }

    public void update(boolean r, boolean g, boolean b) {
        var frame = Gdx.graphics.getFrameId(); // this frame does not need to be relative. It's only for cycling color gradient

        //System.out.print(" fr=" + frame + " ");
        double x = (frame + 400) / frequency * 3.14;
        if (r) topColor.r = (float) Math.abs(Math.sin(x));
        if (g) topColor.g = (float) Math.abs(Math.sin(x));
        if (b) topColor.b = (float) Math.abs(Math.sin(x));
    }

    public void drawGradientRectangle() {
        var w = Math.abs(this.x2 - this.x1);
        var h = Math.abs(this.y2 - this.y1);
        drawGradientRectangle(this.x1, this.y1, w, h);
    }

    public void drawGradientRectangle(int x, int y, int width, int height) {
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

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }
}
