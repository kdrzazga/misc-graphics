package org.kd.threed.adapters;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import org.kd.common.C64Colors;

import static com.badlogic.gdx.graphics.VertexAttributes.Usage.*;

public class Cylinder3DExample extends ApplicationAdapter {
    PerspectiveCamera cam;
    ModelBatch modelBatch;
    SpriteBatch batch;
    Environment environment;
    Model model;
    ModelInstance instance;
    float rotationY = 0f;
    private BitmapFont font;

    @Override
    public void create() {
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 0f, 5f);
        cam.lookAt(0f, 0f, 0f);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        modelBatch = new ModelBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        var modelBuilder = new ModelBuilder();
        var texture = new Texture(Gdx.files.internal("c64.png"));
        var material = new Material(TextureAttribute.createDiffuse(texture));

        var radius = 4f;
        var radiusTop = 4f;
        var height = 2f;
        var segments = 32;

        model = modelBuilder.createCylinder(radius, height, radiusTop, segments, material,
                Position | Normal | TextureCoordinates);

        instance = new ModelInstance(model);

        batch = new SpriteBatch();
        createFont();
    }

    private void createFont() {
        var generator = new FreeTypeFontGenerator(Gdx.files.internal("Pixels.ttf"));
        var parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 61;
        parameter.color = C64Colors.LIGHT_BLUE.toBadlogicColor();
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void render() {
        handleInput();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        cam.update();

        instance.transform.setToRotation(Vector3.Y, rotationY);
        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();

        batch.begin();
        font.draw(batch, "Use cursor keys <- and -> to rotate the cylinder.", 2, 62 + 10);
        batch.end();
    }

    public void rotateLeft() {
        rotationY -= 3f;
    }

    public void rotateRight() {
        rotationY += 3f;
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
            rotateLeft();
        }
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {
            rotateRight();
        }
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        model.dispose();
    }
}
