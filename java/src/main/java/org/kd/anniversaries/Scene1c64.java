package org.kd.anniversaries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Scene1c64 extends BasicC64Screen {
    SpriteBatch batch2;
    BitmapFont normalFont;
    private List<Sprite> letters;
    private ShapeRenderer shapeRenderer;
    private TravellingLogo logoSprite;
    private Music gravitationRamos;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        this.createMusic();

        this.batch2 = new SpriteBatch(2);
        normalFont = this.createFont(16);
        normalFont.setColor(C64Colors.LIGHT_BLUE.toBadlogicColor());
        letters = new ArrayList<>(5);
        var logoTxtr = new Texture(Gdx.files.internal("anniversaries/logo.png"));
        this.logoSprite = new TravellingLogo(logoTxtr, Globals.SCREEN_WIDTH, 110, 1000, 50);
        this.logoSprite.colorize(C64Colors.LIGHT_BLUE);
        this.logoSprite.spriteSpeed = 300f;

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
        if (frame >= 330) {
            this.logoSprite.move(delta, Globals.SCREEN_WIDTH);
            if (frame >= 500)
                if (this.logoSprite.getX()< -355 || this.logoSprite.getX() > 260)
                    this.logoSprite.changeDirection();
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
        if (frame > 10) {
            this.logoSprite.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);

            this.gravitationRamos.play();
        }
        if (frame > 900) {
        }

        if (frame > 2730) {
            letters.forEach(letter -> letter.draw(batch2));
        }

        textWall(StaticData.messages1, frame, 800, 2400);
        textWall(StaticData.messages2, frame, 2500, 4100);

        batch2.end();
    }

    private void textWall(List<String> messages, long frame, int veryInitialFrame, int endFrame) {
        for (int i = 0; i < messages.size(); i++) {
            int fontSize = 22 * (i + 1);
            drawWishes(batch2, normalFont, frame,  veryInitialFrame + 200*i, endFrame, messages.get(i), fontSize);
        }
    }

    private void drawWishes(SpriteBatch batch, BitmapFont font, long currentFrame, int startFrame, int endFrame, String wishes, int shiftY) {
        if (currentFrame > startFrame && currentFrame < endFrame) {
                font.draw(batch, wishes, 80, 406 - shiftY);

        }
    }

    private void flySprites(List<Sprite> spriteGroup) {
        for (int i = 0; i < spriteGroup.size(); i++) {
            Sprite s = spriteGroup.get(i);
            flyCaptionSprite(s, i);
        }
    }

    private void createMusic() {
        gravitationRamos = Gdx.audio.newMusic(Gdx.files.internal("anniversaries/Gravitation.mp3"));
        gravitationRamos.setLooping(false);
        gravitationRamos.setVolume(2f);
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
        gravitationRamos.dispose();
    }
}
