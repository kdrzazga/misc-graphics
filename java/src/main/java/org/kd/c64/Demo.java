package org.kd.c64;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.winter.WinterAnimationMgr;

public class Demo extends org.kd.common.Demo {

    public static void main(String[] args) {
        var config = org.kd.common.Demo.createConfig("C64 Demo");
        new LwjglApplication(new AnimationManager(), config);
    }
}
