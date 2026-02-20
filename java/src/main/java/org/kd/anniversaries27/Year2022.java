package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year2022 extends Year {

    protected Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = 5500;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
