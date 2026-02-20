package org.kd.anniversaries27;

import org.kd.anniversaries.Scene1c64;

public class Year2022 extends Year{

    protected Year2022( long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
    }

    @Override
    public void draw(long frame, Scene1c64 screen) {
        scene4Paintbrush.render();
    }
}
