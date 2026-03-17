package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2002 extends Year {

     Year2002(long startingFrame) {
        super("anniversaries27/2002.mp3", startingFrame);
        this.endFrame = 14800;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        log(relFrame);

        writeYear(batch);
    }

    private static void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Dijkstra ");
        } else if (relFrame == 1500) {
            System.out.print("eMule ");
        } else if (relFrame == 1890) {
            System.out.println("Battlefield ");
        }
    }

}
