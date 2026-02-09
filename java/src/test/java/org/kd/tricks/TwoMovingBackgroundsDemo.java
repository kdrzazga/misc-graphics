package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.tricks.TwoMovingBackgrounds;

public class TwoMovingBackgroundsDemo extends ApplicationAdapter {

    TwoMovingBackgrounds trick;
    SpriteBatch batch;

    @Override
    public void create() {
        super.create();
        var path1 = "bkg/physics.jpg";
        var path2 = "bkg/MaxwellFormulas.png";
        trick = new TwoMovingBackgrounds(path1, path2, 800, 600);
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        super.render();
        var frame = Gdx.graphics.getFrameId();
        trick.update();
        batch.begin();
        trick.draw(batch);
        batch.end();
        System.out.print(frame + " ");
    }

    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Test";
        config.width = 800;
        config.height = 600;
        config.fullscreen = false;

        var display = new TwoMovingBackgroundsDemo();
        new LwjglApplication(display, config);
    }
}
