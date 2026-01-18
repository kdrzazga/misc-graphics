package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlphabetScroll {

    protected final List<Sprite> textSprites;
    protected final float startX, endX;
    protected int speed;
    protected final long initialFrame;
    protected float shiftY = 0;

    public AlphabetScroll(String text, long initialFrame) {
        this(text, 0.91f * Gdx.graphics.getWidth(), 21f, -15, initialFrame);
    }

    public AlphabetScroll(String text, float startX, float endX, int speed, long initialFrame) {
        this.textSprites = new ArrayList<>(text.length());
        this.initialFrame = initialFrame;

        List<Character> letters = Stream.of(text.split(""))
                .map(s -> s.charAt(0))
                .collect(Collectors.toList());

        letters.forEach(letter -> {
            var filename = convertToFilename(letter).toString();

            var texture = new Texture(filename);
            var sprite = new Sprite(texture);
            sprite.setX(speed > 0 ? startX + 1 : startX - 1);
            this.textSprites.add(sprite);

        });

        this.startX = startX;
        this.endX = endX;
        this.speed = speed;
    }

    public void update() {
        var frame = Gdx.graphics.getFrameId() - initialFrame;

        for (int i = 0; i < textSprites.size(); i++) {
            var s = textSprites.get(i);
            float delay = i * textSprites.get(0).getWidth() * 0.1f - 7 * speed;
            if (frame > delay) {
                if (s.getX() < startX && s.getX() > endX) {
                    var x = s.getX() + speed;
                    float y = shiftY + (float) (s.getHeight() * Math.sin(Math.PI * x / startX));
                    s.setPosition(x, y);
                }
            }
        }
    }

    public void render(SpriteBatch openBatch) {
        textSprites.forEach(sprite -> sprite.draw(openBatch));
    }

    public void colorize(Color color) {
        textSprites.forEach(s -> s.setColor(color));
    }

    public void scale(float scale) {
        textSprites.forEach(sprite -> sprite.setScale(scale));
    }

    private static StringBuilder convertToFilename(Character letter) {
        var firstChar = letter == 32 ? "space" : "" + letter;
        var filename = new StringBuilder();
        filename.append(firstChar);

        filename.insert(0, "alphabet\\");
        filename.append(".png");
        return filename;
    }

    public void setShiftY(float shiftY) {
        this.shiftY = shiftY;
        textSprites.forEach(s -> s.setY(shiftY));
    }
}
