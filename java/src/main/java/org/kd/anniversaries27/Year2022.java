package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.VideoClip;

public final class Year2022 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);

    private final long bitcoinFrame = 350;
    private final long lunaFrame = 1100;
    private final long threeArrowsFrame = 1950;

    // The articles appear when the animation under them has looped once
    private final long bitcoinCrashLoopFrames = 485; // 194 video frames at 24 fps
    private final int lunaFrameCount = 50;
    private final float lunaFrameDuration = 0.21f;
    private final long lunaLoopFrames = Math.round(lunaFrameCount * lunaFrameDuration * 60);
    private final long bitcoinArticleFrame = bitcoinFrame + bitcoinCrashLoopFrames;
    private final long lunaArticleFrame = lunaFrame + lunaLoopFrames;
    private final float articleOpacity = 0.8f; // 20% transparent
    private final long articleFadeInFrames = 15;

    private final AnimatedSpriteV twiX, luna;
    private final VideoClip bitcoinCrash;
    private final Sprite gradient;
    private final FittedPicture bitcoinArticle, lunaArticle;

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = startingFrame + 3062; // 2022.mp3 lasts 51.024 s at 60 fps
        twiX = new AnimatedSpriteV("anniversaries27/pics/TwiX.png", 71, 0.0704f, 0, 0);
        // 432x304 video scaled to the canvas height (543), centred horizontally in the 797 px wide canvas.
        // The file plays forward then backward (baked in with ffmpeg reverse), so looping it gives a ping-pong.
        bitcoinCrash = new VideoClip("anniversaries27/video/BitcoinCrash.mp4", 24, 117, 112, 771, 543, true);
        luna = new AnimatedSpriteV("anniversaries27/pics/luna.png", lunaFrameCount, lunaFrameDuration, 0, 0);
        bitcoinArticle = new FittedPicture("anniversaries27/pics/BTCdropArticle.png", canvas, Color.BLACK);
        lunaArticle = new FittedPicture("anniversaries27/pics/LunaCrashArticle.png", canvas, Color.BLACK);
        var gradientTexture = new Texture("gradient-black.png");
        gradient = new Sprite(gradientTexture);
        gradient.setPosition(104, 212 + 125 - 13);
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
            drawArticle(batch, bitcoinArticle, relFrame, bitcoinArticleFrame);
        } else if (relFrame < threeArrowsFrame) {
            drawLuna(batch);
            drawArticle(batch, lunaArticle, relFrame, lunaArticleFrame);
        } else
            gradient.draw(batch);

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
        } else if (relFrame == 1950) {
            System.out.print("3AC ");
        } else if (relFrame == 2500)
            System.out.println("Celsius");
    }

    /** News article over the looping animation, from appearFrame on, partly transparent. */
    private void drawArticle(SpriteBatch batch, FittedPicture article, long relFrame, long appearFrame) {
        if (relFrame < appearFrame)
            return;
        float fadeIn = Math.min(1f, (relFrame - appearFrame) / (float) articleFadeInFrames);
        article.drawWhole(batch, articleOpacity * fadeIn);
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