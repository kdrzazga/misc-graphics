package org.kd.hellogdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.kd.hellogdx.rotation.ZoomingCamera;

public class HelloCameraZoom {
    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.title = "Hello Rotation";
        config.width = 640;
        config.height = 480;
        config.fullscreen = false;

        new LwjglApplication(new ZoomingCamera(), config);
    }
}
