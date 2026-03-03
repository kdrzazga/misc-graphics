package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1992 extends Year {

    private final Texture suse;

    Year1992(long startingFrame) {
        super("anniversaries27/1992.mp3", startingFrame);
        this.endFrame = 22300;
        suse = new Texture("anniversaries27/pics/suse.png");
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();

        if (getStartingFrame() + 500 < frame && frame < getStartingFrame() + 700) {
            batch.draw(suse, 104, 111);
        }

        batch.end();

        writeYear(batch);
    }

}
