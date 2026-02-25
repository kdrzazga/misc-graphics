package org.kd.anniversaries27;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.common.Demo;
import org.kd.common.Globals;

public class Anniversaries27Demo extends Demo {

    public static void main(String[] args) {
        Anniversaries27Demo.analyzeArguments(args);
        var config = Demo.createConfig("Windows 3.11");
        config.width = 1024;
        config.height = 768;
        config.fullscreen = false;

        System.out.println(Globals.startTime);

        new LwjglApplication(new Win311IntroManager(), config);
    }
}
