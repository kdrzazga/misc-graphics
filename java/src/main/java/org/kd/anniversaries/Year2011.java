package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.List;

public class Year2011 extends Year {

    private final List<Texture> motorolas;

    Year2011(long startingFrame) {
        super("anniversaries/ya/15 years ago.mp3", startingFrame);
        this.endFrame -= DEFAULT_DURATION/3;
        this.motorolas = Arrays.asList(
                new Texture("anniversaries/HW/motorola.png"),
                new Texture("anniversaries/HW/motorola-cracked1.png"),
                new Texture("anniversaries/HW/motorola-cracked2.png"),
                new Texture("anniversaries/HW/motorola-cracked3.png"),
                new Texture("anniversaries/HW/motorola-cracked4.png"),
                new Texture("anniversaries/HW/motorola-cracked5.png")
        );
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "2011", 300, 570);
        if (frame < this.endFrame) {

            long interval = (this.endFrame - this.startingFrame) / 6;
            long elapsed = frame - this.startingFrame;

            int index = (int) (elapsed / interval);
            if (index >= motorolas.size()) {
                index = motorolas.size() - 1;
            }

            screen.backgroundTexture = this.motorolas.get(index);

            if (frame > this.startingFrame + interval) {
                c64Font.draw(screen.batch2, "January 4, 2011 - Motorola ceased to exist", 87, 535);
            }
        }
    }
}

