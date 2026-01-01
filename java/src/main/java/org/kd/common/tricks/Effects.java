package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Effects {

    private static final BeepSound beep = new BeepSound();

    public static void typewriter(SpriteBatch batch, BitmapFont font, float x, float y,
                                  long startFrame, String caption, int frameSkip) {

        long currentFrame = Gdx.graphics.getFrameId();
        long relativeFrame = currentFrame - startFrame;

        if (relativeFrame >= 0) {
            int charsToShow = (int) (relativeFrame / frameSkip);
            if (charsToShow > caption.length()) {
                charsToShow = caption.length();
            }
            String textToDraw = caption.substring(0, charsToShow);
            font.draw(batch, textToDraw, x, y);

            var lottery = new Random().nextFloat() * 2;
            var frequency = lottery > 1 ? 1000 : 2000;
            beep.playBeep(frequency);
        }
    }

}
