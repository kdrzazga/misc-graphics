package org.kd.hellogdx.animspr;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSprite;

public class SpriteSheetAnim extends ApplicationAdapter {
    SpriteBatch batch;
    AnimatedSprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();

        sprite = new AnimatedSprite("spritesheets/owls.png", 2, 0.2f, 25, 25);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
