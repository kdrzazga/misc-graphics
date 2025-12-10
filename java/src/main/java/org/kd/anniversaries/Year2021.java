package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import org.kd.common.BasicC64Screen;

public class Year2021 {

    private Texture raspberryPI;
    private Texture win11Logo;

    Year2021() {
        this.raspberryPI = new Texture("anniversaries/computers/RaspberriPi2zero.png");
        this.win11Logo = new Texture("anniversaries/os/win11.png");
    }

    public void draw(long frame, BasicC64Screen screen) {
        if (frame < 5117) {
            screen.backgroundTexture = this.raspberryPI;
        } else if (frame < 5517) {
            screen.backgroundTexture = this.win11Logo;
        }
    }
}
