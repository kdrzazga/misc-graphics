package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year1962 extends Year {

    Year1962(long startingFrame) {
        super("anniversaries27/1962.mp3", startingFrame);
        this.endFrame = 29900 + 420 + 2580;//33900;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
