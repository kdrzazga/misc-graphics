package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1982 extends Year {

    Year1982(long startingFrame) {
        super("anniversaries27/1982.mp3", startingFrame);
        this.endFrame = 27450;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Sun ");
        } else if (relFrame == 1020) {
            System.out.print("C64 ");
        } else if (relFrame == 1780) {
            System.out.print("CD ");
        } else if (relFrame == 2099) {
            System.out.println("EA,RA ");
        }

        writeYear(batch);
    }

}
