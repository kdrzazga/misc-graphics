package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1991 extends Year {

    private final Texture python;
    private final Texture thorvalds;
    private final Texture penguin;
    private final Texture amigaOs2;
    private final Texture amiga500plus;

    Year1991(long startingFrame) {
        super("anniversaries/ya/35 years ago.mp3", startingFrame);
        this.endFrame = Math.round(startingFrame + 3.5 * Year.DEFAULT_DURATION);
        this.amigaOs2 = new Texture("anniversaries/os/amigaos.png");
        this.python = new Texture("anniversaries/languages/python.png");
        this.thorvalds = new Texture("anniversaries/os/LinusT.png");
        this.penguin = new Texture("anniversaries/os/Linux-Pinguino.png");
        this.amiga500plus = new Texture("anniversaries/HW/Amiga500+.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1991", 300, 570);
        if (frame < this.startingFrame + 0.7 * Year.DEFAULT_DURATION) {
            c64Font.draw(screen.batch2, "Linus Thorvalds", 100, 495);
            c64Font.draw(screen.batch2, "created Linux", 100, 475);
            screen.backgroundTexture = this.thorvalds;

        } else if (frame < this.startingFrame + 1.4 * Year.DEFAULT_DURATION) {
            c64Font.draw(screen.batch2, "Linus Thorvalds", 100, 495);
            c64Font.draw(screen.batch2, "created Linux,", 100, 475);
            c64Font.draw(screen.batch2, "pioneering step", 100, 455);
            c64Font.draw(screen.batch2, "towards ", 100, 435);
            c64Font.draw(screen.batch2, "open-source", 100, 415);
            c64Font.draw(screen.batch2, "software.", 100, 395);
            screen.backgroundTexture = this.penguin;

        } else if (frame < this.startingFrame + 2.1 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.amigaOs2;
            c64Font.draw(screen.batch2, "     AmigaOS 2.0 released in August 1991", 93, 535);

        } else if (frame < this.startingFrame + 2.7 * Year.DEFAULT_DURATION) {
            c64Font.draw(screen.batch2, "Commodore company introduced Amiga 500+ in October 1991", 37, 532);
            screen.backgroundTexture = this.amiga500plus;

        } else  {
            if (frame < this.startingFrame + 2.9 * Year.DEFAULT_DURATION)
                c64Font.draw(screen.batch2, "Guido van Rossum developed", 98, 532);
            else if (frame < this.startingFrame + 3.1 * Year.DEFAULT_DURATION)
                c64Font.draw(screen.batch2, "all-purpose programming language ", 145, 532);
            else if (frame < this.startingFrame + 3.3 * Year.DEFAULT_DURATION)
                c64Font.draw(screen.batch2, " and named it after British comedian group", 87, 532);
            else
                c64Font.draw(screen.batch2, "'Monty Python flying circus' ", 163, 532);
            screen.backgroundTexture = this.python;
        }
    }
}
