package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1957 extends Year {

    Year1957(long startingFrame) {
        super("anniversaries27/1957.mp3", startingFrame);
        this.endFrame = 29900 + 420 + 2580 + 28*60;//37300;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        writeYear(batch);
    }

}
