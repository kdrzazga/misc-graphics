package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.FittedPicture;
import org.kd.common.VideoClip;

public final class Year2022 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final String picturesPath = "anniversaries27/pics/2022/";

    private final long yearFrames = 3062; // 2022.mp3 lasts 51.024 s at 60 fps
    private final long bitcoinFrame = 350;
    private final long lunaFrame = 1100;
    private final long threeArrowsFrame = seconds(32);
    private final long celsiusFrame = seconds(41);

    // 3AC: its logo for the first half, then Su Zhu with the Bloomberg article over him in the last 3 seconds
    private final long suZhuFrame = (threeArrowsFrame + celsiusFrame) / 2;
    private final long bloombergFrame = celsiusFrame - seconds(3);
    // Celsius until the end, with the Forbes article over it in the last 5 seconds
    private final long forbesFrame = yearFrames - seconds(5);
    private final float overlayOpacity = 0.5f;

    // The articles appear when the animation under them has looped once
    private final long bitcoinCrashLoopFrames = 485; // 194 video frames at 24 fps
    private final int lunaFrameCount = 50;
    private final float lunaFrameDuration = 0.21f;
    private final long lunaLoopFrames = Math.round(lunaFrameCount * lunaFrameDuration * 60);
    private final long bitcoinFirstArticleFrame = bitcoinFrame + bitcoinCrashLoopFrames / 2; // 2nd half of the 1st loop
    private final long bitcoinArticleFrame = bitcoinFrame + bitcoinCrashLoopFrames;
    private final long lunaArticleFrame = lunaFrame + lunaLoopFrames;
    private final float articleOpacity = 0.8f; // 20% transparent
    private final long articleFadeInFrames = 15;

    private final AnimatedSpriteV twiX, luna;
    private final VideoClip bitcoinCrash;
    private final FittedPicture bitcoinFirstArticle, bitcoinArticle, lunaArticle;
    private final FittedPicture threeArrows, suZhu, bloombergArticle, celsius, forbesArticle;

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = startingFrame + yearFrames;
        twiX = new AnimatedSpriteV("anniversaries27/pics/TwiX.png", 71, 0.0704f, 0, 0);
        // 432x304 video scaled to the canvas height (543), centred horizontally in the 797 px wide canvas.
        // The file plays forward then backward (baked in with ffmpeg reverse), so looping it gives a ping-pong.
        bitcoinCrash = new VideoClip("anniversaries27/video/BitcoinCrash.mp4", 24, 117, 112, 771, 543, true);
        luna = new AnimatedSpriteV("anniversaries27/pics/luna.png", lunaFrameCount, lunaFrameDuration, 0, 0);
        bitcoinFirstArticle = new FittedPicture("anniversaries27/pics/BTCdropArticle2.png", canvas, Color.BLACK);
        bitcoinArticle = new FittedPicture("anniversaries27/pics/BTCdropArticle.png", canvas, Color.BLACK);
        lunaArticle = new FittedPicture("anniversaries27/pics/LunaCrashArticle.png", canvas, Color.BLACK);
        threeArrows = new FittedPicture(picturesPath + "3ac.jpg", canvas, Color.BLACK);
        suZhu = new FittedPicture(picturesPath + "3AC-SuZhu.png", canvas, Color.BLACK);
        bloombergArticle = new FittedPicture(picturesPath + "3AC-Bloomberg.png", canvas, Color.BLACK);
        celsius = new FittedPicture(picturesPath + "Celsius.png", canvas, Color.BLACK);
        forbesArticle = new FittedPicture(picturesPath + "CelsiusForbes.png", canvas, Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();
        long relFrame = frame - getStartingFrame();

        log(relFrame);

        if (relFrame < bitcoinFrame) drawTwix(batch);
        else if (relFrame < lunaFrame) {
            bitcoinCrash.draw(batch);
            drawArticle(batch, bitcoinFirstArticle, relFrame, bitcoinFirstArticleFrame, bitcoinArticleFrame, articleOpacity);
            drawArticle(batch, bitcoinArticle, relFrame, bitcoinArticleFrame, lunaFrame, articleOpacity);
        } else if (relFrame < threeArrowsFrame) {
            drawLuna(batch);
            drawArticle(batch, lunaArticle, relFrame, lunaArticleFrame, threeArrowsFrame, articleOpacity);
        } else if (relFrame < celsiusFrame) {
            drawArticle(batch, threeArrows, relFrame, threeArrowsFrame, suZhuFrame, 1);
            drawArticle(batch, suZhu, relFrame, suZhuFrame, celsiusFrame, 1);
            drawArticle(batch, bloombergArticle, relFrame, bloombergFrame, celsiusFrame, overlayOpacity);
        } else {
            drawArticle(batch, celsius, relFrame, celsiusFrame, yearFrames, 1);
            drawArticle(batch, forbesArticle, relFrame, forbesFrame, yearFrames, overlayOpacity);
        }

        batch.end();
        writeYear(batch);

    }

    private void log(long relFrame) {
        //System.out.println(frame + " " + relFrame);
        if (relFrame == 30) {
            System.out.print("TwiX ");
        } else if (relFrame == 560) {
            System.out.print("BTC drop ");
        } else if (relFrame == 1100) {
            System.out.print("Luna ");
        } else if (relFrame == threeArrowsFrame) {
            System.out.print("3AC ");
        } else if (relFrame == celsiusFrame)
            System.out.println("Celsius");
    }

    /** Picture (an article over an animation, or a photo) fading in at appearFrame, shown until disappearFrame. */
    private void drawArticle(SpriteBatch batch, FittedPicture article, long relFrame, long appearFrame, long disappearFrame,
                             float opacity) {
        if (relFrame < appearFrame || relFrame >= disappearFrame)
            return;
        float fadeIn = Math.min(1f, (relFrame - appearFrame) / (float) articleFadeInFrames);
        article.drawWhole(batch, opacity * fadeIn);
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void drawTwix(SpriteBatch batch) {
        var twixPic = twiX.getTextureRegion();
        var twixSprite = new Sprite(twixPic);
        twixSprite.setScale(1f, 4.7677745f);
        twixSprite.setPosition(104, 212 + 125 - 13);
        twixSprite.draw(batch);
    }

    private void drawLuna(SpriteBatch batch) {
        var pic = luna.getTextureRegion();
        var sprite = new Sprite(pic);
        sprite.setScale(1.3f, 3.1f);
        sprite.setPosition(182, 282);
        sprite.draw(batch);
    }
}