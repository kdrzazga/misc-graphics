package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2002 extends Year {

    protected Year2002(long startingFrame) {
        super("anniversaries27/2002.mp3", startingFrame);
        this.endFrame = 14800;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
    }

}
