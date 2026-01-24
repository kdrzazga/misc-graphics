package org.kd.kickass.lib;

import com.badlogic.gdx.Gdx;
import org.kd.common.tricks.AlphabetScroll;

public class BootAlphabetScroll extends AlphabetScroll {
    public BootAlphabetScroll(String text, long initialFrame) {
        super(text, initialFrame);
        this.speed = -10;
        this.textSprites.forEach(s -> {
            s.setScale(0.8f);
            s.setY(-90f);
        });
    }

    public void update() {
        var frame = Gdx.graphics.getFrameId() - initialFrame;
        var H = Gdx.graphics.getHeight();
        var W = Gdx.graphics.getWidth();

        for (int i = 0; i < textSprites.size(); i++) {
            var s = textSprites.get(i);
            float delay = i * textSprites.get(0).getWidth() * 0.1f - 7 * speed;
            if (frame > delay) {
                if (s.getX() < startX && s.getX() > endX) {
                    var x = s.getX() + speed;
                    float y = shiftY + (float) (0.69f * H * Math.sin(Math.PI * x / startX + 0.2f));
                    if (x < 0.31 * W) {
                        var diff = 0.31f * W - x;
                        y -= diff;
                        x += 1;
                    }
                    s.setPosition(x, y);
                }
            }
        }
    }
}
