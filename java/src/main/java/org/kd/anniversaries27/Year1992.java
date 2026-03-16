package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1992 extends Year {

    private final Sprite suse;

    Year1992(long startingFrame) {
        super("anniversaries27/1992.mp3", startingFrame);
        this.endFrame = 22300;
        var suseTexture = new Texture("anniversaries27/pics/suse.png");
        suse = new Sprite(suseTexture);
        suse.setPosition(204, 201);
        suse.setScale(1.3f, 1.4f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        long relFrame = frame - this.startingFrame;
        sayItOnce();

        batch.begin();

        if (getStartingFrame() + 2000 < frame && frame < getStartingFrame() + 2300) {
            suse.draw(batch);
            System.out.print("SuSe");
        }

        batch.end();

        if (relFrame == 10) {System.out.println();}

        writeYear(batch);
    }

}
