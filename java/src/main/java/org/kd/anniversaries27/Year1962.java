package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1962 extends Year {

    protected Year1962(long startingFrame) {
        super("anniversaries27/1962.mp3", startingFrame);
        this.endFrame = 31900;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
