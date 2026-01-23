package org.kd.hellogdx.two;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static org.kd.common.tricks.Mesh.createMeshPixmap;

public class PixmapDrawing extends ApplicationAdapter {
    Texture texture;
    SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Pixmap pixmap = createMeshPixmap(Color.RED, 70, 4, 7);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        texture.dispose();
        batch.dispose();
    }
}
