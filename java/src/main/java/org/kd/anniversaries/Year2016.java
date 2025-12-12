package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2016 extends Year{

    private final Texture jobsWozniak;

    Year2016(long startingFrame) {
        super("anniversaries/ya/10 years ago.mp3", startingFrame);
        this.jobsWozniak = new Texture("anniversaries/os/JobsWayneWozniak.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        zxSpectrumFont.draw(screen.batch2, "2016", 300, 570);
        if (frame < this.endFrame) {
            screen.backgroundTexture = this.jobsWozniak;
        }
    }
}
