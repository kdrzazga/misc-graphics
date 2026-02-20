package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1977 extends Year {

    protected Year1977(long startingFrame) {
        super("anniversaries27/1977.mp3", startingFrame);
        this.endFrame = 26700;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
