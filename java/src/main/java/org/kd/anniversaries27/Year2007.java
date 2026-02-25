package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year2007 extends Year {

    protected Year2007(long startingFrame) {
        super("anniversaries27/2007.mp3", startingFrame);
        this.endFrame = 12500;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
