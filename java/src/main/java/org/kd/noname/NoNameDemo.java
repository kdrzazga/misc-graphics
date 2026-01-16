package org.kd.noname;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class NoNameDemo extends org.kd.common.Demo {

    public static void main(String[] args) {
        NoNameDemo.analyzeArguments(args);
        var config = org.kd.common.Demo.createConfig("NoName Demo");
        config.width = 1700;
        config.height = 990;
        config.fullscreen = false;
        new LwjglApplication(new AnimationMgr(), config);
    }
}