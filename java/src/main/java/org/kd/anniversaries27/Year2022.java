package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;

public final class Year2022 extends Year {

    private final AnimatedSpriteV twiX, bitcoin, luna;
    private final Sprite gradient;

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = 5500;
        twiX = new AnimatedSpriteV("anniversaries27/pics/TwiX.png", 71, 0.0704f, 0, 0);
        bitcoin = new AnimatedSpriteV("anniversaries27/pics/btc.png", 40, 0.0704f, 0, 0);
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

        if (relFrame < 350) drawTwix(batch);
        else if (relFrame < 2900)
            drawBitcoin(batch);
        else if (frame < 3500)

            drawLuna(batch);
        else
            gradient.draw(batch);

        batch.end();

        writeYear(batch);

        System.out.println(frame + " " + relFrame);
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

    private void drawBitcoin(SpriteBatch batch) {
        var pic = bitcoin.getTextureRegion();
        var sprite = new Sprite(pic);
        sprite.setScale(1f, 1f);
        sprite.setPosition(104, 212 + 125 - 13);
        sprite.draw(batch);
    }
}