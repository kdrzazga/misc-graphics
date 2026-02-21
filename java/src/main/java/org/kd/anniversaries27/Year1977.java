package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year1977 extends Year {

    protected Year1977(long startingFrame) {
        super("anniversaries27/1977.mp3", startingFrame);
        this.endFrame = 28500;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
