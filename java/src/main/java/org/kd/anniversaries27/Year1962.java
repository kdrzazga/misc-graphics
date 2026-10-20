package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.VideoClip;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Year1962 extends Year {

    private final VideoClip krishnaInterview;
    private final long simulaFrame = 360; // right after the 6 s video, at the "Simula" part of the speech

    private final List<String> simulaCode = List.of("BEGIN",
            "INTEGER year;",
            "year := 2027;",
            "OutText(\"Anniversaries Demo \", 1);",
            "OutInt(year, 4);",
            "END");

    Year1962(long startingFrame) {
        super("anniversaries27/1962.mp3", startingFrame);
        this.endFrame = startingFrame + 2623; // 1962.mp3 lasts 43.704 s at 60 fps

        // 640x360 video fitted to the canvas width: 797x448, centred vertically
        krishnaInterview = new VideoClip("anniversaries27/video/krishna.mp4", 30, 104, 160, 797, 448, false);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - startingFrame;
        sayItOnce();

        batch.begin();
        if (relFrame < simulaFrame) {
            krishnaInterview.draw(batch);
        } else {
            var Y = new AtomicInteger(600);
            simulaCode.forEach(line -> {
                font1.draw(batch, line, 150, Y.floatValue());
                Y.addAndGet(Math.round(-font1.getCapHeight()) - 5);
            });
        }
        batch.end();

        log(relFrame);
        writeYear(batch);
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
