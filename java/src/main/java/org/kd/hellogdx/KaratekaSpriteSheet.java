package org.kd.hellogdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.kd.hellogdx.animspr.KaratekaAnim;

public class KaratekaSpriteSheet {
    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.title = "Sprite Animation";
        config.width = 1700;
        config.height = 480;
        config.fullscreen = false;

        new LwjglApplication(new KaratekaAnim(), config);
    }
}
