package org.kd.hellogdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.kd.hellogdx.video.Player;

public class PlayVideo {

    public static void main(String[] args) {
        var config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Video");
        config.setWindowedMode(980, 540);
        new Lwjgl3Application(new Player(), config);
    }
}