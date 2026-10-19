package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;
import org.kd.common.VideoClip;

public final class Year2022 extends Year {

    private final AnimatedSpriteV twiX, luna;
    private final VideoClip bitcoinCrash;
    private final Sprite gradient;

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = startingFrame + 3062; // 2022.mp3 lasts 51.024 s at 60 fps
        twiX = new AnimatedSpriteV("anniversaries27/pics/TwiX.png", 71, 0.0704f, 0, 0);
        // 432x304 video scaled to the canvas height (543), centred horizontally in the 797 px wide canvas.
        // The file plays forward then backward (baked in with ffmpeg reverse), so looping it gives a ping-pong.
        bitcoinCrash = new VideoClip("anniversaries27/video/BitcoinCrash.mp4", 24, 117, 112, 771, 543, true);
        luna = new AnimatedSpriteV("anniversaries27/pics/luna.png", 50, 0.21f, 0, 0);
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

        if (relFrame < 350) drawTwix(batch);
        else if (relFrame < 1100)
            bitcoinCrash.draw(batch);
        else if (relFrame < 1950)
            drawLuna(batch);
        else
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