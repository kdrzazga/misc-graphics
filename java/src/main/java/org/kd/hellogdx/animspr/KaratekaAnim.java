package org.kd.hellogdx.animspr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;

public class KaratekaAnim extends ApplicationAdapter {
    SpriteBatch batch;
    AnimatedSpriteV sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();

        sprite = new AnimatedSpriteV("kickass/karateka3.png", 53, 0.1f, 25, 25);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //sprite.drawClipped(batch,24,24,1100,250);
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
