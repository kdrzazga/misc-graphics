package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.tricks.GradientRectangleTrick;

public class WavedEdgeTrick extends GradientRectangleTrick {

    private float sineWidth = 20f;
    private float xExit = 0f;

    private static final long TRICK1_GRADUAL_EXIT = 1000;

    public WavedEdgeTrick(int x1, int y1, int x2, int y2, SpriteBatch batch2, ShapeRenderer shapeRenderer, float sineWidth, float xExit) {
        super(x1, y1, x2, y2, batch2, shapeRenderer);
        this.sineWidth = sineWidth;
        this.xExit = xExit;
    }

    public void update() {
        super.update(false,false,true);
        var frame = Gdx.graphics.getFrameId(); // this frame does not need to be relative. It's only for cycling color gradient

        double x = (frame + 400) / 1000f * 3.14;
        sineWidth = (float) (23 + 12 * Math.cos(x / 3));
        conditionalExit();
    }

    public void render() {
        drawGradientRectangle();
        drawEdgeWaves();
    }

    public void drawGradientRectangle() {
        int width = this.x2 - x1;
        int height = this.y2 - this.y1;

        drawGradientRectangle(x1, y1, width, height);
    }

    public void drawEdgeWaves() {
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

}
