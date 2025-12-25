package org.kd.tricks;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Trick1Main extends org.kd.common.Demo {

    public static void main(String[] args) {
        Trick1Main.analyzeArguments(args);
        var config = org.kd.common.Demo.createConfig("Trick1");
        config.fullscreen = false;
        new LwjglApplication(new TrickAppMgr(), config);
    }
}
