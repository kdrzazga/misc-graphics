package org.kd.xmas25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.BasicC64Screen;
import org.kd.common.C64Colors;
import org.kd.common.Globals;

public class Scene2 extends BasicC64Screen {

    public SpriteBatch batch2;
    public ShapeRenderer shapeRenderer;

    private Mikolaj mikolaj1;
    private boolean snowing;

    public Scene2(String id) {
        super(id);
        this.backgroundScreenPng = "winter/lblue-ready.png";
        this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
    }

    @Override
    public void create() {
        super.create();
        this.batch2 = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        var mikolajPng = new Texture("dream210/mikolaj.png");
        this.mikolaj1 = new Mikolaj(mikolajPng);

        this.borderColor = C64Colors.WHITE;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        var frame = Gdx.graphics.getFrameId();
        if (frame == WishesHelper.SCENE2_START_FRAME + 1) {
            Globals.cursorY = Math.round(0.753 * Globals.SCREEN_HEIGHT) - 2;
            Globals.BKG_COLOR = C64Colors.LIGHT_BLUE;
        }

        this.mikolaj1.move();
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        this.mikolaj1.draw(batch2);
        batch2.end();

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(C64Colors.WHITE.toBadlogicColor());
        this.shapeRenderer.rect(0, 100, 80, 400);
        this.shapeRenderer.rect(800 - 80, 100, 80, 400);
        this.shapeRenderer.end();

    }
}
