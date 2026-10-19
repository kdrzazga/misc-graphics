package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2017 extends Year {

    private final Sprite fortnite;
    private final long fortniteRelFrame = 2440; // "Fortnite" part of the speech

    Year2017(long startingFrame) {
        super("anniversaries27/2017.mp3", startingFrame);
        this.endFrame = startingFrame + 2637; // 2017.mp3 lasts 43.944 s at 60 fps

        var fortniteTexture = new Texture("anniversaries27/pics/2017/Fortnite.jpg");
        fortnite = new Sprite(fortniteTexture);
        fortnite.setScale(0.75f, 0.75f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();
        fortnite.setPosition(-10, 27);

        long relFrame = frame - this.startingFrame;
        if (relFrame > fortniteRelFrame)
            fortnite.draw(batch);

        batch.end();
        log(relFrame);
        writeYear(batch);
    }

    private void log(long relFrame) {
        if (relFrame == 200) {
            System.out.print("BTC peak ");
        } else if (relFrame == 700) {
            System.out.print("ETH ");
        } else if (relFrame == 1170) {
            System.out.print("BCH ");
        } else if (relFrame == 1300) {
            System.out.print("Alphabet ");
        } else if (relFrame == 1700) {
            System.out.print("Apple iOS ");
        } else if (relFrame == fortniteRelFrame)
            System.out.println("Fortnite");
    }

}
