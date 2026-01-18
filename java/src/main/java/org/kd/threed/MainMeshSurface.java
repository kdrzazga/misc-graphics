package org.kd.threed;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class MainMeshSurface {
    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Textured";
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new MeshSurface(), config);
    }
}
