package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.kd.common.C64Helper;

public class Year2006 extends Year {

    private final Texture twitter1;
    private final Texture twitter2;
    private final Texture googleYT;

    Year2006() {
        super("anniversaries/ya/20 years ago.mp3");
        this.twitter1 = new Texture("anniversaries/apps/twitter.jpg");
        this.twitter2 = new Texture("anniversaries/apps/twitter2.jpg");
        this.googleYT = new Texture("anniversaries/apps/googleYT.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        BitmapFont zxSpectrumFont = C64Helper.createFont(32, "zx-spectrum.ttf");
        zxSpectrumFont.draw(screen.batch2, "2006", 300, 570);
        if (frame < 6250) {
            screen.backgroundTexture = this.googleYT;
        } else if (frame < 6300) {
            screen.backgroundTexture = this.twitter1;
        }else if (frame < 6380) {
            screen.backgroundTexture = this.twitter2;
        }
    }
}
