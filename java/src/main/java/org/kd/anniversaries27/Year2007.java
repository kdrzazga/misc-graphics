package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2007 extends Year {

     Year2007(long startingFrame) {
        super("anniversaries27/2007.mp3", startingFrame);
        this.endFrame = 12500;
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
            System.out.print("IPhone ");
        } else if (relFrame == 300) {
            System.out.print("WinVista ");
        } else if (relFrame == 600) {
            System.out.print("Kindle ");
        } else if (relFrame == 850) {
            System.out.print("Amazon Dynamo ");
        } else if (relFrame == 1250) {
            System.out.print("IBM Cloud ");
        } else if (relFrame == 1620)
            System.out.println("Witcher");
    }

}
