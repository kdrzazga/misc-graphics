package org.kd.win311;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.common.Demo;

public final class Win311Demo extends Demo {

    public static void main(String[] args) {
        Win311Demo.analyzeArguments(args);
        var config = Demo.createConfig("Windows 3.11");
        config.width = 1024;
        config.height = 768;
        //config.fullscreen = false;

        new LwjglApplication(new Win311Manager(), config);
    }
}
