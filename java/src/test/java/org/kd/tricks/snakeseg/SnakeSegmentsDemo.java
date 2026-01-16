package org.kd.tricks.snakeseg;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class SnakeSegmentsDemo {

    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Test";
        config.width = 800;
        config.height = 600;
        config.fullscreen = false;

        var display = new Display();
        new LwjglApplication(display, config);
    }
}
