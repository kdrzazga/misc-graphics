package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year1987 extends Year {

    Year1987(long startingFrame) {
        super("anniversaries27/1987.mp3", startingFrame);
        this.endFrame = 24700;
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
            System.out.print("TSMC ");
        } else if (relFrame == 890) {
            System.out.print("Amiga ");
        } else if (relFrame == 1450) {
            System.out.print("Perl ");
        } else if (relFrame == 2020) {
            System.out.println("Dizzy ");
        }
    }

}
