package org.kd.tricks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GradienLinesDemo {
    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Test";
        config.width = 640;
        config.height = 480;
        config.fullscreen = false;

        var display = new Display();
        new LwjglApplication(display, config);
    }
}
