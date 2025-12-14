package org.kd.wishesmount;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class MainDemo extends org.kd.common.Demo {

    public static void main(String[] args) {
        org.kd.winter25.WinterDemo25.analyzeArguments(args);
        var config = org.kd.common.Demo.createConfig("Dream 210 or Die");
        config.fullscreen = false;
        new LwjglApplication(new DreamAnimationMgr(), config);
    }
}
