package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1987 extends Year {

    Year1987(long startingFrame) {
        super("anniversaries27/1987.mp3", startingFrame);
        this.endFrame = 24700;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        writeYear(batch);
    }

}
