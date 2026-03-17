package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Year2012 extends Year {

    protected Year2012(long startingFrame) {
        super("anniversaries27/2012.mp3", startingFrame);
        this.endFrame = 10500;
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        if (frame == 8350) {
            System.out.print("FB ");
        } else if (frame == 8950) {
            System.out.print("iPhone5 ");
        } else if (frame == 9150) {
            System.out.print("Apple OSX ");
        } else if (frame == 9720) {
            System.out.print("Win8 ");
        } else if (frame == 10300)
            System.out.println("TS");

        writeYear(batch);
    }

}
