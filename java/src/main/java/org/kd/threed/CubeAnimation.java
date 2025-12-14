package org.kd.threed;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Normal;
import static com.badlogic.gdx.graphics.VertexAttributes.Usage.Position;

public class CubeAnimation extends ApplicationAdapter {
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;

    private Model model;
    private ModelInstance instance;

    private Model contourModel;
    private ModelInstance contourInstance;

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

        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);

        modelBatch = new ModelBatch();

        var modelBuilder = new ModelBuilder();
        var material = new Material(ColorAttribute.createDiffuse(0f, 0.5f, 1f, 1f));

        model = modelBuilder.createBox(100f, 100f, 100f, material, Position | Normal);
        instance = new ModelInstance(model);

        var contourMaterial = new Material(ColorAttribute.createDiffuse(1f, 0.5f, 0f, 1f));
        contourModel = modelBuilder.createBox(111f, 111f, 111f, contourMaterial,
                Position | Normal);
        contourInstance = new ModelInstance(contourModel);

        environment = new Environment();
    }

    @Override
    public void render() {
        camController.update();

        Gdx.gl.glClearColor(0.1f, 0.7f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        instance.transform.rotate(0, 1, 0, 30*delta);
        contourInstance.transform.rotate(0, 1, 0, 30*delta);

        modelBatch.begin(camera);
        modelBatch.render(contourInstance, environment);
        modelBatch.render(instance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
