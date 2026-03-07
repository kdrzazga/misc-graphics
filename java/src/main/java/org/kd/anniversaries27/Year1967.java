package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1967 extends Year {

    private final Sprite satyaNadella;

    Year1967(long startingFrame) {
        super("anniversaries27/1967.mp3", startingFrame);
        this.endFrame = 29900 + 420;//30400;

        var satyaNadellaTexture = new Texture("anniversaries27/pics/1967/satyaNadella.jpg");
        satyaNadella = new Sprite(satyaNadellaTexture);
        satyaNadella.setPosition(104, 315);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();
        satyaNadella.draw(batch);
        batch.end();

        writeYear(batch);
    }

}
