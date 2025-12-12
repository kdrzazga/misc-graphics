package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year1981 extends Year {

    private final Texture gates;
    private final Texture vic20;

    Year1981(long startingFrame) {
        super("anniversaries/ya/45 years ago.mp3", startingFrame);
        this.gates = new Texture("anniversaries/os/gates.png");
        this.vic20 = new Texture("anniversaries/computers/vic20.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "1981", 300, 570);
        if (frame < this.startingFrame + 0.35 * Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.vic20;
        } else if (frame < this.endFrame) {
            screen.backgroundTexture = this.gates;
        }
    }
}
