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
    private int bottomLimit = 170;
    private Year2021 year2021;
    private Year2016 year2016;
    private Year2011 year2011;
    private Year2006 year2006;
    private Year2001 year2001;
    private Year1996 year1996;
    private Year1991 year1991;
    private Year1986 year1986;
    private Year1981 year1981;
    private Year1976 year1976;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();
        this.year2021 = new Year2021();
        this.year2016 = new Year2016();
        this.year2011 = new Year2011();
        this.year2006 = new Year2006();
        this.year2001 = new Year2001();
        this.year1996 = new Year1996();
        this.year1991 = new Year1991();
        this.year1986 = new Year1986();
        this.year1981 = new Year1981();
        this.year1976 = new Year1976();
        this.createMusic();

        this.batch2 = new SpriteBatch(2);
        normalFont = C64Helper.createFont(16, "C64_Pro_Mono-STYLE.ttf");
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
                if (this.logoSprite.getX() < -355 || this.logoSprite.getX() > 260)
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

        if (frame == 3570) {
            this.borderColor = C64Colors.BLACK;
        }
        if (frame > 3570 && frame < 3570+517) {
            this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            this.shapeRenderer.setColor(this.borderColor.toBadlogicColor());
            this.shapeRenderer.rect(0, 0, 800, this.bottomLimit);
            this.shapeRenderer.end();
        }

        batch2.begin();

        if (frame > 6000) {
            letters.forEach(letter -> letter.draw(batch2));
        }

        textWall(StaticData.messages1, frame, 800, 1811);
        textWall(StaticData.messages2, frame, 1850, 2922);
        textWall(StaticData.messages3, frame, 3000, 3600);

        if (frame > 3670) {
            this.logoSprite.colorize(C64Colors.WHITE);
            if (frame % 5 == 0 && this.logoSprite.getSprite().getY() > 10) {
                var sprite = this.logoSprite.getSprite();
                sprite.setY(sprite.getY() - 1);
            }

            boolean extraCondition = frame > 3702 || frame % 2 == 1;
            if (this.bottomLimit < 550 && extraCondition) {
                this.bottomLimit++;
            }
        }

        if (frame > 3790) {
            conditionallyColorizeLogo(frame);
        }

        if (frame > 4109)
            if (frame == 4110) {
                this.blinkingCursor = false;
            } else if (frame > 4117 && frame < 4900) {
                this.borderColor = C64Colors.DARK_GRAY;
                this.year2021.sayItOnce();
                this.year2021.draw(frame, this);
            } else if (frame <= 4900) {
                this.year2016.sayItOnce();
            } else if (frame < 5200) {
                this.year2011.sayItOnce();
            } else if (frame < 5450) {
                this.year2006.sayItOnce();
                this.year2006.draw(frame, this);
            } else if (frame < 5700) {
                this.year2001.sayItOnce();
                this.year2001.draw(frame, this);
            } else if (frame < 5750) {
                this.year1996.sayItOnce();
            } else if (frame < 6200) {
                this.year1991.sayItOnce();
            } else if (frame < 7250) {
                this.year1986.sayItOnce();
            } else if (frame < 7500) {
                this.year1981.sayItOnce();
                this.year1981.draw(frame, this);
            } else if (frame < 8000) {
                this.year1976.sayItOnce();
                this.year1976.draw(frame, this);
            }

        if (frame > 10) {
            this.logoSprite.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);

            this.gravitationRamos.play();
        }
        batch2.end();
    }

    private void conditionallyColorizeLogo(long frame) {
        if (frame % 1200 < 150)
            this.logoSprite.colorize(C64Colors.YELLOW);
        else if (frame % 1200 < 300)
            this.logoSprite.colorize(C64Colors.RED);
        else if (frame % 1200 < 450)
            this.logoSprite.colorize(C64Colors.LIGHT_GRAY);
        else if (frame % 1200 < 600)
            this.logoSprite.colorize(C64Colors.LIGHT_BLUE);
        else if (frame % 1200 < 750)
            this.logoSprite.colorize(C64Colors.PURPLE);
        else if (frame % 1200 < 900)
            this.logoSprite.colorize(C64Colors.CYAN);
        else if (frame % 1200 < 1050)
            this.logoSprite.colorize(C64Colors.PINK);
        else
            this.logoSprite.colorize(C64Colors.WHITE);
    }

    private void textWall(List<String> messages, long frame, int veryInitialFrame, int endFrame) {
        for (int i = 0; i < messages.size(); i++) {
            int fontSize = 22 * (i + 1);
            drawMessage(batch2, normalFont, frame, veryInitialFrame + 150 * i, endFrame, messages.get(i), fontSize);
        }
    }

    private void drawMessage(SpriteBatch batch, BitmapFont font, long currentFrame, int startFrame, int endFrame, String msg, int shiftY) {
        if (currentFrame > startFrame && currentFrame < endFrame) {
            font.draw(batch, msg, 81, 406 - shiftY);

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
