package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WavedEdgeTrick {
    public SpriteBatch batch2;

    private long initialFrame;
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    private ShapeRenderer shapeRenderer;
    private float sineWidth = 20f;
    private float xExit = 0f;
    private Color topColor = new Color(0f, 0f, 0.5f, 1f);
    private Color bottomColor = new Color(0.4f, 0.7f, 1f, 1f);

    private static final long TRICK1_GRADUAL_EXIT = 1000;

    public WavedEdgeTrick(int x1, int y1, int x2, int y2, SpriteBatch batch2, ShapeRenderer shapeRenderer, float sineWidth, float xExit) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.batch2 = batch2;
        this.shapeRenderer = shapeRenderer;
        this.sineWidth = sineWidth;
        this.xExit = xExit;
    }


    public void update() {

        var frame = Gdx.graphics.getFrameId(); // this frame does not need to be relative. It's only for cycling color gradient

        //System.out.print(" fr=" + frame + " ");
        double x = (frame + 400) / 1000f * 3.14;
        topColor.b = (float) Math.abs(Math.sin(x));

        sineWidth = (float) (23 + 12 * Math.cos(x / 3));
        conditionalExit();
    }

    public void render() {
        int width = this.x2 - x1;
        int height = this.y2 - this.y1;

        drawGradientRectangle(x1, y1, width, height);
        drawEdgeWaves();

    }

    private void drawEdgeWaves() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (float yy = y1; yy < this.y2; yy++) {
            shapeRenderer.setColor(Color.BLACK);
            float xx = (float) (100 + 50 * Math.sin(yy / Math.PI / sineWidth) + 20 * Math.sin(0.9 * yy / 15));
            float xLeft = xx + xExit;
            shapeRenderer.line(x1, yy, xLeft + x1, yy);
            shapeRenderer.line(this.x2, this.y2 - yy + this.y1, this.x2 - xLeft, this.y2 - yy + this.y1 - 1);
        }

        if (this.getRelativeFrame() > TRICK1_GRADUAL_EXIT) {
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

        if (this.xExit > (float) Gdx.graphics.getWidth() / 2)
            return false;
        else if (this.getRelativeFrame() > TRICK1_GRADUAL_EXIT)
            this.xExit++;

        return true;
    }

    private long getRelativeFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - this.initialFrame;
    }

    public void setInitialFrame(long initialFrame) {
        this.initialFrame = initialFrame;
    }
}
