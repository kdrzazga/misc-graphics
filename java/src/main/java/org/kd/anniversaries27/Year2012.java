package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2012 extends Year {

    Year2012(long startingFrame) {
        super("anniversaries27/2012.mp3", startingFrame);
        this.endFrame = startingFrame + 2153; // 2012.mp3 lasts 35.880 s at 60 fps
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        log(frame - this.startingFrame);

        writeYear(batch);
    }

    private static void log(long relFrame) {
        if (relFrame == 50) {
            System.out.print("FB ");
        } else if (relFrame == 650) {
            System.out.print("iPhone5 ");
        } else if (relFrame == 850) {
            System.out.print("Apple OSX ");
        } else if (relFrame == 1420) {
            System.out.print("Win8 ");
        } else if (relFrame == 2000)
            System.out.println("TS");
    }

}
