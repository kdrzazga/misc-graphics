package org.kd.c64;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.kd.hellogdx.Animation;

public class Demo {
    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.title = "First Cats";
        config.width = 640;
        config.height = 480;
        config.fullscreen = true;

        new LwjglApplication(new Animation(), config);
    }

}