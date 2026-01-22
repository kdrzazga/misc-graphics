package org.kd.hellogdx;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.kd.hellogdx.gdxworld.SpriteSheetAnim;

public class HelloSpriteSheets {
    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.title = "Gdx World";
        config.width = 100;
        config.height = 100;
        config.fullscreen = false;

        new LwjglApplication(new SpriteSheetAnim(), config);
    }
}
