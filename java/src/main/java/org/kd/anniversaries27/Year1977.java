package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1977 extends Year {

    Year1977(long startingFrame) {
        super("anniversaries27/1977.mp3", startingFrame);
        this.endFrame = 28500;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        writeYear(batch);
    }

}
