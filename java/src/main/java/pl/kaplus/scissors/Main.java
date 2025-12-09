package pl.kaplus.scissors;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.title  = "libGDX ScissorStack Example";
        config.fullscreen = false;
        new LwjglApplication(new Scissors(), config);
    }
}
