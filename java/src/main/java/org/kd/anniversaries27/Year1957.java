package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Year1957 extends Year {

    private final List<String> fortranCode = List.of("program AnniversariesDemo", "  implicit none"
            , "  print *, \"Anniversaries Demo 2027\"", "end program AnniversariesDemo");
    private final Sprite punchCard, vonNeuman;
    private float vonNeumanTransparency = 0f;

    Year1957(long startingFrame) {
        super("anniversaries27/1957.mp3", startingFrame);
        this.endFrame = 29900 + 420 + 2580 + 28 * 60;//37300;

        var punchCardTexture = new Texture("anniversaries27/pics/1957/punch-card.jpg");
        punchCard = new Sprite(punchCardTexture);
        punchCard.setPosition(100, 300);
        punchCard.setScale(0.3f, 0.3f);

        var vonNeumanTexture = new Texture("anniversaries27/pics/1957/john-von-neuman.png");
        vonNeuman = new Sprite(vonNeumanTexture);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var fr = getEndFrame() - frame;

        sayItOnce();

        batch.begin();
        var Y = new AtomicInteger(500);
        if (fr < 510) {
            fortranCode.forEach(line -> {
                font1.draw(batch, line, 150, Y.floatValue());
                Y.addAndGet(Math.round(-font1.getCapHeight()) - 5);
            });

            punchCard.draw(batch);
        } else {
            vonNeuman.draw(batch);
            vonNeuman.setAlpha(vonNeumanTransparency);
            vonNeumanTransparency = Math.min(vonNeumanTransparency + 0.01f, 0f);
        }
        batch.end();

        log(fr);
        writeYear(batch);
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 1) {// wrong values
            System.out.println("von Neuman");
        } else if (relFrame == 510) {
            System.out.print("Fortran ");// wrong values
        }
    }
}
