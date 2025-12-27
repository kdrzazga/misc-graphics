package org.kd.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;

public final class TrickScene2 extends Scene {

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    public SpriteBatch batch2;

    private StarsArray starsArray;
    private ShapeRenderer shapeRenderer;

    public TrickScene2(int x1, int y1, int x2, int y2) {
        super("trick-scene2");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
        this.starsArray.draw(shapeRenderer, x1, y1, x2, y2);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

}
