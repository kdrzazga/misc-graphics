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
        this.endFrame = 29900 + 420 + 2580;//33900;

        var arvindKrishnaTexture = new Texture("anniversaries27/pics/1962/ArvindKrishna.jpg");
        arvindKrishna = new Sprite(arvindKrishnaTexture);
        arvindKrishna.setPosition(0, 55);
        arvindKrishna.setScale(0.75f, .75f);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var fr = getEndFrame() - frame;
        sayItOnce();

        batch.begin();
        var Y = new AtomicInteger(300);
        if (fr < 500) {
            simulaCode.forEach(line -> {
                font1.draw(batch, line, 150, Y.floatValue());
                Y.addAndGet(Math.round(-font1.getCapHeight()) - 5);
            });
        } else arvindKrishna.draw(batch);
        batch.end();

        writeYear(batch);
    }
}
