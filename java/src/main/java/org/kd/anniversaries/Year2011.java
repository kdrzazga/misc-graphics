package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2011 extends Year {

    private final Texture motorola;

    Year2011(long startingFrame) {
        super("anniversaries/ya/15 years ago.mp3", startingFrame);
        this.motorola = new Texture("anniversaries/HW/motorola.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "2011", 300, 570);
        if (frame < this.endFrame) {
            screen.backgroundTexture = this.motorola;
            if (frame > startingFrame + Year.DEFAULT_DURATION / 3)
                c64Font.draw(screen.batch2, "January 4, 2011 - Motorola ceased to exist", 87, 535);
        }
    }
}
