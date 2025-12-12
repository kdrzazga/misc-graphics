package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2006 extends Year {

    private final Texture twitter1;
    private final Texture twitter2;
    private final Texture googleYT;

    Year2006(long startingFrame) {
        super("anniversaries/ya/20 years ago.mp3", startingFrame);
        this.twitter1 = new Texture("anniversaries/apps/twitter.jpg");
        this.twitter2 = new Texture("anniversaries/apps/twitter2.jpg");
        this.googleYT = new Texture("anniversaries/apps/googleYT.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        zxSpectrumFont.draw(screen.batch2, "2006", 300, 570);
        if (frame < this.startingFrame + 0.4*Year.DEFAULT_DURATION) {
            screen.backgroundTexture = this.googleYT;
        } else if (frame < this.startingFrame + 0.7*DEFAULT_DURATION) {
            screen.backgroundTexture = this.twitter1;
        }else if (frame < this.endFrame) {
            screen.backgroundTexture = this.twitter2;
        }
    }
}
