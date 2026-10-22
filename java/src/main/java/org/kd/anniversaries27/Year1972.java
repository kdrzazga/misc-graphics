package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.VideoClip;

public final class Year1972 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);

    // Stage start frames, relative to the start of the year
    private final long yearFrames = 1032;
    private final long prologFrame = seconds(4);           // right after the 4 s Pichai video
    private final long prologTypedFrame = seconds(10);
    private final long pongFrame = yearFrames - seconds(6); // the 6 s Pong video ends the year

    private final float marseillePhotoAlpha = 0.4f; // 60% transparent
    private final float codeMargin = 20;
    private final Color terminalAmber = new Color(1f, 0.75f, 0.2f, 1);

    private final VideoClip pichai, pong;
    private final FittedPicture marseillePhoto;
    private final TypedCode prologCode;

    Year1972(long startingFrame) {
        super("anniversaries27/1972.mp3", startingFrame);
        this.endFrame = startingFrame + yearFrames; // 1972.mp3 lasts 17.184 s at 60 fps

        // 4 s and 6 s, 640x360 videos fitted to the canvas width: 797x448, centred vertically
        pichai = new VideoClip("anniversaries27/video/pichai.mp4", 30, 104, 160, 797, 448, false);
        pong = new VideoClip("anniversaries27/video/pong.mp4", 30, 104, 160, 797, 448, false);
        marseillePhoto = new FittedPicture("anniversaries27/pics/1972/Aix-Marseille.png", canvas, Color.BLACK, true);
        // Marseille Prolog I style: + heads, - goals, * variables; prints the sentence about Prolog
        prologCode = new TypedCode("anniversaries27/code/marseille.pro", 15, 22, terminalAmber,
                prologTypedFrame - prologFrame);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();

        long relFrame = frame - this.startingFrame;
        if (relFrame < prologFrame)
            drawVideo(batch, pichai);
        else if (relFrame < pongFrame)
            drawProlog(batch, relFrame);
        else
            drawVideo(batch, pong);

        log(relFrame);

        writeYear(batch);
    }

    private void drawVideo(SpriteBatch batch, VideoClip video) {
        batch.begin();
        video.draw(batch);
        batch.end();
    }

    /** Prolog code typed over the faded Aix-Marseille University photo. */
    private void drawProlog(SpriteBatch batch, long relFrame) {
        batch.begin();
        marseillePhoto.drawWhole(batch, marseillePhotoAlpha);
        batch.end();

        prologCode.draw(batch, canvas.x + codeMargin, canvas.y + canvas.height - codeMargin, relFrame - prologFrame);
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 10) {
            System.out.print("Pichai ");
        } else if (relFrame == prologFrame) {
            System.out.print("Prolog ");
        } else if (relFrame == pongFrame) {
            System.out.println("Pong ");
        }
    }

}
