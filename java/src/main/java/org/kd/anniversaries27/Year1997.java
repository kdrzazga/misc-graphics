package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1997 extends Year {

    protected Year1997(long startingFrame) {
        super("anniversaries27/1997.mp3", startingFrame);
        this.endFrame = 17300;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Win98 ");
        } else if (relFrame == 200) {
            System.out.print("Yahoo ");
        } else if (relFrame == 600) {
            System.out.print("PostgreSQL ");
        } else if (relFrame == 1100) {
            System.out.print("Kaspersky ");
        } else if (relFrame == 1800) {
            System.out.print("Tibia ");
        } else if (relFrame == 2200) {
            System.out.println("GTA ");
        }
        writeYear(batch);
    }

}
