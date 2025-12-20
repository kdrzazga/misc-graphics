package org.kd.xmas25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

    private final float xmasTreeScale = 0.8f;

    private Mikolaj mikolaj1;
    private PetsciiSnowman snowman;
    private TravellingLogo scroll1;
    private TravellingLogo xmasTree;
    private Sprite forest;
    private Sprite mountain;
    private Sprite socks;
    private List<Sprite> snowflakes;
    private boolean snowing;

    public Scene2(String id) {
        super(id);
        this.backgroundScreenPng = "lblue-ready.png";
        this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
    }

    @Override
    public void create() {
        super.create();
        this.batch2 = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        var mikolajPng = new Texture("dream210/mikolaj.png");
        this.mikolaj1 = new Mikolaj(mikolajPng);
        this.mikolaj1.setColor(Color.BLACK);

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

        var choinka = new Texture("dream210/tree/tree.png");
        this.xmasTree = new TravellingLogo(choinka, Globals.SCREEN_WIDTH, 100, 417, 438);
        this.xmasTree.getSprite().setX(-100);
        this.xmasTree.getSprite().setScale(xmasTreeScale);
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
            if (frame > WishesHelper.SCENE2_START_FRAME + 1675)
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

        var treeX = this.xmasTree.getSprite().getX();
        if (WishesHelper.SCENE2_START_FRAME + WishesHelper.NIGHT_START_RELATIVE_FRAME < frame) {
            var treeBlink1 = new Texture("dream210/tree/tree4.png");
            var treeBlink2 = new Texture("dream210/tree/tree2.png");
            var x = this.xmasTree.getX();
            var y = this.xmasTree.getSprite().getY();
            if (treeX < 300)
                this.xmasTree.getSprite().setX(treeX + 2);
            else if (frame % 30 == 15) {
                this.xmasTree.changeTexture(treeBlink1);
                this.xmasTree.getSprite().setPosition(x, y);
                this.xmasTree.getSprite().setScale(xmasTreeScale);
            } else if (frame % 30 == 0) {
                this.xmasTree.changeTexture(treeBlink2);
                this.xmasTree.getSprite().setPosition(x, y);
                this.xmasTree.getSprite().setScale(xmasTreeScale);
            }
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
        else if (frame == WishesHelper.SCENE2_START_FRAME + WishesHelper.NIGHT_START_RELATIVE_FRAME)
            this.scroll1.changeTexture(new Texture("dream210/scroll/stanza4.bmp"));
        else if (frame == 9350)
            this.scroll1.spriteSpeed = 0;
    }

    @Override
    public void render() {
        super.render();

        long relativeFrame = Gdx.graphics.getFrameId() - WishesHelper.SCENE2_START_FRAME;
        batch2.begin();
        if (relativeFrame > 400 && relativeFrame < 1675)
            this.snowman.draw(batch2);

        if (this.snowing) {
            if (450 < relativeFrame && relativeFrame <= 3400)
                this.mountain.draw(batch2);
            else if (3400 < relativeFrame && relativeFrame <= WishesHelper.NIGHT_START_RELATIVE_FRAME) {
                this.socks.draw(batch2);
                this.backgroundScreenPng = "lgrey-ready.png";
                this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
                Globals.BKG_COLOR = C64Colors.GRAY;
            } else {
                if (relativeFrame > WishesHelper.NIGHT_START_RELATIVE_FRAME) {
                    this.mikolaj1.setColor(C64Colors.WHITE.toBadlogicColor());
                    this.backgroundScreenPng = "lblack-ready.png";
                    this.borderColor = C64Colors.BLACK;
                    this.mikolaj1.setColor(Color.CYAN);
                } else
                    this.backgroundScreenPng = "lblue-ready.png";
                this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
            }

            if (relativeFrame > 6550) {
                var msg = relativeFrame < 6870 ? "CODE  & GFX: KD" : "MSX: KD & https://csdb.dk/sid/?id=26004";
                whiteFont.draw(batch2, msg, 81, Globals.SCREEN_HEIGHT - 100);
            }

            if (WishesHelper.NIGHT_START_RELATIVE_FRAME < relativeFrame && relativeFrame < 6550) {
                this.xmasTree.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
            }

            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (relativeFrame + WishesHelper.SCENE2_START_FRAME > 400 + startIndex * 100) {
                    for (int i = startIndex; i < snowflakes.size(); i += 7) {
                        snowflakes.get(i).draw(batch2);
                    }
                }
            }
        }

        if (relativeFrame > 1675) this.mikolaj1.draw(batch2);
        this.scroll1.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        batch2.end();

        var hidersColor = relativeFrame > WishesHelper.NIGHT_START_RELATIVE_FRAME ? C64Colors.BLACK : C64Colors.WHITE;
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(hidersColor.toBadlogicColor());
        this.shapeRenderer.rect(0, 100, 80, 400);
        this.shapeRenderer.rect(800 - 80, 100, 80, 400);
        if (relativeFrame > WishesHelper.SCENE2_START_FRAME + 2340) {
            this.shapeRenderer.rect(79, 510, 400, 12);
        }
        this.shapeRenderer.end();

    }
}
