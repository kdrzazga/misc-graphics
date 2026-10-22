package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.C64Helper;
import org.kd.common.VideoClip;

import java.util.List;

public final class Year1992 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final Rectangle openGlArea = new Rectangle(292, 173, 420, 420);
    private final Rectangle amiga600Area = new Rectangle(114, 393, 378, 252);
    private final Rectangle amiga1200Area = new Rectangle(114, 122, 378, 252);
    private final Rectangle amiga4000Area = new Rectangle(502, 122, 389, 523);
    private final String picturesPath = "anniversaries27/pics/1992/";

    // Stage start frames, relative to the start of the year. Each matches a pause in 1992.mp3.
    private final long pc486Frame = seconds(5);
    private final long aviFrame = seconds(11);
    private final long decFrame = seconds(21);
    private final long suseFrame = seconds(35);
    private final long suseLogoFrame = seconds(38.5);
    private final long openGlFrame = seconds(42);
    private final long amiga600Frame = seconds(60);
    private final long amiga1200Frame = seconds(64);
    private final long amiga4000Frame = seconds(68);
    private final long mortalKombatFrame = seconds(72);

    private final long fadeInFrames = 15;
    private final long amigaFadeInFrames = 30;

    // What S.u.S.E. stood for, typed over the darkened headquarters in the last 2 seconds before the logo
    private final long suseNameFrame = suseLogoFrame - seconds(2);
    private final long suseNameTypingFrames = seconds(1);
    private final int suseNameFontSize = 96;
    private final float suseNameLineSpacing = 118;
    private final float suseNameMargin = 40;
    private final float suseNameDimming = 0.6f;

    private final VideoClip graceHopper, mortalKombat;
    private final FittedPicture pc486, suseLogo, openGl, amiga600, amiga1200, amiga4000;
    private final AcronymAnimation avi;
    private final TypedCode suseName;
    private final Sprite suse, dec;

    Year1992(long startingFrame) {
        super("anniversaries27/1992.mp3", startingFrame);
        this.endFrame = startingFrame + 4888; // 1992.mp3 lasts 81.456 s at 60 fps

        // 360x434 video fitted to the canvas height: 450x543, centred horizontally
        graceHopper = new VideoClip("anniversaries27/video/grace.mp4", 30, 277, 112, 450, 543, false);
        // 320x240 video fitted to the canvas height: 724x543, centred horizontally
        mortalKombat = new VideoClip("anniversaries27/video/mk.mp4", 30, 140, 112, 724, 543, false);
        pc486 = new FittedPicture(picturesPath + "486.jpg", canvas, Color.BLACK);
        avi = new AcronymAnimation(List.of("Audio", "Video", "Interleave"), canvas);

        var decTexture = new Texture(picturesPath + "DEC.jpg");
        dec = new Sprite(decTexture);
        dec.setPosition(204, 201);

        var suseTexture = new Texture(picturesPath + "suse.png");
        suse = new Sprite(suseTexture);
        suse.setPosition(204, 201);
        suse.setScale(1.3f, 1.3f);
        suseLogo = new FittedPicture(picturesPath + "suse-logo.png", canvas, Color.BLACK, true);
        suseName = new TypedCode(List.of("Software-", "und", "System-", "Entwicklung"),
                C64Helper.createFont(suseNameFontSize, "Helvetica Regular.otf"), suseNameLineSpacing, Color.WHITE,
                suseNameTypingFrames);

        openGl = new FittedPicture(picturesPath + "opengl.png", openGlArea, Color.WHITE);
        amiga600 = new FittedPicture(picturesPath + "a600.jpg", amiga600Area, Color.BLACK);
        amiga1200 = new FittedPicture(picturesPath + "a1200.jpg", amiga1200Area, Color.BLACK);
        amiga4000 = new FittedPicture(picturesPath + "a4000.jpg", amiga4000Area, Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        long relFrame = frame - this.startingFrame;
        sayItOnce();

        if (relFrame < pc486Frame)
            drawVideo(batch, graceHopper);
        else if (relFrame < aviFrame)
            drawWhole(batch, pc486, relFrame, pc486Frame);
        else if (relFrame < decFrame)
            avi.draw(batch, relFrame - aviFrame);
        else if (relFrame < suseFrame)
            drawSprite(batch, dec);
        else if (relFrame < suseLogoFrame) {
            drawSprite(batch, suse);
            if (relFrame >= suseNameFrame)
                drawSuseName(batch, relFrame);
        } else if (relFrame < openGlFrame)
            drawWhole(batch, suseLogo, relFrame, suseLogoFrame);
        else if (relFrame < amiga600Frame)
            drawOpenGl(batch, relFrame);
        else if (relFrame < mortalKombatFrame)
            drawAmigas(batch, relFrame);
        else
            drawVideo(batch, mortalKombat);

        log(relFrame);

        writeYear(batch);
    }

    private void drawVideo(SpriteBatch batch, VideoClip video) {
        batch.begin();
        video.draw(batch);
        batch.end();
    }

    private void drawSprite(SpriteBatch batch, Sprite sprite) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    /** The OpenGL logo is dark blue, so it gets a white canvas. */
    private void drawOpenGl(SpriteBatch batch, long relFrame) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(canvas.x, canvas.y, canvas.width, canvas.height);
        shapeRenderer.end();

        batch.begin();
        openGl.drawWhole(batch, fadeIn(relFrame, openGlFrame, fadeInFrames));
        batch.end();
    }

    /** Amiga 600 first, then the Amiga 1200 joins below it, finally the Amiga 4000 on the right. */
    private void drawAmigas(SpriteBatch batch, long relFrame) {
        batch.begin();
        amiga600.drawWhole(batch, fadeIn(relFrame, amiga600Frame, amigaFadeInFrames));
        if (relFrame >= amiga1200Frame)
            amiga1200.drawWhole(batch, fadeIn(relFrame, amiga1200Frame, amigaFadeInFrames));
        if (relFrame >= amiga4000Frame)
            amiga4000.drawWhole(batch, fadeIn(relFrame, amiga4000Frame, amigaFadeInFrames));
        batch.end();
    }

    /** Software- / und / System- / Entwicklung, left aligned and centred vertically on the dimmed headquarters. */
    private void drawSuseName(SpriteBatch batch, long relFrame) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, suseNameDimming * fadeIn(relFrame, suseNameFrame, fadeInFrames));
        shapeRenderer.rect(canvas.x, canvas.y, canvas.width, canvas.height);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        float top = canvas.y + canvas.height / 2 + (3 * suseNameLineSpacing + suseNameFontSize) / 2;
        suseName.draw(batch, canvas.x + suseNameMargin, top, relFrame - suseNameFrame);
    }

    private void drawWhole(SpriteBatch batch, FittedPicture picture, long relFrame, long appearFrame) {
        batch.begin();
        picture.drawWhole(batch, fadeIn(relFrame, appearFrame, fadeInFrames));
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame, long durationFrames) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) durationFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("Grace Hopper ");
        } else if (relFrame == pc486Frame) {
            System.out.print("PC486DX2 ");
        } else if (relFrame == aviFrame) {
            System.out.print("AVI ");
        } else if (relFrame == decFrame) {
            System.out.print("DEC ");
        } else if (relFrame == suseFrame) {
            System.out.print("SuSe ");
        } else if (relFrame == openGlFrame) {
            System.out.print("OpenGL ");
        } else if (relFrame == amiga600Frame) {
            System.out.print("Amigas ");
        } else if (relFrame == mortalKombatFrame) {
            System.out.println("MortalKombat");
        }
    }
}
