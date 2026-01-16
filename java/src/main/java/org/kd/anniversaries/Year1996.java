package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1996 extends Year {

    private final Texture kasparov;
    private final Texture java;
    private final Texture playstation;

    Year1996(long startingFrame) {
        super("anniversaries/ya/30 years ago.mp3", startingFrame);
        this.endFrame = Math.round(this.startingFrame + 1.5 * Year.DEFAULT_DURATION);
        this.kasparov = new Texture("anniversaries/go-chess/kasparov.png");
        this.java = new Texture("anniversaries/languages/java.png");
        this.playstation = new Texture("anniversaries/HW/playstation.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1996", 300, 570);
        if (frame < this.startingFrame + 0.4 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.kasparov;
            c64Font.draw(screen.batch2, "Garii Kasparov beat IBM Deep Blue 4 times.", 90, 535);

        } else if (frame < this.startingFrame + 1.2 * DEFAULT_DURATION) {
            c64Font.draw(screen.batch2, "Sun Microsystems released JAVA in January 1996", 90, 535);

            if (frame > this.startingFrame + 0.45 * DEFAULT_DURATION) {
                c64Font.draw(screen.batch2, "Java is like a wheel", 100, 255);
                c64Font.draw(screen.batch2, "invented long time ago,", 100, 235);
                c64Font.draw(screen.batch2, "but still needed.", 100, 215);
            }
            if (frame > this.startingFrame + 0.6 * DEFAULT_DURATION) {
                c64Font.draw(screen.batch2, "This demo is actually", 100, 165);
                c64Font.draw(screen.batch2, "written in Java. LOL", 100, 145);
            }
            screen.backgroundTexture = this.java;

        } else if (frame < this.endFrame) {
            if (frame < this.startingFrame + 1.35 * DEFAULT_DURATION)
                c64Font.draw(screen.batch2, "In December 1996 flagship product of Sony", 90, 535);
            else c64Font.draw(screen.batch2, " became available in South Africa :D", 90, 535);
            screen.backgroundTexture = this.playstation;
        }
    }
}
