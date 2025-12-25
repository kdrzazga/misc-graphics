package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public final class TrickScene2 extends Scene {

    public SpriteBatch batch2;

    private StarsArray starsArray;
    private ShapeRenderer shapeRenderer;

    public TrickScene2() {
        super("trick-scene2");
    }

    @Override
    public void create() {
        starsArray = new StarsArray(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        starsArray.spread = 0.75;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {
        starsArray.move();

    }

    @Override
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.starsArray.draw(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

}
