package org.kd.anniversaries;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.*;
import org.kd.common.tricks.TravellingLogo;

import java.util.Arrays;
import java.util.List;

public class Scene1c64 extends BasicC64Screen {

    private final long startAnniversariesDisplay = 4099;

    SpriteBatch batch2;
    BitmapFont normalFont;
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
    private Year1971 year1971;
    private long outroBeginFrame;
    private Music thanksForWatching;

    public Scene1c64(String id) {
        super(id);
    }

    @Override
    public void create() {
        super.create();

        this.year2021 = new Year2021(startAnniversariesDisplay);
        this.year2016 = new Year2016(this.year2021.getEndFrame());
        this.year2011 = new Year2011(this.year2016.getEndFrame());
        this.year2006 = new Year2006(this.year2011.getEndFrame());
        this.year2001 = new Year2001(this.year2006.getEndFrame());
        this.year1996 = new Year1996(this.year2001.getEndFrame());
        this.year1991 = new Year1991(this.year1996.getEndFrame());
        this.year1986 = new Year1986(this.year1991.getEndFrame());
        this.year1981 = new Year1981(this.year1986.getEndFrame());
        this.year1976 = new Year1976(this.year1981.getEndFrame());
        this.year1971 = new Year1971(this.year1976.getEndFrame());
        outroBeginFrame = this.year1971.getEndFrame() + 300;
        this.createMusic();

        this.batch2 = new SpriteBatch(2);
        normalFont = C64Helper.createFont(16, "C64_Pro_Mono-STYLE.ttf");
        normalFont.setColor(C64Colors.LIGHT_BLUE.toBadlogicColor());
        var logoTxtr = new Texture(Gdx.files.internal("anniversaries/logo.png"));
        this.logoSprite = new TravellingLogo(logoTxtr, Globals.SCREEN_WIDTH, 110, 1000, 50);
        this.logoSprite.colorize(C64Colors.LIGHT_BLUE);
        this.logoSprite.spriteSpeed = 300f;

        shapeRenderer = new ShapeRenderer();
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
        if (frame > 3255) {
            System.out.println("\n".repeat(50) + " Elapsed time " + Helper.countElapsedTime() + " Frame: " + frame);
        }
    }

    @Override
    public void render() {
        super.render();

        long frame = Gdx.graphics.getFrameId();

        if (frame == 3570) {
            this.borderColor = C64Colors.BLACK;
        }
        if (frame > 3570 && frame < 3570 + 527) {
            hideBottomPart();
        }

        batch2.begin();
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

        if (frame > startAnniversariesDisplay && frame <= this.year1971.getEndFrame() + Year.DEFAULT_DURATION / 2)
            displayAnniversary(frame);

        if (frame > 10) {
            this.logoSprite.draw(batch2, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);

            this.gravitationRamos.play();
        }

        if (frame > this.year1971.getEndFrame()) {
            var ls = this.logoSprite.getSprite();
            if (ls.getY() < 500)
                ls.setY(ls.getY() + 2);
        }

        if (frame > outroBeginFrame) {
            this.outro();
        }

        batch2.end();
    }

    private void outro() {
        this.backgroundScreenPng = "c64.png";
        backgroundTexture = new Texture(Gdx.files.internal(this.backgroundScreenPng));
        this.blinkingCursor = true;

        long frame = Gdx.graphics.getFrameId();

        if (frame > this.outroBeginFrame + 200) {
            this.borderColor = C64Colors.LIGHT_BLUE;
            this.logoSprite.colorize(C64Colors.BLUE);
        }

        textWall(StaticData.outroMessages1, frame, Math.round(this.outroBeginFrame + 300), Math.round(this.outroBeginFrame + 700));
        textWall(StaticData.outroMessages2, frame, Math.round(this.outroBeginFrame + 750), Math.round(this.outroBeginFrame + 1200));

        textWall(StaticData.outroMessages3, frame, Math.round(this.outroBeginFrame + 1200), Math.round(this.outroBeginFrame + 1650));

        if (frame == this.outroBeginFrame + 1200) {
            this.gravitationRamos.stop();
            thanksForWatching.play();
        } else if (frame > this.outroBeginFrame + 1650) {
            Gdx.app.exit();
            System.out.println("\n".repeat(50) + "End demo: " + Helper.countElapsedTime() + " frame = " + frame);
            this.dispose();
            System.exit(0);
        }
    }

    private void hideBottomPart() {
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.shapeRenderer.setColor(this.borderColor.toBadlogicColor());
        this.shapeRenderer.rect(0, 0, 800, this.bottomLimit);
        this.shapeRenderer.end();
    }

    private void displayAnniversary(long frame) {
        this.blinkingCursor = false;

        var allYears = Arrays.asList(this.year2021, this.year2016, this.year2011
                , this.year2006, this.year2001, this.year1996, this.year1991, this.year1986
                , this.year1981, this.year1976, this.year1971);

        for (int i = 0; i < allYears.size() - 1; i++) {
            if (frame > allYears.get(i).startingFrame && frame < allYears.get(i + 1).startingFrame) {
                this.borderColor = C64Colors.DARK_GRAY;
                allYears.get(i).sayItOnce();
                allYears.get(i).draw(frame, this);
            }
        }
        if (frame > this.year1971.startingFrame && frame <= this.year1971.getEndFrame()) {
            this.year1971.sayItOnce();
            this.year1971.draw(frame, this);
        }
    }

    private void conditionallyColorizeLogo(long frame) {
        if (frame > this.outroBeginFrame + 200)
            return;

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

        thanksForWatching = Gdx.audio.newMusic(Gdx.files.internal("anniversaries/Thanks for watching.mp3"));
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
