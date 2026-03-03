package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public final class Year2012 extends Year {

    protected Year2012(long startingFrame) {
        super("anniversaries27/2012.mp3", startingFrame);
        this.endFrame = 10500;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
    }

}
