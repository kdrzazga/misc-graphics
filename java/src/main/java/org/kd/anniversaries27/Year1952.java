package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1952 extends Year {

    private final Texture ibm701, besm;

    Year1952(long startingFrame) {
        super("anniversaries27/1952.mp3", startingFrame);
        this.endFrame = 29900 + 420 + 2580 + 28 * 60 + 35 * 60;//39700;

        ibm701 = new Texture("anniversaries27/pics/IBM701.jpg");
        besm = new Texture("anniversaries27/pics/BESM-1-computer-on-the-ground-floor-of-IPMCE-building-in-Moscow-251888155.jpg");
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        System.out.println(frame + " " + (frame - startingFrame));
        var halfWay = (endFrame - startingFrame) / 2;
        var pic = (startingFrame < frame && frame < halfWay) ? besm : ibm701;

        batch.begin();
        batch.draw(pic, 104, 109);
        batch.end();
        sayItOnce();
    }
}
