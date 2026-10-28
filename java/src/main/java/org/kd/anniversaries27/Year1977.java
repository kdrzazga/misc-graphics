package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.VideoClip;

public final class Year1977 extends Year {

    // Stage start frames, relative to the start of the year
    private final long oracleFrame = 200;   // the 3.1 s Oracle video (186 frames); Nintendo cuts its last 0.1 s
    private final long nintendoFrame = 380; // the 6.1 s Nintendo video, frames 380-746, ends the year

    private final Sprite apple;
    private final VideoClip oracle, nintendo;

    Year1977(long startingFrame) {
        super("anniversaries27/1977.mp3", startingFrame);
        this.endFrame = startingFrame + 754; // 1977.mp3 lasts 12.552 s at 60 fps

        var appleTexture = new Texture("apple2.jpg");
        apple = new Sprite(appleTexture);
        apple.setPosition(346, 175);

        // 3.1 s and 6.1 s, 360x640 videos fitted to the canvas height: 305x543, centred horizontally
        oracle = new VideoClip("anniversaries27/video/77oracle_trimmed.mp4", 30, 350, 112, 305, 543, false);
        nintendo = new VideoClip("anniversaries27/video/77nintendo_trimmed.mp4", 30, 350, 112, 305, 543, false);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        batch.begin();
        if (relFrame < oracleFrame)
            apple.draw(batch);
        else if (relFrame < nintendoFrame)
            oracle.draw(batch);
        else
            nintendo.draw(batch);
        batch.end();

        log(relFrame);

        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Apple2 ");
        } else if (relFrame == oracleFrame) {
            System.out.print("Oracle ");
        } else if (relFrame == nintendoFrame) {
            System.out.println("Nintendo ");
        }
    }

}
