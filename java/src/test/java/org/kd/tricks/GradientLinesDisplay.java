package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
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

    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Test";
        config.width = 640;
        config.height = 480;
        config.fullscreen = false;

        var display = new GradientLinesDisplay();
        new LwjglApplication(display, config);
    }
}
