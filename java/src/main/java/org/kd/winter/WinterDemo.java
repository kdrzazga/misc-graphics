package org.kd.winter;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class WinterDemo extends org.kd.common.Demo {

    public static void main(String[] args) {
        WinterDemo.analyzeArguments(args);
        var config = org.kd.common.Demo.createConfig("Winter Demo");
        //config.fullscreen = false;
        new LwjglApplication(new WinterAnimationMgr(), config);
    }
}
