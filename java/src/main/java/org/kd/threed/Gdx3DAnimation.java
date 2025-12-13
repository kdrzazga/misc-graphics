package org.kd.threed;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public class Gdx3DAnimation extends ApplicationAdapter {
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private Model model;
    private ModelInstance instance;
    private Environment environment;
    private CameraInputController camController;

    @Override
    public void create() {
        // Set up camera
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 300f);
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 1000f;
        camera.update();

        // Input controller to move camera around
        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);

        modelBatch = new ModelBatch();

        // Build a cube model
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(100f, 100f, 100f,
                new Material(ColorAttribute.createDiffuse(0f, 0.5f, 1f, 1f)),
                com.badlogic.gdx.graphics.VertexAttributes.Usage.Position | com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        // Set up environment lighting
        environment = new Environment();
        // Add lights if needed
    }

    @Override
    public void render() {
        // Handle camera input
        camController.update();

        // Clear screen
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Animate rotation
        instance.transform.rotate(0, 1, 0, 1); // rotate around Y axis

        // Render the model
        modelBatch.begin(camera);
        modelBatch.render(instance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
