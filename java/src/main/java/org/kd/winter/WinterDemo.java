package org.kd.winter;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import org.kd.hellogdx.Animation;

public class WinterDemo extends org.kd.common.Demo {

    public static void main(String[] args) {
        var config = org.kd.common.Demo.createConfig("Winter Demo");
        new LwjglApplication(new Animation(), config);
    }
}
