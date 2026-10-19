package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.VideoClip;

public final class Year2002 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final Rectangle emuleMascotArea = new Rectangle(322, 172, 360, 420);
    private final String picturesPath = "anniversaries27/pics/2002/";

    // Stage start frames, relative to the start of the year
    private final long dijkstraFrame = seconds(0);
    private final long algorithmVideoFrame = seconds(9);
    private final long emuleFrame = seconds(26);  // right after the 17 s video
    private final long emuleMascotFrame = seconds(28);
    private final long battlefieldFrame = seconds(31);

    private final long fadeInFrames = 15;

    private final FittedPicture dijkstra, emuleScreen, emuleMascot, battlefield;
    private final VideoClip dijkstraAlgorithm;

    Year2002(long startingFrame) {
        super("anniversaries27/2002.mp3", startingFrame);
        this.endFrame = startingFrame + 2300; // 2002.mp3 lasts 38.328 s at 60 fps

        dijkstra = new FittedPicture(picturesPath + "dijkstra.png", canvas, Color.BLACK);
        // 360x512 portrait video fitted to the canvas height: 382x543, centred horizontally
        dijkstraAlgorithm = new VideoClip("anniversaries27/video/djk.mp4", 30, 311, 112, 382, 543, false);
        emuleScreen = new FittedPicture(picturesPath + "emule-soft.jpg", canvas, Color.BLACK);
        emuleMascot = new FittedPicture(picturesPath + "emule.png", emuleMascotArea, Color.BLACK);
        battlefield = new FittedPicture(picturesPath + "battlefield.jpg", canvas, Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        sayItOnce();
        long relFrame = frame - this.startingFrame;

        if (relFrame < algorithmVideoFrame)
            drawWhole(batch, dijkstra, relFrame, dijkstraFrame);
        else if (relFrame < emuleFrame)
            drawAlgorithmVideo(batch);
        else if (relFrame < battlefieldFrame)
            drawEmule(batch, relFrame);
        else
            drawWhole(batch, battlefield, relFrame, battlefieldFrame);

        log(relFrame);
        writeYear(batch);
    }

    private void drawAlgorithmVideo(SpriteBatch batch) {
        batch.begin();
        dijkstraAlgorithm.draw(batch);
        batch.end();
    }

    /** eMule window, with the eMule mascot added over it. */
    private void drawEmule(SpriteBatch batch, long relFrame) {
        batch.begin();
        emuleScreen.drawWhole(batch, fadeIn(relFrame, emuleFrame));
        if (relFrame >= emuleMascotFrame)
            emuleMascot.drawWhole(batch, fadeIn(relFrame, emuleMascotFrame));
        batch.end();
    }

    private void drawWhole(SpriteBatch batch, FittedPicture picture, long relFrame, long appearFrame) {
        batch.begin();
        picture.drawWhole(batch, fadeIn(relFrame, appearFrame));
        batch.end();
    }

    private float fadeIn(long relFrame, long appearFrame) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) fadeInFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("Dijkstra ");
        } else if (relFrame == emuleFrame) {
            System.out.print("eMule ");
        } else if (relFrame == battlefieldFrame) {
            System.out.println("Battlefield ");
        }
    }
}
