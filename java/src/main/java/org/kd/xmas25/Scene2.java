package org.kd.xmas25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.*;
import org.kd.common.winter.WinterEffects;

import java.util.List;

public class Scene2 extends BasicC64Screen {

    public SpriteBatch batch2;
    public ShapeRenderer shapeRenderer;

    private Mikolaj mikolaj1;
    private PetsciiSnowman snowman;
    private TravellingLogo scroll1;
    private Sprite forest;
    private Sprite mountain;
    private Sprite socks;
    private List<Sprite> snowflakes;
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

        this.snowman = new PetsciiSnowman();
        this.borderColor = C64Colors.WHITE;

        this.forest = new Sprite(new Texture("dream210/las2.png"));
        this.forest.setScale(0.1f);
        this.forest.setPosition(150, 150);

        this.mountain = new Sprite(new Texture("dream210/mountains.png"));
        this.mountain.setPosition(235, 156);
        this.mountain.setScale(1.95f);

        this.socks = new Sprite(new Texture("dream210/socks.png"));
        this.socks.setPosition(235, 170 - 17);
        this.socks.setScale(2.05f, 1.5f);

        this.snowflakes = WinterEffects.createSnowflakeSprites();
        this.snowflakes.forEach(flake -> flake.setX((float) (flake.getX() + 21 * Math.random())));
        for (int i = 0; i < this.snowflakes.size(); i++) {
            float y = 101 * (i % 7);
            this.snowflakes.get(i).setY(y);
        }

        var scroll1 = new Texture("dream210/scroll/stanza1.bmp");
        this.scroll1 = new TravellingLogo(scroll1, Globals.SCREEN_WIDTH, 100, 2000, 16);
        this.scroll1.spriteSpeed = 100f;

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

        if (frame > WishesHelper.SCENE2_START_FRAME + 400 && this.snowman.getScaleY() < 0.55f) {
            this.snowman.setScale(this.snowman.getScaleX(), this.snowman.getScaleY() + 0.001f);
        }

        if (frame > WishesHelper.SCENE2_START_FRAME + 800) {
            var alpha = Math.PI * (WishesHelper.SCENE2_START_FRAME + 800 - frame) / 50;
            var y = (float) (0.5 + 0.02 * Math.sin(alpha));
            this.snowman.setScale(0.55f, y);
        }

        if (this.snowing) {
            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (frame > 2340 + startIndex * 100) {
                    WinterEffects.snow(startIndex, this.snowflakes);
                }
            }
        }
    }

    private void handleScrollTextChange(long frame) {
        if (frame == WishesHelper.SCENE2_START_FRAME + 1675) {
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza2.bmp"));
            this.snowing = true;
        } else if (frame == WishesHelper.SCENE2_START_FRAME + 3400)
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza3.bmp"));
        else if (frame == WishesHelper.SCENE2_START_FRAME + 4900)
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza4.bmp"));
        else if (frame == WishesHelper.SCENE2_START_FRAME + 6900)
            this.scroll1.spriteSpeed = 0;
    }

    @Override
    public void render() {
        super.render();

        long realiveFrame = Gdx.graphics.getFrameId() - WishesHelper.SCENE2_START_FRAME;
        batch2.begin();
        if (realiveFrame > 400 && realiveFrame < 1675)
            this.snowman.draw(batch2);

        if (this.snowing) {
            if (450 < realiveFrame && realiveFrame <= 3400)
                this.mountain.draw(batch2);
            else if (3400 < realiveFrame && realiveFrame <= 4900) {
                this.socks.draw(batch2);
                this.backgroundScreenPng = "winter/lgrey-ready.png";
                this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
                Globals.BKG_COLOR = C64Colors.GRAY;
            } else {
                this.backgroundScreenPng = "winter/lblue-ready.png";
                this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
                Globals.BKG_COLOR = C64Colors.LIGHT_BLUE;
            }
            if (realiveFrame > 500)
                this.forest.draw(batch2);

            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (realiveFrame + WishesHelper.SCENE2_START_FRAME > 400 + startIndex * 100) {
                    for (int i = startIndex; i < snowflakes.size(); i += 7) {
                        snowflakes.get(i).draw(batch2);
                    }
                }
            }
        }

        if (realiveFrame > WishesHelper.SCENE2_START_FRAME + 50) this.mikolaj1.draw(batch2);
        this.scroll1.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        batch2.end();

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(C64Colors.WHITE.toBadlogicColor());
        this.shapeRenderer.rect(0, 100, 80, 400);
        this.shapeRenderer.rect(800 - 80, 100, 80, 400);
        if (realiveFrame > WishesHelper.SCENE2_START_FRAME + 2340) {
            this.shapeRenderer.rect(79, 510, 400, 12);
        }
        this.shapeRenderer.end();

    }
}
