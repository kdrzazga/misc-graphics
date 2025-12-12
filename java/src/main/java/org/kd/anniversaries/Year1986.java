package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1986 extends Year {

    private final Texture gates;

    Year1986(long startingFrame) {
        super("anniversaries/ya/40 years ago.mp3", startingFrame);
        this.gates = new Texture("anniversaries/os/gates.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1986", 300, 570);
        if (frame < this.startingFrame + Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.gates;
        }
    }
}
