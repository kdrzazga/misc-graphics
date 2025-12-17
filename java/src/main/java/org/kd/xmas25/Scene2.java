package org.kd.xmas25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.*;

public class Scene2 extends BasicC64Screen {

    public SpriteBatch batch2;
    public ShapeRenderer shapeRenderer;

    private TravellingLogo scroll1;
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
        var scroll1 = new Texture("dream210/scroll/stanza1.bmp");
        this.scroll1 = new TravellingLogo(scroll1, Globals.SCREEN_WIDTH, 100, 2000, 16);
        this.scroll1.spriteSpeed = 70f;

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

        this.scroll1.move(delta, Globals.SCREEN_WIDTH);

        if (frame > WishesHelper.SCENE2_START_FRAME + 50) {
            this.mikolaj1.move();

            handleScrollTextChange(frame);

            ConsoleLogger.logBannerWithElapsedTime(BannerMCh.lines);
        }
    }

    private void handleScrollTextChange(long frame) {
        if (frame == WishesHelper.SCENE2_START_FRAME + 2340)
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza2.bmp"));
        else if (frame == WishesHelper.SCENE2_START_FRAME + 4700)
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza3.bmp"));
        else if (frame == WishesHelper.SCENE2_START_FRAME + 7000)
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza4.bmp"));
        else if (frame == WishesHelper.SCENE2_START_FRAME + 9330)
            this.scroll1.spriteSpeed = 0;
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        if (frame > WishesHelper.SCENE2_START_FRAME + 50) this.mikolaj1.draw(batch2);
        this.scroll1.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        batch2.end();

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(C64Colors.WHITE.toBadlogicColor());
        this.shapeRenderer.rect(0, 100, 80, 400);
        this.shapeRenderer.rect(800 - 80, 100, 80, 400);
        this.shapeRenderer.end();

    }
}
