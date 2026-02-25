package org.kd.anniversaries27;

import org.kd.common.Scene;

public final class Year1992 extends Year {

    protected Year1992(long startingFrame) {
        super("anniversaries27/1992.mp3", startingFrame);
        this.endFrame = 22300;
    }

    @Override
    public void draw(long frame, Scene screen) {
        sayItOnce();
    }

}
