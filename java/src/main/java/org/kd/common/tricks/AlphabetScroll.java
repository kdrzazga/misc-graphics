package org.kd.common.tricks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AlphabetScroll {

    private final List<Sprite> textSprites;
    private final float startX, endX;
    private final int speed;

    public AlphabetScroll(String text) {
        this(text, 0.81f * Gdx.graphics.getWidth(), 21f, -5);
    }

    public AlphabetScroll(String text, float startX, float endX, int speed) {
        this.textSprites = new ArrayList<>(text.length());

        List<Character> letters = Stream.of(text.split(""))
                .map(s -> s.charAt(0))
                .collect(Collectors.toList());

        letters.forEach(letter -> {
            var filename = convertToFilename(letter).toString();

            var texture = new Texture(filename);
            var sprite = new Sprite(texture);
            this.textSprites.add(sprite);
        });

        this.startX = startX;
        this.endX = endX;
        this.speed = speed;
    }

    public void update() {
        textSprites.stream()
                .filter(sprite -> sprite.getX() > startX && sprite.getX() < endX)
                .forEach(sprite -> {
                    var x = sprite.getX() + speed;
                    sprite.setX(x);
                });
    }

    public void render(SpriteBatch openBatch) {
        textSprites.forEach(sprite -> sprite.draw(openBatch));
    }

    private static StringBuilder convertToFilename(Character letter) {
        var firstChar = letter == 32 ? "space" : "" + letter;
        var filename = new StringBuilder();
        filename.append(firstChar);

        filename.insert(0, "alphabet\\");
        filename.append(".png");
        return filename;
    }

    public List<Sprite> getTextSprites() {
        return textSprites;
    }
}
