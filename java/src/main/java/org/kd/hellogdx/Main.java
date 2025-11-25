package org.kd.hellogdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.title = "First Cats";
        config.width = 640;
        config.height = 480;
        config.fullscreen = true;

        new LwjglApplication(new Animation(), config);
    }

}