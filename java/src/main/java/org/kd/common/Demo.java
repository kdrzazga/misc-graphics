package org.kd.common;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Demo {

    protected static boolean fullscreen = true;

    protected static void analyzeArguments(String[] args) {
        Set<String> argsSet = new HashSet<>(Arrays.asList(args));
        fullscreen = !(argsSet.contains("window") || argsSet.contains("--window") || argsSet.contains("nofullscreen"));
    }

    public static LwjglApplicationConfiguration createConfig(String title) {
        var config = new LwjglApplicationConfiguration();
        config.title = title;
        config.width = Globals.SCREEN_WIDTH;
        config.height = Globals.SCREEN_HEIGHT;
        config.resizable = false;
        config.fullscreen = fullscreen;

        return config;
    }
}
