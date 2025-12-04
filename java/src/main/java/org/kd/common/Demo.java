package org.kd.common;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public abstract class Demo {

    public static LwjglApplicationConfiguration createConfig(String title) {
        var config = new LwjglApplicationConfiguration();
        config.title = title;
        config.width = 640;
        config.height = 480;
        config.fullscreen = true;

        return config;
    }
}
