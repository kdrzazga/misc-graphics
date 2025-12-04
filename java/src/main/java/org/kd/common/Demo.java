package org.kd.common;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public abstract class Demo {

    public static LwjglApplicationConfiguration createConfig(String title) {
        var config = new LwjglApplicationConfiguration();
        config.title = title;
        config.width = 800;
        config.height = 600;
        config.fullscreen = true;

        return config;
    }
}
