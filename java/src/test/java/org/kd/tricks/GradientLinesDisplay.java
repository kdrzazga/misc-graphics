package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GradientLinesDisplay extends ApplicationAdapter {
    GradientLines gradientLines;

    @Override
    public void create() {
        gradientLines = new GradientLines(new ShapeRenderer(), 10, 10, 400, 200, Color.BLACK);
    }

    @Override
    public void render() {
        super.render();

        gradientLines.draw(5);
    }
}
