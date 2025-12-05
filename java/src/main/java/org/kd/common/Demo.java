package org.kd.common;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public abstract class Demo {

    public static LwjglApplicationConfiguration createConfig(String title) {
        var config = new LwjglApplicationConfiguration();
        config.title = title;
        config.width = Globals.SCREEN_WIDTH;
        config.height = Globals.SCREEN_HEIGHT;
        config.fullscreen = true;

        return config;
    }
}
