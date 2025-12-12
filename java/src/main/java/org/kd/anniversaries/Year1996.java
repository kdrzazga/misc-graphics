package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1996 extends Year {

    private final Texture kasparov;
    private final Texture java;
    private final Texture playstation;

    Year1996(long startingFrame) {
        super("anniversaries/ya/30 years ago.mp3", startingFrame);
        this.kasparov = new Texture("anniversaries/go-chess/kasparov.png");
        this.java = new Texture("anniversaries/languages/java.png");
        this.playstation = new Texture("anniversaries/computers/playstation.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1996", 300, 570);
        if (frame < this.startingFrame + 0.4*Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.kasparov;
        } else if (frame < this.startingFrame + 0.7*DEFAULT_DURATION) {
            screen.backgroundTexture = this.java;
        }else if (frame < this.endFrame) {
            screen.backgroundTexture = this.playstation;
        }
    }
}
