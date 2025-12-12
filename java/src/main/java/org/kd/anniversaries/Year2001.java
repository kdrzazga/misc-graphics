package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public class Year2001 extends Year {

    private final Texture winXP;

    Year2001(long startingFrame) {
        super("anniversaries/ya/25 years ago.mp3", startingFrame); //should be 25 y a
        this.winXP = new Texture("anniversaries/os/winXP.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        zxSpectrumFont.draw(screen.batch2, "2001", 300, 570);
        if (frame < 6700) {
            screen.backgroundTexture = this.winXP;
        }
    }
}
