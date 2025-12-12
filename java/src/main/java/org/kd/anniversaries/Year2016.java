package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2016 extends Year{

    private final Texture leeSeDol;

    Year2016(long startingFrame) {
        super("anniversaries/ya/10 years ago.mp3", startingFrame);
        this.leeSeDol = new Texture("anniversaries/go-chess/go.png");
    }

    public void draw(long frame, Scene1c64 screen) {

        zxSpectrumFont.draw(screen.batch2, "2016", 300, 570);
        if (frame < this.endFrame) {
            screen.backgroundTexture = this.leeSeDol;
            if (frame > startingFrame + Year.DEFAULT_DURATION / 4)
                c64Font.draw(screen.batch2, " Google's DeepMind’s AlphaGo", 200, 485);
            if (frame > startingFrame + Year.DEFAULT_DURATION / 3)
                c64Font.draw(screen.batch2, "beat world champion Go player", 195, 465);
            if (frame > startingFrame + Year.DEFAULT_DURATION / 2)
                c64Font.draw(screen.batch2, "  LEE SE DOL", 310, 445);
        }
    }
}
