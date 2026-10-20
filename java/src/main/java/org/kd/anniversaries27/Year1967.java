package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.VideoClip;

public final class Year1967 extends Year {

    private final VideoClip nadellaKeynote;

    Year1967(long startingFrame) {
        super("anniversaries27/1967.mp3", startingFrame);
        this.endFrame = startingFrame + 443; // 1967.mp3 lasts 7.368 s at 60 fps

        // 7 s, 640x360 video fitted to the canvas width: 797x448, centred vertically.
        // Its last frame stays for the remaining 0.37 s of the narration.
        nadellaKeynote = new VideoClip("anniversaries27/video/nandella.mp4", 30, 104, 160, 797, 448, false);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - getStartingFrame();
        sayItOnce();

        batch.begin();
        nadellaKeynote.draw(batch);
        batch.end();

        log(relFrame);
        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.println("SatyaNadella ");
        }
    }
}
