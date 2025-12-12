package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1986 extends Year {

    private final Texture spectrum;

    Year1986(long startingFrame) {
        super("anniversaries/ya/40 years ago.mp3", startingFrame);
        this.spectrum = new Texture("anniversaries/computers/zxSpectrum+2.jpg");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1986", 300, 570);
        if (frame < this.endFrame) {
            screen.backgroundTexture = this.spectrum;
        }
    }
}
