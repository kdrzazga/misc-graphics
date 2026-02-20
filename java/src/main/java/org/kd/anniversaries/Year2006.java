package org.kd.anniversaries;

import com.badlogic.gdx.graphics.Texture;

public class Year2006 extends Year {

    private final Texture twitter1;
    private final Texture twitter2;
    private final Texture googleYT;
    private final Texture googleNoYT;

    Year2006(long startingFrame) {
        super("anniversaries/ya/20 years ago.mp3", startingFrame);
        this.twitter1 = new Texture("anniversaries/apps/twitter.png");
        this.twitter2 = new Texture("anniversaries/apps/twitter2.png");
        this.googleYT = new Texture("anniversaries/apps/googleYT.png");
        this.googleNoYT = new Texture("anniversaries/apps/googlenoYT.png");
    }

    public void draw(long frame, Scene1c64 screen) {
        font1.draw(screen.batch2, "2006", 300, 570);
        if (frame < this.startingFrame + 0.4 * Year.DEFAULT_DURATION) {
            font2.draw(screen.batch2, "Google acquired YouTube for $1.65 billion", 93, 535);
            if (frame < this.startingFrame + 0.2 * Year.DEFAULT_DURATION)
                screen.backgroundTexture = this.googleNoYT;
            else screen.backgroundTexture = this.googleYT;

        } else if (frame < this.startingFrame + 0.7 * DEFAULT_DURATION) {
            screen.backgroundTexture = this.twitter1;
            font2.draw(screen.batch2, "Jack Dorsey,Biz Stone,Noah Glass,and Evan Williams", 28, 535);

        } else if (frame < this.endFrame) {
            font2.draw(screen.batch2, " founded TWITTER in March 2006", 183, 535);
            screen.backgroundTexture = this.twitter2;
        }
    }
}
