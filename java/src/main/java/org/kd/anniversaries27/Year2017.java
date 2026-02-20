package org.kd.anniversaries27;

import org.kd.common.Scene;

public class Year2017 extends Year {

    protected Year2017(long startingFrame) {
        super("anniversaries27/2017.mp3", startingFrame);
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
