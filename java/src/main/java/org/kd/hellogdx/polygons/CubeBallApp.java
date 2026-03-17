package org.kd.hellogdx.polygons;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class CubeBallApp extends ApplicationAdapter {

    private CubeBall cubeBall;
    private PerspectiveCamera camera;
    private ShaderProgram shader;

    @Override
    public void create() {
        cubeBall = new CubeBall();
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 0, 15);
        camera.lookAt(0, 0, 0);
        camera.near = 0.1f;
        camera.far = 100f;

        // Create a simple shader
        String vertexShader = "attribute vec3 a_position;\n" +
                "uniform mat4 u_mvpMatrix;\n" +
                "void main() {\n" +
                "    gl_Position = u_mvpMatrix * vec4(a_position, 1.0);\n" +
                "}\n";

        String fragmentShader = "void main() {\n" +
                "    gl_FragColor = vec4(1.0, 0.5, 0.0, 1.0);\n" +
                "}\n";

        shader = new ShaderProgram(vertexShader, fragmentShader);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();

        // Create transformation matrix
        Matrix4 transform = new Matrix4();
        transform.translate(0, 0, 0); // Position
        transform.rotate(Vector3.Y, Gdx.graphics.getDeltaTime() * 30); // Rotate

        // Render the cube ball
        if (shader.isCompiled()) {
            shader.begin();
            shader.setUniformMatrix("u_mvpMatrix", camera.combined.mul(transform));
            cubeBall.render(shader, transform);
            shader.end();
        }
    }

    public static void main(String[] arg) {

        var config = new LwjglApplicationConfiguration();
        config.width = 640;
        config.height = 480;
        config.fullscreen = false;

        new LwjglApplication(new CubeBallApp(), config);
    }
}
