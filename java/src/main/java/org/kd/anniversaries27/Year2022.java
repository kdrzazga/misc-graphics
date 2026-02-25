package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year2022 extends Year {

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = 5500;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
