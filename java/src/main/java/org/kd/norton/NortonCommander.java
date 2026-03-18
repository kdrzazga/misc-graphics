package org.kd.norton;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.common.Demo;

public class NortonCommander extends Demo {

    public static void main(String[] args) {
        NortonCommander.analyzeArguments(args);
        var config = Demo.createConfig("Norton Commander");
        config.width = 1024;
        config.height = 768;
        config.fullscreen = false;

        new LwjglApplication(new NortonManager("win311/EscapeRamos.mp3"), config);
    }
}
