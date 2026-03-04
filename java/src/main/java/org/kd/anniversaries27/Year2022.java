package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.AnimatedSpriteV;

public final class Year2022 extends Year {

    private Texture elon;
    private AnimatedSpriteV twiX;

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = 5500;
        twiX = new AnimatedSpriteV("anniversaries27/pics/TwiX.png", 71, 0.0704f, 0, 0);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();
        if (frame - getStartingFrame() < 350) drawTwix(batch);
        batch.end();

        writeYear(batch);

        System.out.println(frame + " " + (frame - getStartingFrame()));
    }

    private void drawTwix(SpriteBatch batch) {
        var twixPic = twiX.getTextureRegion();
        var twixSprite = new Sprite(twixPic);
        twixSprite.setScale(1f, 4.7677745f);
        twixSprite.setPosition(104, 212 + 125 - 13);
        twixSprite.draw(batch);
    }
}