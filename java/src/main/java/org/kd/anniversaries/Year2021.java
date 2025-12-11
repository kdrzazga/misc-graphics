package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2021 extends Year {

    private final Texture raspberryPI;
    private final Texture win11Logo;

    Year2021() {
        super("anniversaries/ya/5 years ago.mp3");
        this.raspberryPI = new Texture("anniversaries/computers/RaspberriPi2zero.png");
        this.win11Logo = new Texture("anniversaries/os/win11.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "2021", 300, 570);
        if (frame < 4117) {
            screen.backgroundTexture = this.raspberryPI;
        } else if (frame < 4617) {
            screen.backgroundTexture = this.win11Logo;
        }
    }
}
