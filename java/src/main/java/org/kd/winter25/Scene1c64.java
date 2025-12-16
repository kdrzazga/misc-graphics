package org.kd.winter25;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.BasicC64Screen;
import org.kd.common.C64Colors;
import org.kd.common.C64Helper;
import org.kd.common.Globals;
import org.kd.common.winter.WinterEffects;
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Scene1c64 extends BasicC64Screen {
    public SpriteBatch batch2;
    BitmapFont fontSmall;
    private List<Sprite> backgroundSprites;
    private List<Sprite> backgroundSprites2;
    private List<Sprite> snowflakes;
    private List<Point> snowPatches;
    private List<Sprite> letters;
    private float snowPatchThreshold = 90f;
    private Sprite backgroundSprite3;
    private Sprite christmasTree;
    private Sprite christmasCaption;
    private Sprite santa;
    private Sprite reset;
    private ShapeRenderer shapeRenderer;
    private boolean snowing;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();

        this.snowing = true;
        this.batch2 = new SpriteBatch(2);
        fontSmall = C64Helper.createFont(12, "C64_Pro_Mono-STYLE.ttf");
        fontSmall.setColor(Color.BLACK);
        backgroundSprites = new ArrayList<>(2);
        backgroundSprites2 = new ArrayList<>(2);
        snowPatches = new ArrayList<>();
        snowflakes = new ArrayList<>();
        letters = new ArrayList<>(5);
        AtomicReference<Float> shift = new AtomicReference<>((float) 0);

        Arrays.asList("lda1.png", "sta$d020.png").forEach(picPath -> initSprite(picPath, shift, backgroundSprites));
        Arrays.asList("poke646_1.png", "clrscr.png").forEach(picPath -> {
            initSprite(picPath, shift, backgroundSprites2);
        });

        AtomicReference<Float> shift2 = new AtomicReference<>((float) 0);
        Arrays.asList("m.png", "e.png", "r.png", "r.png", "y.png").forEach(picPath -> {
            initSprite(picPath, new AtomicReference<>((float) 0), letters);
            var letter = letters.get(letters.size() - 1);
            letter.setX(100 * (shift2.get() + 1));
            letter.setY(Globals.SCREEN_HEIGHT - 220);
            letter.setScale(0.2f, 0.2f);
            shift2.set(shift2.get() + 1);
        });

        backgroundSprite3 = new Sprite(new Texture("winter/lightblu_bkgnd.png"));
        backgroundSprite3.setX(0);
        backgroundSprite3.setY(250);

        this.snowflakes = WinterEffects.createSnowflakeSprites();

        christmasTree = new Sprite(new Texture("winter/choinka.png"));
        christmasTree.setScale(1, 0f);
        christmasTree.setPosition(2 * 243, 120);

        christmasCaption = new Sprite(new Texture("winter/Christmas.png"));
        christmasCaption.setScale(0.25f, 1f);
        christmasCaption.setPosition(0, Globals.SCREEN_HEIGHT - 239);

        reset = new Sprite(new Texture("winter/reset.png"));
        reset.setScale(0.1f, 0.1f);
        reset.setPosition(Globals.SCREEN_WIDTH / 2f, Globals.SCREEN_HEIGHT / 2f);

        santa = new Sprite(new Texture("winter/santa.png"));
        santa.setScale(1f, 1f);
        santa.setPosition(150, 30);

        shapeRenderer = new ShapeRenderer();
    }


    private void initSprite(String picPath, AtomicReference<Float> shift, List<Sprite> spriteList) {
        var texture = new Texture("winter/" + picPath);
        var sprite = new Sprite(texture);
        sprite.setX(2 * 100 + shift.get());
        sprite.setY(2 * 160 + shift.get());
        shift.set(shift.get() - 30);
        spriteList.add(sprite);
    }

    @Override
    public void update(float delta) {
        long frame = Gdx.graphics.getFrameId();

        if (frame == 400) {
            this.borderColor = C64Colors.WHITE;
        } else if (frame == 600) {
            Globals.CURSOR_COLOR = C64Colors.WHITE;
        } else if (frame == 699) {
            this.backgroundScreenPng = "winter/white-ready.png";
            this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
            Globals.cursorY = Math.round(0.753 * Globals.SCREEN_HEIGHT) - 2;
        } else if (frame == 1100) {
            this.backgroundScreenPng = "winter/lblue-ready.png";
            this.backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
            Globals.BKG_COLOR = C64Colors.LIGHT_BLUE;
        }

        if (this.snowing)
            for (int startIndex = 0; startIndex <= 6; startIndex++) {
                if (frame > 1100 + startIndex * 100)
                    WinterEffects.snow(startIndex, this.snowflakes);
            }
        // System.out.print(frame + " ");
        if (frame > 1499 && frame % 3 == 0) {
            createSnowPatch();
        }

        if (frame > 2730 && frame % 2 == 0) {
            long index = frame % 5;
            var letter = letters.get((int) index);
            float scale = letter.getScaleX();
            if (scale < 1.1f) {
                scale += 0.05f;
            }
            letter.setScale(scale, scale);
        }

        if (frame > 3255) {
            Sprite cc = christmasCaption;
            if (cc.getScaleX() < 1) cc.setScale(cc.getScaleX() + 0.1f);
            if (cc.getX() < 200) cc.setX(cc.getX() + 1);
            //System.out.println("\n\n\nElapsed time " + C64Helper.countElapsedTime());
        }

        if (frame > 3400 && frame % 2 == 0) {
            if (letters.get(0).getY() > 360) {
                letters.get(0).setY(letters.get(0).getY() - 1);
                letters.get(4).setY(letters.get(4).getY() - 1);
            }
        }

        if (frame > 3530 && frame % 4 == 0 && christmasTree.getScaleY() < 1 - 0.05f) {
            christmasTree.setScale(1, christmasTree.getScaleY() + 0.05f);
        }

        if (frame > 8600 && frame < 8800) {
            reset.setScale(reset.getScaleX() + 0.02f);
        }
    }

    private void createSnowPatch() {
        if (this.snowPatchThreshold < 138f) this.snowPatchThreshold += 0.15f;
        int min = 50;
        int max = 800 - 50;
        int x = (int) (new Random().nextDouble() * (max - min) + min);
        int y = Math.round(this.snowPatchThreshold);
        var p = new Point(x, y);
        this.snowPatches.add(p);
        if (x < min + max / 2) {
            this.snowPatches.add(new Point(x + 2, y + 1));
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        if (frame > 300 && frame < 700) {
            var spriteGroup = backgroundSprites;

            if (frame > 550) spriteGroup = backgroundSprites2;

            flySprites(spriteGroup);
        }
        if (frame > 900) {
            float x = backgroundSprite3.getX();
            backgroundSprite3.setX(x + 1.5f);
            double y = backgroundSprite3.getY() + 3.5 * Math.sin(x / (4 * Math.PI));
            backgroundSprite3.setY((float) y);
            backgroundSprite3.draw(batch2);
        }

        for (int startIndex = 0; startIndex <= 6; startIndex++) {
            if (frame > 1100 + startIndex * 100) {
                for (int i = startIndex; i < snowflakes.size(); i += 7) {
                    snowflakes.get(i).draw(batch2);
                }
            }
        }

        if (frame > 1234) {
            this.snowPatches.forEach(point -> {
                whiteFont.draw(batch2, "#", point.getX(), point.getY());
            });

            christmasTree.draw(batch2);
        }

        if (frame > 2730) {
            letters.forEach(letter -> letter.draw(batch2));
        }

        if (frame > 3255) {
            christmasCaption.draw(batch2);
        }

        if (frame > 3800 && frame < 8500) {
            santa.draw(batch2);
        }

        drawWishes(batch2, fontSmall, frame, 4000, 4800, Arrays.asList("KD and K&A+", " team send", "best wishes", "2 all retro", "maniacs !!!", "   YOU", "  ROCK !!!"));
        drawWishes(batch2, fontSmall, frame, 4850, 5650, Arrays.asList(" Let this", " festive", "  season", " bring you", "lotsa joy !", " Joy and", "joysticks !"));
        drawWishes(batch2, fontSmall, frame, 5700, 6600, Arrays.asList("  Spend", " Christmas", " with your", "family, but", "also don't", "forget abt", "your retro", "hardware."));
        drawWishes(batch2, fontSmall, frame, 6650, 7450, Arrays.asList("Once supper", " is over,", "take your", " siblings, ", " turn on", "  INTER.", "KARATE and"));
        drawWishes(batch2, fontSmall, frame, 7500, 8300, Arrays.asList("   KICK", "  THEIR", " BUTTS !!!", "  HA HA !", "  HO! HO!", "  Merry", "Christmas !"));

        if (frame > 8600 && frame < 9150) {
            reset.draw(batch2);
        }
        if (frame > 8700) {
            executeReset();
        }

        batch2.end();
    }

    private void executeReset() {
        snowing = false;
        Globals.CURSOR_COLOR = C64Colors.LIGHT_BLUE;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        var width = 90;
        shapeRenderer.rect(0, 0, width, 600);
        shapeRenderer.rect(800 - width, 0, width, 600);
        shapeRenderer.end();
    }

    private void drawWishes(SpriteBatch batch, BitmapFont font, long currentFrame, int startFrame, int endFrame, List<String> wishes) {
        if (currentFrame > startFrame && currentFrame < endFrame) {
            for (int i = 0; i < wishes.size(); i++) {
                font.draw(batch, wishes.get(i), 338, 235 - 23 * i);
            }
        }
    }

    private void flySprites(List<Sprite> spriteGroup) {
        for (int i = 0; i < spriteGroup.size(); i++) {
            Sprite s = spriteGroup.get(i);
            flyCaptionSprite(s, i);
        }
    }

    private void flyCaptionSprite(Sprite s, int i) {
        s.draw(batch2);
        s.setX(s.getX() + (i + 1));
        s.setY(s.getY() - (i - 1));
        s.setScale(s.getScaleX() + 0.01f, s.getScaleY() + 0.01f);
        s.setAlpha(1f);
    }

    @Override
    public void dispose() {
        super.dispose();
        batch2.dispose();
        for (Sprite sprite : backgroundSprites) {
            sprite.getTexture().dispose();
        }
    }
}
