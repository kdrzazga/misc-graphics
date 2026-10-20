package org.kd.anniversaries27;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.kd.common.Demo;
import org.kd.common.Globals;

/**
 * Runs in full screen by default, scaled with the demo's proportions kept.
 * Arguments w, window or windowed run it in a 1024x768 window instead.
 */
public class Anniversaries27Demo extends Demo {

    public static void main(String[] args) {
        Anniversaries27Demo.analyzeArguments(args);
        int demoWidth = 1024;
        int demoHeight = 768;

        var config = Demo.createConfig("Windows 3.11");
        if (fullscreen) {
            config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        } else {
            config.width = demoWidth;
            config.height = demoHeight;
            config.fullscreen = false;
        }

        System.out.println(Globals.startTime);

        new LwjglApplication(new Win311IntroManager(demoWidth, demoHeight), config);
    }
}
