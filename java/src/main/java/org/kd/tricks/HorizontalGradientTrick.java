package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HorizontalGradientTrick {

    private final Color color;
    private final int period;
    private final float x, y, width, height;
    private long startingFrame;

    public HorizontalGradientTrick(float x, float y, float width, float height, Color startingColor, int period) {
        this.color = startingColor;
        this.period = Math.max(1, period);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startingFrame = -1;
    }

    public void start() {
        this.startingFrame = Gdx.graphics.getFrameId();
    }

    public void update() {
        if (this.startingFrame < 0) {
            System.err.println(HorizontalGradientTrick.class.getSimpleName() + " not started. Call start() method.");
            return;
        }

        var frame = Gdx.graphics.getFrameId() - this.startingFrame;
        if (frame % period > 0)
            return;

        color.r = Math.min(1f, color.r + 0.01f);
        color.g = Math.min(1f, color.g + 0.01f);
        color.b = Math.min(1f, color.b + 0.01f);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i <= height; i++) {
            float interpolationFactor = (float) i / height; // interpolation factor from 0 to 1
            float r = color.r + interpolationFactor * (1f - color.r);
            float g = color.g + interpolationFactor * (1f - color.g);
            float b = color.b + interpolationFactor * (1f - color.b);
            shapeRenderer.setColor(r, g, b, 1f);
            shapeRenderer.line(x, y + i, x + width, y + i);
        }
        shapeRenderer.end();
    }

}
