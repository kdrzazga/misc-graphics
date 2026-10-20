package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.C64Helper;

import java.util.List;

final class AcronymAnimation {

    private final List<String> words;
    private final Rectangle area;
    private final BitmapFont font;
    private final GlyphLayout layout = new GlyphLayout();

    private final long fadeInFrames = 30;
    private final long moveStartFrame = 90;
    private final long moveFrames = 120;
    private final long typingStartFrame = 240;
    private final long framesPerTypedLetter = 15;

    private final float leftMargin = 30;
    private final float rowHeight = 160;
    private final Color letterColor = new Color(1f, 0.85f, 0.2f, 1);
    private final Color typedColor = new Color(1f, 1f, 1f, 1);

    AcronymAnimation(List<String> words, Rectangle area) {
        this.words = words;
        this.area = area;
        this.font = C64Helper.createFont(120, "Helvetica Regular.otf");
    }

    void draw(SpriteBatch batch, long animationFrame) {
        float alpha = Math.min(1f, animationFrame / (float) fadeInFrames);
        float moveProgress = Math.max(0f, Math.min(1f, (animationFrame - moveStartFrame) / (float) moveFrames));
        float movedFraction = Interpolation.smooth.apply(moveProgress);
        long typedLetters = Math.max(0, (animationFrame - typingStartFrame) / framesPerTypedLetter);

        float capHeight = font.getCapHeight();
        float centreY = area.y + area.height / 2;
        float rowCentreTop = centreY + capHeight / 2;
        float columnTop = centreY + ((words.size() - 1) * rowHeight + capHeight) / 2;
        float rowStartX = area.x + (area.width - textWidth(spacedLetters())) / 2;

        batch.begin();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String letter = word.substring(0, 1);

            // where this letter sits inside "A V I": width up to and including it, minus its own width
            float rowX = rowStartX + textWidth(spacedLetters().substring(0, 2 * i + 1)) - textWidth(letter);
            float columnX = area.x + leftMargin;
            float columnY = columnTop - i * rowHeight;
            float x = rowX + (columnX - rowX) * movedFraction+ 6;
            float y = rowCentreTop + (columnY - rowCentreTop) * movedFraction;

            font.setColor(letterColor.r, letterColor.g, letterColor.b, alpha);
            font.draw(batch, letter, x, y);

            String rest = word.substring(1);
            int visibleLetters = (int) Math.min(rest.length(), typedLetters);
            typedLetters -= visibleLetters;
            if (visibleLetters > 0) {
                font.setColor(typedColor);
                font.draw(batch, rest.substring(0, visibleLetters), x + textWidth(letter), y);
            }
        }
        batch.end();
    }

    /** "A V I" for the words Audio, Video, Interleave. */
    private String spacedLetters() {
        var letters = new StringBuilder();
        for (String word : words)
            letters.append(word.charAt(0)).append(' ');
        return letters.toString().trim();
    }

    private float textWidth(String text) {
        layout.setText(font, text);
        return layout.width;
    }
}
