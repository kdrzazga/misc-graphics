package org.kd.hellogdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.kd.hellogdx.screens.Screen1;

public class ScreensGame extends Game {
    @Override
    public void create() {
        setScreen(new Screen1(this));
    }

    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.title = "Screens Game";
        config.width = 640;
        config.height = 480;
        config.fullscreen = true;

        new LwjglApplication(new ScreensGame(), config);
    }
}
