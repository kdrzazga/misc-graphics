package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public class Year2021 {

    private final Texture raspberryPI;
    private final Texture win11Logo;

    Year2021() {
        this.raspberryPI = new Texture("anniversaries/computers/RaspberriPi2zero.png");
        this.win11Logo = new Texture("anniversaries/os/win11.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        BitmapFont zxSpectrumFont = C64Helper.createFont(32, "zx-spectrum.ttf");
        zxSpectrumFont.draw(screen.batch2, "2021", 300, 570);
        if (frame < 5117) {
            screen.backgroundTexture = this.raspberryPI;
        } else if (frame < 5517) {
            screen.backgroundTexture = this.win11Logo;
        }
    }
}
