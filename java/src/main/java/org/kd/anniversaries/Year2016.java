package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2016 extends Year{

    private final Texture jobsWozniak;

    Year2016() {
        super("anniversaries/ya/10 years ago.mp3");
        this.jobsWozniak = new Texture("anniversaries/os/JobsWayneWozniak.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        zxSpectrumFont.draw(screen.batch2, "2016", 300, 570);
        if (frame < 8400) {
            screen.backgroundTexture = this.jobsWozniak;
        }
    }
}
