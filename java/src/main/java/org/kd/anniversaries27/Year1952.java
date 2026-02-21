package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1952 extends Year {

    protected Year1952(long startingFrame) {
        super("anniversaries27/1952.mp3", startingFrame);
        this.endFrame = 39700;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
