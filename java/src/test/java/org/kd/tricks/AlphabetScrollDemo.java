package org.kd.tricks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class AlphabetScrollDemo {
    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Alphabet Scroll";
        config.width = 640;
        config.height = 480;
        config.fullscreen = false;

        var display = new AlphabetDisplay();
        new LwjglApplication(display, config);
    }
}
