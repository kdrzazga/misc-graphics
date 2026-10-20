package org.kd.anniversaries27;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.kd.anniversaries27.FittedPicture.Part;

import java.util.List;

/**
 * 8 seconds: 台積電 is written in white, turns red, morphs into "t", "sm", "c" lying exactly over
 * the letters of the TSMC logo, and the logo fades in to replace them. The logo then stays.
 * Glyphs are white images (rendered once from Microsoft JhengHei Bold and Times New Roman Bold), tinted here.
 * Times are frames since the animation started.
 */
final class TsmcLogoAnimation {

    private final String glyphsPath = "anniversaries27/pics/1987/glyphs/";

    private final long firstCharacterFrame = 0;
    private final long nextCharacterFrames = 30;
    private final long characterFadeInFrames = 18;
    private final long reddenStartFrame = 90;
    private final long reddenFrames = 90;
    private final long morphStartFrame = 180;
    private final long morphFrames = 120;
    private final long logoStartFrame = 360;
    private final long logoFrames = 120;  // the logo is complete at frame 480 = 8 s

    private final float characterSize = 220;
    private final float characterGap = 20;
    private final Color tsmcRed = new Color(0.9f, 0f, 0.07f, 1); // #E60012 from the logo

    // Letters of the logo in tsmc.png pixels: t, sm, c
    private final List<Part> logoLetters = List.of(
            new Part(4, 118, 82, 123), new Part(94, 137, 285, 105), new Part(379, 137, 117, 105));

    private final List<Texture> chineseCharacters, latinLetters;
    private final List<Rectangle> chineseRectangles, latinRectangles;
    private final FittedPicture logo;

    TsmcLogoAnimation(Rectangle canvas) {
        chineseCharacters = List.of(glyph("tai.png"), glyph("ji.png"), glyph("dian.png"));
        latinLetters = List.of(glyph("t.png"), glyph("sm.png"), glyph("c.png"));
        logo = new FittedPicture("anniversaries27/pics/1987/tsmc.png", canvas, Color.BLACK);

        float rowWidth = 3 * characterSize + 2 * characterGap;
        float rowLeft = canvas.x + (canvas.width - rowWidth) / 2;
        float rowBottom = canvas.y + (canvas.height - characterSize) / 2;
        chineseRectangles = List.of(
                new Rectangle(rowLeft, rowBottom, characterSize, characterSize),
                new Rectangle(rowLeft + characterSize + characterGap, rowBottom, characterSize, characterSize),
                new Rectangle(rowLeft + 2 * (characterSize + characterGap), rowBottom, characterSize, characterSize));
        latinRectangles = logoLetters.stream().map(logo::screenRectangleOf).toList();
    }

    void draw(SpriteBatch batch, long animationFrame) {
        float redness = progress(animationFrame, reddenStartFrame, reddenFrames);
        float morphed = Interpolation.smooth.apply(progress(animationFrame, morphStartFrame, morphFrames));
        float logoShown = progress(animationFrame, logoStartFrame, logoFrames);
        var letterColor = new Color(Color.WHITE).lerp(tsmcRed, redness);

        batch.begin();
        logo.drawWhole(batch, logoShown);
        for (int i = 0; i < chineseCharacters.size(); i++) {
            float appeared = progress(animationFrame, firstCharacterFrame + i * nextCharacterFrames, characterFadeInFrames);
            var rectangle = lerp(chineseRectangles.get(i), latinRectangles.get(i), morphed);

            drawTinted(batch, chineseCharacters.get(i), rectangle, letterColor, appeared * (1 - morphed));
            drawTinted(batch, latinLetters.get(i), rectangle, letterColor, morphed * (1 - logoShown));
        }
        batch.end();
    }

    private void drawTinted(SpriteBatch batch, Texture glyph, Rectangle rectangle, Color color, float alpha) {
        if (alpha <= 0)
            return;
        batch.setColor(color.r, color.g, color.b, alpha);
        batch.draw(glyph, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        batch.setColor(Color.WHITE);
    }

    private Rectangle lerp(Rectangle from, Rectangle to, float fraction) {
        return new Rectangle(
                from.x + (to.x - from.x) * fraction, from.y + (to.y - from.y) * fraction,
                from.width + (to.width - from.width) * fraction, from.height + (to.height - from.height) * fraction);
    }

    private float progress(long animationFrame, long startFrame, long durationFrames) {
        return Math.max(0f, Math.min(1f, (animationFrame - startFrame) / (float) durationFrames));
    }

    private Texture glyph(String fileName) {
        var texture = new Texture(glyphsPath + fileName);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }
}
