package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1991 extends Year {

    private final Texture python;
    private final Texture thorvalds;
    private final Texture penguin;
    private final Texture amigaOs2;
    private final Texture streetFighter2;

    Year1991(long startingFrame) {
        super("anniversaries/ya/35 years ago.mp3", startingFrame);
        this.endFrame = startingFrame + 2 * Year.DEFAULT_DURATION;
        this.amigaOs2 = new Texture("anniversaries/os/amigaos.png");
        this.python = new Texture("anniversaries/languages/python.png");
        this.thorvalds = new Texture("anniversaries/os/LinusT.png");
        this.penguin = new Texture("anniversaries/os/Linux-Pinguino.png");
        this.streetFighter2 = new Texture("anniversaries/games/sf2.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1991", 300, 570);
        if (frame < this.startingFrame + 0.35 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.thorvalds;
        } else if (frame < this.startingFrame + 0.7 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.penguin;
        } else if (frame < this.startingFrame + 1.05 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.amigaOs2;
        } else if (frame < this.startingFrame + 1.4 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.streetFighter2;
        } else {
            screen.backgroundTexture = this.python;
        }
    }
}
