package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1957 extends Year {

    protected Year1957(long startingFrame) {
        super("anniversaries27/1957.mp3", startingFrame);
        this.endFrame = 39700;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
