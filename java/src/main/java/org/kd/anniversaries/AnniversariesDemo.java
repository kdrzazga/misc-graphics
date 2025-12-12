package org.kd.anniversaries;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class AnniversariesDemo extends org.kd.common.Demo {

    public static void main(String[] args) {
        AnniversariesDemo.analyzeArguments(args);
        //AnniversariesDemo.fullscreen = false;
        var config = org.kd.common.Demo.createConfig("Anniversaries Demo");
        new LwjglApplication(new AnniversariesAnimationMgr(), config);

    }
}
