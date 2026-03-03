package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2022 extends Year {

    private Texture elon;

    Year2022(long startingFrame) {
        super("anniversaries27/2022.mp3", startingFrame);
        this.endFrame = 5500;

        elon = new Texture("anniversaries27/pics/mozg.jpg");
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {

        int topY = 680;

        sayItOnce();
        batch.begin();
        batch.draw(elon, 104, 111);
        batch.end();

        writeYear(batch);
    }
}