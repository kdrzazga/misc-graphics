package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public class Year1976 extends Year{

    private final Texture jobsWozniak;

    Year1976() {
        super("anniversaries/ya/50 years ago.mp3");
        this.jobsWozniak = new Texture("anniversaries/os/JobsWayneWozniak.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        BitmapFont zxSpectrumFont = C64Helper.createFont(32, "zx-spectrum.ttf");
        zxSpectrumFont.draw(screen.batch2, "1976", 300, 570);
        if (frame < 8000) {
            screen.backgroundTexture = this.jobsWozniak;
        }
    }
}
