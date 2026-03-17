package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1952 extends Year {

    private final Sprite besm, ibm701;

    Year1952(long startingFrame) {
        super("anniversaries27/1952.mp3", startingFrame);
        this.endFrame = 29900 + 420 + 2580 + 28 * 60 + 35 * 60;//39700;

        var besmTexture = new Texture("anniversaries27/pics/BESM-1-computer-on-the-ground-floor-of-IPMCE-building-in-Moscow-251888155.jpg");
        besm = new Sprite(besmTexture);
        //besm.setScale(1.7f);

        ibm701 = new Sprite(new Texture("anniversaries27/pics/IBM701.jpg"));
        ibm701.setScale(1.5f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - startingFrame;
        var change = startingFrame + 500;
        var pic = (startingFrame < frame && frame < change) ? ibm701 : besm;
        pic.setPosition(104, 109);

        batch.begin();
        pic.draw(batch);
        batch.end();
        sayItOnce();

        log(relFrame);
        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 1) {
            System.out.print("IBM-701 ");
        } else if (relFrame == 510) {
            System.out.println("BESM");
        }
    }
}
