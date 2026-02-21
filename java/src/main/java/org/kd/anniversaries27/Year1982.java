package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year1982 extends Year {

    protected Year1982(long startingFrame) {
        super("anniversaries27/1982.mp3", startingFrame);
        this.endFrame = 27450;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
