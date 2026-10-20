package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.VideoClip;

public final class Year1987 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);

    // Stage start frames, relative to the start of the year
    private final long morrisChangFrame = seconds(10);   // the TSMC logo is complete at 8 s and stays 2 s
    private final long morrisChangEndFrame = seconds(15);

    private final long crossFadeFrames = seconds(1);

    // 張忠謀 (Morris Chang) flies out of the centre of his photo
    private final long chineseNameFrame = seconds(10.5);
    private final long chineseNameEndFrame = seconds(14.5);
    private final float chineseNameStartWidth = 30;
    private final float chineseNameEndWidth = 3000;

    // Perl source typed letter by letter, then Dizzy for the last 4 s of the year
    private final long yearFrames = 2320;
    private final long perlFrame = seconds(24);
    private final long perlTypingFrames = seconds(8.5);
    private final long dizzyFrame = yearFrames - seconds(4);
    private final float codeMargin = 20;
    private final Color terminalGreen = new Color(0.35f, 1f, 0.35f, 1);

    private final TsmcLogoAnimation tsmc;
    private final FittedPicture morrisChang;
    private final Texture chineseName;
    private final TypedCode perlCode;
    private final VideoClip dizzy;

    Year1987(long startingFrame) {
        super("anniversaries27/1987.mp3", startingFrame);
        this.endFrame = startingFrame + yearFrames; // 1987.mp3 lasts 38.664 s at 60 fps

        tsmc = new TsmcLogoAnimation(canvas);
        morrisChang = new FittedPicture("anniversaries27/pics/1987/chang.jpg", canvas, Color.BLACK, true);
        // white image rendered once from Microsoft JhengHei Bold
        chineseName = new Texture("anniversaries27/pics/1987/glyphs/zhang-zhongmou.png");
        chineseName.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        // larry.pl is runnable and prints the sentence about Perl and Larry Wall
        perlCode = new TypedCode("anniversaries27/code/larry.pl", 15, 22, terminalGreen, perlTypingFrames);
        // 4 s, 640x360 video fitted to the canvas width: 797x448, centred vertically
        dizzy = new VideoClip("anniversaries27/video/Dizzy1.mp4", 30, 104, 160, 797, 448, false);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        if (relFrame < morrisChangFrame + crossFadeFrames)
            tsmc.draw(batch, relFrame); // 台積電 -> tsmc -> logo, complete at 8 s
        if (morrisChangFrame <= relFrame && relFrame < morrisChangEndFrame)
            drawMorrisChang(batch, relFrame);
        if (chineseNameFrame <= relFrame && relFrame < chineseNameEndFrame)
            drawChineseName(batch, relFrame);
        if (perlFrame <= relFrame && relFrame < dizzyFrame)
            perlCode.draw(batch, canvas.x + codeMargin, canvas.y + canvas.height - codeMargin, relFrame - perlFrame);
        if (relFrame >= dizzyFrame)
            drawDizzy(batch);

        log(relFrame);

        writeYear(batch);
    }

    /** TSMC founder, cross-fading in over the logo. */
    private void drawMorrisChang(SpriteBatch batch, long relFrame) {
        float alpha = Math.min(1f, (relFrame - morrisChangFrame) / (float) crossFadeFrames);
        batch.begin();
        morrisChang.drawWhole(batch, alpha);
        batch.end();
    }

    private void drawDizzy(SpriteBatch batch) {
        batch.begin();
        dizzy.draw(batch);
        batch.end();
    }

    /** Starts tiny in the centre, grows exponentially and fades out; clipped to the canvas. */
    private void drawChineseName(SpriteBatch batch, long relFrame) {
        float progress = (relFrame - chineseNameFrame) / (float) (chineseNameEndFrame - chineseNameFrame);
        float width = chineseNameStartWidth * (float) Math.pow(chineseNameEndWidth / chineseNameStartWidth, progress);
        float height = width * chineseName.getHeight() / chineseName.getWidth();
        float centreX = canvas.x + canvas.width / 2;
        float centreY = canvas.y + canvas.height / 2;

        Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
        HdpiUtils.glScissor((int) canvas.x, (int) canvas.y, (int) canvas.width, (int) canvas.height);
        batch.begin();
        batch.setColor(1, 1, 1, 1 - progress);
        batch.draw(chineseName, centreX - width / 2, centreY - height / 2, width, height);
        batch.setColor(Color.WHITE);
        batch.end();
        Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private static void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("TSMC ");
        } else if (relFrame == 890) {
            System.out.print("Amiga ");
        } else if (relFrame == 1450) {
            System.out.print("Perl ");
        } else if (relFrame == 2020) {
            System.out.println("Dizzy ");
        }
    }

}
