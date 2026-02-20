package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import org.kd.common.Scene;

public class Year2021 extends Year {

    private final Texture raspberryPI;
    private final Texture win11Logo;

    Year2021(long startingFrame) {
        super("anniversaries/ya/5 years ago.mp3", startingFrame);
        this.raspberryPI = new Texture("anniversaries/HW/RaspberriPi2zero.png");
        this.win11Logo = new Texture("anniversaries/os/win11.png");
    }

    public void draw(long frame, Scene scene) {
        var screen = (Scene1c64) scene;
        font1.draw(screen.batch2, "2021", 300, 570);
        if (frame < startingFrame + Year.DEFAULT_DURATION / 2) {
            screen.backgroundTexture = this.raspberryPI;

            if (frame > startingFrame + Year.DEFAULT_DURATION / 5) {
                font2.draw(screen.batch2, "Raspberry PI ZERO 2 W", 255, 460);
                font2.draw(screen.batch2, "was introduced in October 2021", 180, 444);
            }
        } else if (frame < endFrame) {
            screen.backgroundTexture = this.win11Logo;
            if (frame > startingFrame + 2 * Year.DEFAULT_DURATION / 3)
                font2.draw(screen.batch2, "Windows 11 was released on June 24, 2021", 98, 535);
        }
    }
}
