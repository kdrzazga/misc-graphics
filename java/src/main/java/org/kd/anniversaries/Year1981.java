package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public class Year1981 {

    private final Texture gates;

    Year1981() {
        this.gates = new Texture("anniversaries/os/gates.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        BitmapFont zxSpectrumFont = C64Helper.createFont(32, "zx-spectrum.ttf");
        zxSpectrumFont.draw(screen.batch2, "1981", 300, 570);
        if (frame < 7400) {
            screen.backgroundTexture = this.gates;
        }
    }
}
