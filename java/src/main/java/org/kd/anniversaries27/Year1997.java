package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1997 extends Year {

    protected Year1997(long startingFrame) {
        super("anniversaries27/1997.mp3", startingFrame);
        this.endFrame = 17300;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
