package org.kd.anniversaries;

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
import org.lwjgl.util.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class Scene1c64 extends BasicC64Screen {
    SpriteBatch batch2;
    BitmapFont fontSmall;
    private List<Sprite> letters;
    private ShapeRenderer shapeRenderer;
    private Sprite logoSprite;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();

        this.batch2 = new SpriteBatch(2);
        fontSmall = this.createFont(12);
        fontSmall.setColor(Color.BLACK);
        letters = new ArrayList<>(5);
        this.logoSprite = new Sprite(new Texture("anniversaries/logo.png"));
        this.logoSprite.setPosition(20f, 300);
        this.logoSprite.setScale(0.4f);

        AtomicReference<Float> shift2 = new AtomicReference<>((float) 0);
        Arrays.asList("m.png", "e.png", "r.png", "r.png", "y.png").forEach(picPath -> {
            initSprite(picPath, new AtomicReference<>((float) 0), letters);
            var letter = letters.get(letters.size() - 1);
            letter.setX(100 * (shift2.get() + 1));
            letter.setY(Globals.SCREEN_HEIGHT - 220);
            letter.setScale(0.2f, 0.2f);
            shift2.set(shift2.get() + 1);
        });

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


        System.out.print(frame + " ");
        if (frame > 1499 && frame % 3 == 0) {
        }

        if (frame > 2730 && frame % 2 == 0) {
        }

        if (frame > 3255) {
            System.out.println("\n\n\nElapsed time " + C64Helper.countElapsedTime());
        }

        if (frame > 3400 && frame % 2 == 0) {
            if (letters.get(0).getY() > 360) {
                letters.get(0).setY(letters.get(0).getY() - 1);
                letters.get(4).setY(letters.get(4).getY() - 1);
            }
        }
/*
        if (frame > 3530 && frame % 4 == 0 && christmasTree.getScaleY() < 1 - 0.05f) {
            christmasTree.setScale(1, christmasTree.getScaleY() + 0.05f);
        }
*/
        if (frame > 8600 && frame < 8800) {
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();
        batch2.begin();
        if (frame > 300 && frame < 700) {
            this.logoSprite.draw(batch2);

            if (frame > 550) ;

            //flySprites(spriteGroup);
        }
        if (frame > 900) {
        }



        if (frame > 2730) {
            letters.forEach(letter -> letter.draw(batch2));
        }


        drawWishes(batch2, fontSmall, frame, 4000, 4800, Arrays.asList("KD and K&A+", " team send", "best wishes", "2 all retro", "maniacs !!!", "   YOU", "  ROCK !!!"));
        drawWishes(batch2, fontSmall, frame, 4850, 5650, Arrays.asList(" Let this", " festive", "  season", " bring you", "lotsa joy !", " Joy and", "joysticks !"));
        drawWishes(batch2, fontSmall, frame, 5700, 6600, Arrays.asList("  Spend", " Christmas", " with your", "family, but", "also don't", "forget abt", "your retro", "hardware."));
        drawWishes(batch2, fontSmall, frame, 6650, 7450, Arrays.asList("Once supper", " is over,", "take your", " siblings, ", " turn on", "  INTER.", "KARATE and"));
        drawWishes(batch2, fontSmall, frame, 7500, 8300, Arrays.asList("   KICK", "  THEIR", " BUTTS !!!", "  HA HA !", "  HO! HO!", "  Merry", "Christmas !"));



        batch2.end();
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
    }
}
