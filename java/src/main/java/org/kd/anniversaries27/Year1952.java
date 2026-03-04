package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1952 extends Year {

    private final Sprite besm, ibm701;

    Year1952(long startingFrame) {
        super("anniversaries27/1952.mp3", startingFrame);
        this.endFrame = 29900 + 420 + 2580 + 28 * 60 + 35 * 60;//39700;

        ibm701 = new Sprite(new Texture("anniversaries27/pics/IBM701.jpg"));
        ibm701.setScale(0.95f);
        var besmTexture = new Texture("anniversaries27/pics/BESM-1-computer-on-the-ground-floor-of-IPMCE-building-in-Moscow-251888155.jpg");
        besm = new Sprite(besmTexture);
        besm.setScale(1.7f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        System.out.println(frame + " " + (frame - startingFrame));
        var change = startingFrame + 500;
        var pic = (startingFrame < frame && frame < change) ? besm : ibm701;
        pic.setPosition(104, 109);

        batch.begin();
        pic.draw(batch);

        font1.draw(batch, getYear(), 104, 500);
        batch.end();
        sayItOnce();

        writeYear(batch);
    }
}
