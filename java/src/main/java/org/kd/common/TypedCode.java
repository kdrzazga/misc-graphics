package org.kd.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Helper;

import java.util.List;

/**
 * Lines of text (e.g. source code read from a resource file) typed letter by letter, with a cursor
 * that blinks once the typing is done. Times are frames since the typing started.
 */
public final class TypedCode {

    private final List<String> lines;
    private final int totalCharacters;
    private final BitmapFont font;
    private final Color color;
    private final float lineSpacing;
    private final long typingFrames;
    private final long cursorBlinkFrames = 30;
    private final GlyphLayout layout = new GlyphLayout();

    /** Text resource typed in the monospaced C64 font. */
    public TypedCode(String internalPath, int fontSize, float lineSpacing, Color color, long typingFrames) {
        this(Gdx.files.internal(internalPath).readString("UTF-8").replace("\r", "").lines().toList(),
                C64Helper.createFont(fontSize, "C64_Pro_Mono-STYLE.ttf"), lineSpacing, color, typingFrames);
    }

    public TypedCode(List<String> lines, BitmapFont font, float lineSpacing, Color color, long typingFrames) {
        this.lines = lines;
        this.totalCharacters = lines.stream().mapToInt(line -> line.length() + 1).sum(); // + 1 for each line break
        this.font = font;
        this.lineSpacing = lineSpacing;
        this.color = color;
        this.typingFrames = typingFrames;
    }

    /** x, top: where the first line starts. */
    public void draw(SpriteBatch batch, float x, float top, long typingFrame) {
        long charactersLeft = Math.min(totalCharacters, totalCharacters * Math.max(0, typingFrame) / typingFrames);
        boolean typingDone = charactersLeft >= totalCharacters;
        boolean cursorShown = !typingDone || (typingFrame / cursorBlinkFrames) % 2 == 0;

        font.setColor(color);
        batch.begin();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int visible = (int) Math.min(line.length(), charactersLeft);
            charactersLeft -= Math.min(line.length() + 1, charactersLeft);
            boolean cursorOnThisLine = charactersLeft == 0; // the first line typing stopped in

            float y = top - i * lineSpacing;
            var typed = line.substring(0, visible);
            font.draw(batch, typed, x, y);
            if (cursorShown && cursorOnThisLine) {
                layout.setText(font, typed);
                font.draw(batch, "_", x + layout.width, y);
                cursorShown = false;
            }
        }
        batch.end();
    }
}
