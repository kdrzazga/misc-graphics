package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GradientLines {

    private int x, y, width, height;
    private Color color;
    private ShapeRenderer shapeRenderer;

    public GradientLines(ShapeRenderer shapeRenderer, int x, int y, int width, int height, Color startingColor) {
        this.shapeRenderer = shapeRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = startingColor;
    }

    private void update(boolean r, boolean g, boolean b) {
        if (r) this.color.r = adaptColorValue(color.r);
        if (g) this.color.g = adaptColorValue(color.g);
        if (b) this.color.b = adaptColorValue(color.b);
    }

    private float adaptColorValue(float rgb) {
        float value = Math.min(1f, rgb + 0.01f);
        if (value >= 1f) value = 0;
        return value;
    }

    public void draw( int frameSkip) {
        if (Gdx.graphics.getFrameId() % frameSkip != 0)
            return;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (float y = this.y; y < this.y + this.height; y += this.height / 255f) {

            this.update(false, false, true);
            shapeRenderer.setColor(this.color);
            shapeRenderer.rect(this.x, y, this.x + width, this.height / 255f);
            System.out.println(y + " " + this.color.r);
        }
        shapeRenderer.end();
    }
}
