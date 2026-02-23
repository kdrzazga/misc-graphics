package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year1967 extends Year {

    Year1967(long startingFrame) {
        super("anniversaries27/1967.mp3", startingFrame);
        this.endFrame = 29900 + 420;//30400;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
