package org.kd.xmas25;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class CommodoreXmas extends org.kd.common.Demo {

    public static void main(String[] args) {
        org.kd.winter25.WinterDemo25.analyzeArguments(args);
        var config = org.kd.common.Demo.createConfig("Commodore Xmas 25 (Dream 210)");
        //config.fullscreen = false;
        new LwjglApplication(new DreamAnimationMgr(), config);
    }
}
