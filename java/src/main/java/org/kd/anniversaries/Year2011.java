package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2011 extends Year {

    private final Texture gates;

    Year2011(long startingFrame) {
        super("anniversaries/ya/15 years ago.mp3", startingFrame);
        this.gates = new Texture("anniversaries/chess/kasparov.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "2011", 300, 570);
        if (frame < this.endFrame) {
            screen.backgroundTexture = this.gates;
        }
    }
}
