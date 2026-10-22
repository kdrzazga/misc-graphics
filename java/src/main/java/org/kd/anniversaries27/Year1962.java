package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.VideoClip;

public final class Year1962 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);

    // SIMULA source typed letter by letter during the "Simula" part of the speech, over the half-transparent
    // Norwegian Computing Center; then FORTRAN IV source, done when Ural and Minsk start
    private final long simulaFrame = 410;
    private final long fortranFrame = 710;
    private final long uralFrame = 930;
    private final long minskFrame = 1050;
    private final long fadeInFrames = 30;
    private final float codeMargin = 20;
    private final Color terminalGreen = new Color(0.35f, 1f, 0.35f, 1);

    private final VideoClip krishnaInterview;
    private final FittedPicture norskRegnesentral;
    private final TypedCode simulaCode;
    private final TypedCode fortranCode;
    private final FittedPicture ural;
    private final FittedPicture minsk;

    Year1962(long startingFrame) {
        super("anniversaries27/1962.mp3", startingFrame);
        this.endFrame = startingFrame + 2623; // 1962.mp3 lasts 43.704 s at 60 fps

        // 640x360 video fitted to the canvas width: 797x448, centred vertically
        krishnaInterview = new VideoClip("anniversaries27/video/krishna.mp4", 30, 104, 160, 797, 448, false);
        norskRegnesentral = new FittedPicture("anniversaries27/pics/1962/Norsk_regnesentral.png", canvas,
                Color.BLACK, true);
        // simula.sim is runnable and prints the sentence about SIMULA and the Norwegian Computing Center
        simulaCode = new TypedCode("anniversaries27/code/simula.sim", 15, 20, terminalGreen,
                fortranFrame - simulaFrame);
        // ibm.f is runnable and prints the sentence about FORTRAN IV and IBM
        fortranCode = new TypedCode("anniversaries27/code/ibm.f", 15, 20, Color.WHITE,
                uralFrame - fortranFrame);
        // Soviet mainframes, each 3/4 of the canvas width: Ural-4 (957x566) in the top-left corner,
        // MINSK-2 (1232x741) in the bottom-right corner, overlapping Ural in the middle
        ural = new FittedPicture("anniversaries27/pics/1962/ural.png",
                new Rectangle(canvas.x, canvas.y + canvas.height - 354, 598, 354), Color.BLACK);
        minsk = new FittedPicture("anniversaries27/pics/1962/minsk.png",
                new Rectangle(canvas.x + canvas.width - 598, canvas.y, 598, 360), Color.BLACK);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - startingFrame;
        sayItOnce();

        if (relFrame < simulaFrame) {
            batch.begin();
            krishnaInterview.draw(batch);
            batch.end();
        } else if (relFrame < fortranFrame) {
            batch.begin();
            norskRegnesentral.drawWhole(batch, 0.5f);
            batch.end();
            simulaCode.draw(batch, canvas.x + codeMargin, canvas.y + canvas.height - codeMargin, relFrame - simulaFrame);
        } else if (relFrame < uralFrame) {
            fortranCode.draw(batch, canvas.x + codeMargin, canvas.y + canvas.height - codeMargin, relFrame - fortranFrame);
        } else {
            drawMainframes(batch, relFrame);
        }

        log(relFrame);
        writeYear(batch);
    }

    /** Ural fades in, then Minsk fades in on top of it. */
    private void drawMainframes(SpriteBatch batch, long relFrame) {
        batch.begin();
        ural.drawWhole(batch, fadeIn(relFrame - uralFrame));
        if (relFrame >= minskFrame)
            minsk.drawWhole(batch, fadeIn(relFrame - minskFrame));
        batch.end();
    }

    private float fadeIn(long framesSinceStart) {
        return Math.min(1f, framesSinceStart / (float) fadeInFrames);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 1) {
            System.out.print("ArvindKrishna ");
        } else if (relFrame == 410) {
            System.out.print("Simula ");
        } else if (relFrame == 710) {
            System.out.print("Fortran4 ");
        } else if (relFrame == 930) {
            System.out.println("Ural+Minsk ");
        }
    }
}
