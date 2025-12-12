package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1996 extends Year {

    private final Texture gates;

    Year1996(long startingFrame) {
        super("anniversaries/ya/30 years ago.mp3", startingFrame);
        this.gates = new Texture("anniversaries/chess/kasparov.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1996", 300, 570);
        if (frame < 7400) {
            screen.backgroundTexture = this.gates;
        }
    }
}
