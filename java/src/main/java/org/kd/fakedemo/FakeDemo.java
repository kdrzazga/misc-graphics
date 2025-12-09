package org.kd.fakedemo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class FakeDemo extends org.kd.common.Demo {

    public static void main(String[] args) {
        var config = org.kd.common.Demo.createConfig("C64 Demo");
        new LwjglApplication(new AnimationManager(), config);
    }
}
