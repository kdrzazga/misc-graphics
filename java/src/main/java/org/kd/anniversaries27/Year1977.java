package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1977 extends Year {

    private final Sprite apple;

    Year1977(long startingFrame) {
        super("anniversaries27/1977.mp3", startingFrame);
        this.endFrame = 28500;

        var appleTexture = new Texture("apple2.jpg");
        apple = new Sprite(appleTexture);
        apple.setPosition(346, 175);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        batch.begin();
        apple.draw(batch);
        batch.end();

        long relFrame = frame - this.startingFrame;
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Apple2 ");
        } else if (relFrame == 200) {
            System.out.print("Oracle ");
        } else if (relFrame == 380) {
            System.out.println("Nintendo ");
        }

        writeYear(batch);
    }

}
