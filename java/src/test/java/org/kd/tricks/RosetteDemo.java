package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.tricks.Rosette;

public class RosetteDemo extends ApplicationAdapter {

    Rosette closingTriangles;
    ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        super.create();
        var frame = Gdx.graphics.getFrameId();
        closingTriangles = new Rosette(frame, Color.BLACK);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        closingTriangles.render(shapeRenderer);
    }

    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Test Closing Triangles";
        config.width = 1920;
        config.height = 1080;
        config.fullscreen = false;

        var display = new RosetteDemo();
        new LwjglApplication(display, config);
    }
}
