package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1972 extends Year {

    Year1972(long startingFrame) {
        super("anniversaries27/1972.mp3", startingFrame);
        this.endFrame = 29900;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        log(relFrame);

        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Pichai ");
        } else if (relFrame == 330) {
            System.out.print("Prolog ");
        } else if (relFrame == 650) {
            System.out.println("Pong ");
        }
    }

}
