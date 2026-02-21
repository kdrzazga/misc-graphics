package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year1987 extends Year {

    protected Year1987(long startingFrame) {
        super("anniversaries27/1987.mp3", startingFrame);
        this.endFrame = 24700;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
