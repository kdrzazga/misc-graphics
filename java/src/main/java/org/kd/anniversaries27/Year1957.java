package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.VideoClip;

public final class Year1957 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);
    private final Rectangle videoArea = new Rectangle(140, 112, 724, 543);
    private final String picturesPath = "anniversaries27/pics/1957/";

    // Stage start frames, relative to the start of the year. Each matches a pause in 1957.mp3.
    private final long architectureFrame = seconds(6);
    private final long architectureEndFrame = seconds(14.25);
    private final long neumannPortraitFrame = seconds(14.85); // right after the video, which starts 0.75 s into the interview
    private final long automataFrame = seconds(20.7);
    private final long punchCardFrame = seconds(24);

    private final long fadeInFrames = 15;
    private final long architectureFadeFrames = seconds(1);
    private final float architectureOpacity = 0.6f;

    private final VideoClip neumannInterview;
    private final FittedPicture architecture, neumannPortrait, automata, punchCard;

    Year1957(long startingFrame) {
        super("anniversaries27/1957.mp3", startingFrame);
        this.endFrame = startingFrame + 1692; // 1957.mp3 lasts 28.200 s at 60 fps

        // 480x360 video fitted to the canvas height: 724x543, centred horizontally
        neumannInterview = new VideoClip("anniversaries27/video/neumann.mp4", 30,
                (int) videoArea.x, (int) videoArea.y, (int) videoArea.width, (int) videoArea.height, false);
        architecture = new FittedPicture(picturesPath + "neumann-arch.jpg", videoArea, Color.BLACK);
        neumannPortrait = new FittedPicture(picturesPath + "neumann.png", canvas, Color.BLACK, true);
        automata = new FittedPicture(picturesPath + "automata.jpg", canvas, Color.BLACK, true);
        punchCard = new FittedPicture(picturesPath + "punch-card.png", canvas, Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - startingFrame;
        sayItOnce();

        batch.begin();
        if (relFrame < neumannPortraitFrame) {
            neumannInterview.draw(batch);
            if (architectureFrame <= relFrame && relFrame < architectureEndFrame)
                architecture.drawWhole(batch, architectureAlpha(relFrame));
        }
        else if (relFrame < automataFrame)
            neumannPortrait.drawWhole(batch, fadeIn(relFrame, neumannPortraitFrame));
        else if (relFrame < punchCardFrame)
            automata.drawWhole(batch, fadeIn(relFrame, automataFrame));
        else
            punchCard.drawWhole(batch, fadeIn(relFrame, punchCardFrame));
        batch.end();

        log(relFrame);
        writeYear(batch);
    }

    /** The von Neumann architecture diagram fades in over the video, stays partly transparent, then fades out. */
    private float architectureAlpha(long relFrame) {
        float fadingIn = (relFrame - architectureFrame) / (float) architectureFadeFrames;
        float fadingOut = (architectureEndFrame - relFrame) / (float) architectureFadeFrames;
        return architectureOpacity * Math.min(1f, Math.min(fadingIn, fadingOut));
    }

    private float fadeIn(long relFrame, long appearFrame) {
        return Math.max(0f, Math.min(1f, (relFrame - appearFrame) / (float) fadeInFrames));
    }

    private static long seconds(double seconds) {
        return Math.round(seconds * 60);
    }

    private void log(long relFrame) {
        if (relFrame == 1) {
            System.out.print("von Neumann ");
        } else if (relFrame == punchCardFrame) {
            System.out.println("Fortran");
        }
    }
}
