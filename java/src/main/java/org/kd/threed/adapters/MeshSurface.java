package org.kd.threed.adapters;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import org.kd.common.tricks.Mesh;

public class MeshSurface extends ApplicationAdapter {
    public final Color BACKGROUND_COLOUR = new Color(153f / 255f, 255f / 255f, 236f / 255f, 1.0f);

    private PerspectiveCamera cam;
    private CameraInputController camController;
    private Environment environment;
    private Array<ModelInstance> instances;
    private ModelBatch modelBatch;

    @Override
    public void create() {
        setUpCamera();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        modelBatch = new ModelBatch();

        setupEnv();

        var meshPixmap = Mesh.createMeshPixmap(Color.CYAN,40, 5 , 5);
        var textureGround = new Texture(meshPixmap);
        textureGround.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        var textureGroundRegion = new TextureRegion(textureGround);
        int repeats = 3;
        textureGroundRegion.setRegion(0, 0, textureGround.getWidth() * repeats, textureGround.getHeight() * repeats);

        var modelBuilder = new ModelBuilder();

        Model modelGround = modelBuilder.createBox(100f, 1f, 100f,
                new Material(TextureAttribute.createDiffuse(textureGroundRegion)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        instances = new Array<>();
        instances.add(new ModelInstance(modelGround, 0, -1, 0));

    }

    private void setupEnv() {
        environment = new Environment();

        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.6f, 0.6f, 0.6f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }

    private void setUpCamera() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 70f, 150f);
        cam.lookAt(0, 0, 0.5f);
        cam.near = 1f; //near and far definies when the framewroks starts rendering objects and when stops
        cam.far = 1300;
        cam.update();
    }

    @Override
    public void render() {
        camController.update();

        // render
        ScreenUtils.clear(BACKGROUND_COLOUR, true);
        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }
}
