package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Year1962 extends Year {

    private final Sprite arvindKrishna;

    private final List<String> simulaCode = List.of("BEGIN",
            "INTEGER year;",
            "year := 2027;",
            "OutText(\"Anniversaries Demo \", 1);",
            "OutInt(year, 4);",
            "END");

    Year1962(long startingFrame) {
        super("anniversaries27/1962.mp3", startingFrame);
        this.endFrame = startingFrame + 2623; // 1962.mp3 lasts 43.704 s at 60 fps

        var arvindKrishnaTexture = new Texture("anniversaries27/pics/1962/ArvindKrishna.jpg");
        arvindKrishna = new Sprite(arvindKrishnaTexture);
        arvindKrishna.setPosition(0, 0);
        arvindKrishna.setScale(0.65f, .65f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - startingFrame;
        sayItOnce();

        batch.begin();
        var Y = new AtomicInteger(600);
        if (relFrame > 2080) {
            simulaCode.forEach(line -> {
                font1.draw(batch, line, 150, Y.floatValue());
                Y.addAndGet(Math.round(-font1.getCapHeight()) - 5);
            });
        } else arvindKrishna.draw(batch);
        batch.end();

        log(relFrame);
        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 2579) {
            System.out.println("ArvindKrishna ");
        } else if (relFrame == 410) {
            System.out.print("Simula ");
        } else if (relFrame == 710) {
            System.out.print("Fortran4 ");
        } else if (relFrame == 930) {
            System.out.print("Ural+Minsk ");
        }
    }
}
