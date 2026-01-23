package org.kd.hellogdx.zoom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ZoomingCamera extends ApplicationAdapter {
    SpriteBatch batch;
    private OrthographicCamera camera;
    Sprite monster;

    private float zoomSpeed = 0.5f;
    private float minZoom = 0.5f;
    private float maxZoom = 2.0f;
    private boolean zoomingIn = true;

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        monster = new Sprite(new Texture("c64.png"));
        monster.setPosition(320 - monster.getWidth() / 2f, 240 - monster.getHeight() / 2f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        camera.zoom = 1f;
    }

    public void update() {
        //camera.
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (zoomingIn) {
            camera.zoom -= zoomSpeed * Gdx.graphics.getDeltaTime();
            if (camera.zoom <= minZoom) {
                camera.zoom = minZoom;
                zoomingIn = false; // Switch direction
            }
        } else {
            camera.zoom += zoomSpeed * Gdx.graphics.getDeltaTime();
            if (camera.zoom >= maxZoom) {
                camera.zoom = maxZoom;
                zoomingIn = true;
            }
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        monster.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
