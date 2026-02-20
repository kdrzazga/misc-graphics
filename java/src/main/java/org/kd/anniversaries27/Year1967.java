package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1967 extends Year {

    protected Year1967(long startingFrame) {
        super("anniversaries27/1967.mp3", startingFrame);
        this.endFrame = 33700;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
