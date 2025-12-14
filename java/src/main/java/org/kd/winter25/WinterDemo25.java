package org.kd.winter25;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class WinterDemo25 extends org.kd.common.Demo {

    public static void main(String[] args) {
        WinterDemo25.analyzeArguments(args);
        var config = org.kd.common.Demo.createConfig("Winter Demo");
        config.fullscreen = false;
        new LwjglApplication(new WinterAnimationMgr(), config);
    }
}
