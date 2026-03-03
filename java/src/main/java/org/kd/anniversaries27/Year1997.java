package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public final class Year1997 extends Year {

    protected Year1997(long startingFrame) {
        super("anniversaries27/1997.mp3", startingFrame);
        this.endFrame = 17300;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
    }

}
