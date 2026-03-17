package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2017 extends Year {

    private final Sprite fortnite;

    Year2017(long startingFrame) {
        super("anniversaries27/2017.mp3", startingFrame);
        this.endFrame = 8300;

        var fortniteTexture = new Texture("anniversaries27/pics/2017/Fortnite.jpg");
        fortnite = new Sprite(fortniteTexture);
        fortnite.setScale(0.75f, 0.75f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();
        fortnite.setPosition(-10, 25);

        if (frame > this.endFrame - 360)
            fortnite.draw(batch);

        batch.end();
        log(frame);
        writeYear(batch);
    }

    private void log(long frame) {
        if (frame == 5700) {
            System.out.print("BTC peak ");
        } else if (frame == 6200) {
            System.out.print("ETH ");
        } else if (frame == 6670) {
            System.out.print("BCH ");
        } else if (frame == 6800) {
            System.out.print("Alphabet ");
        } else if (frame == 7200) {
            System.out.print("Apple iOS ");
        } else if (frame == this.endFrame - 360)
            System.out.println("Fortnite");
    }

}
