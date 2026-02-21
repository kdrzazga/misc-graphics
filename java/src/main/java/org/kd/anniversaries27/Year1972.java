package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year1972 extends Year {

    protected Year1972(long startingFrame) {
        super("anniversaries27/1972.mp3", startingFrame);
        this.endFrame = 29900;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
