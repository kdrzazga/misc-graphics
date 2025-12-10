package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1981 extends Year {

    private final Texture gates;

    Year1981() {
        super("anniversaries/ya/45 years ago.mp3");
        this.gates = new Texture("anniversaries/os/gates.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1981", 300, 570);
        if (frame < 7400) {
            screen.backgroundTexture = this.gates;
        }
    }
}
