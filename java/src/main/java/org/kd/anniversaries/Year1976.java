package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1976 extends Year{

    private final Texture jobsWozniak;

    Year1976(long startingFrame) {
        super("anniversaries/ya/50 years ago.mp3", startingFrame);
        this.jobsWozniak = new Texture("anniversaries/os/JobsWayneWozniak.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        zxSpectrumFont.draw(screen.batch2, "1976", 300, 570);
        if (frame < this.startingFrame + Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.jobsWozniak;
        }
    }
}
