package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.C64Colors;
import org.kd.common.C64Helper;
import org.kd.common.TypedCode;

import java.util.ArrayList;
import java.util.List;

public final class Year1952 extends Year {

    private final Rectangle canvas = new Rectangle(104, 112, 797, 543);

    private final long besmFrame = 500;

    // Facts typed over each photo, on a translucent band along the bottom of the photo
    private final long ibm701FactsFrame = 30;
    private final long ibm701FactsTypingFrames = 360;
    private final float ibm701FactsBandHeight = 160;
    private final long besmFactsFrame = besmFrame + 30;
    private final long besmFactsTypingFrames = 180;
    private final float besmFactsBandHeight = 96;
    private final float factsBandOpacity = 0.6f;
    private final float factsMargin = 24;
    private final int factsFontSize = 22;
    private final float factsLineSpacing = 32;

    private final Sprite besm, ibm701;
    private final TypedCode ibm701Facts, besmFacts;
    private final CaptionMorph besmName;

    Year1952(long startingFrame) {
        super("anniversaries27/1952.mp3", startingFrame);
        this.endFrame = startingFrame + 2135; // 1952.mp3 lasts 35.568 s at 60 fps

        var besmTexture = new Texture("anniversaries27/pics/1952/BESM-1-computer-on-the-ground-floor-of-IPMCE-building-in-Moscow-251888155.jpg");
        besm = new Sprite(besmTexture);
        //besm.setScale(1.7f);
        besm.setPosition(104, 109);
        besmFacts = new TypedCode(List.of(
                "BESM-I a.k.a. BESM_AN:",
                "~5'000 vacuum tubes"),
                C64Helper.createFont(factsFontSize, "Helvetica Regular.otf"), factsLineSpacing, Color.WHITE,
                besmFactsTypingFrames);
        // runs through the first half of the BESM narration
        besmName = new CaptionMorph(List.of(
                "BESM",
                "БЭСМ",
                "Большая Электронно-Счётная Машина",
                "Big Electronic Computing Machine"),
                (endFrame - startingFrame - besmFrame) / 2);

        ibm701 = new Sprite(new Texture("anniversaries27/pics/1952/IBM701.jpg"));
        fitToCanvas(ibm701);
        ibm701Facts = new TypedCode(List.of(
                "IBM 701:",
                "4000 vacuum tubes",
                "~16,000 additions or ~2,000 multiplications per second",
                "36-bit words (or 18-bit half-words)"),
                C64Helper.createFont(factsFontSize, "Helvetica Regular.otf"), factsLineSpacing, Color.WHITE,
                ibm701FactsTypingFrames);
    }

    @Override
    public void draw(long frame, SpriteBatch batch) {
        var relFrame = frame - startingFrame;
        var pic = (0 < relFrame && relFrame < besmFrame) ? ibm701 : besm;

        batch.begin();
        pic.draw(batch);
        batch.end();
        if (pic == ibm701 && relFrame >= ibm701FactsFrame)
            drawFacts(batch, ibm701, ibm701Facts, ibm701FactsBandHeight, relFrame - ibm701FactsFrame);
        if (pic == besm && relFrame >= besmFactsFrame)
            drawFacts(batch, besm, besmFacts, besmFactsBandHeight, relFrame - besmFactsFrame);
        if (pic == besm)
            besmName.draw(batch, relFrame - besmFrame);
        sayItOnce();

        log(relFrame);
        writeYear(batch);
    }

    private void drawFacts(SpriteBatch batch, Sprite photo, TypedCode facts, float bandHeight, long typingFrame) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, factsBandOpacity);
        shapeRenderer.rect(photo.getX(), photo.getY(), photo.getWidth(), bandHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        // shifted right past the year label in the bottom-left corner
        float left = photo.getX() + factsMargin + 1.2f * yearLabelWidth;
        facts.draw(batch, left, photo.getY() + bandHeight - factsMargin, typingFrame);
    }

    /** Scales the sprite to fit inside the canvas, keeping proportions, and centres it. */
    private void fitToCanvas(Sprite sprite) {
        float scale = Math.min(canvas.width / sprite.getWidth(), canvas.height / sprite.getHeight());
        float width = sprite.getWidth() * scale;
        float height = sprite.getHeight() * scale;
        sprite.setBounds(canvas.x + (canvas.width - width) / 2, canvas.y + (canvas.height - height) / 2, width, height);
    }

    /**
     * A caption centred on the canvas that turns into each next string in turn. Consecutive strings share their
     * anchor letters (all letters of a single word, else the initials of the words, hyphenated parts included:
     * B E S M, Б Э С М, ...). Anchors glide and resize from their places in one string to their places in the
     * next, cross-fading where the letter changes; all other letters fade out and the new ones fade in.
     * Times are frames since the animation started.
     */
    private final class CaptionMorph {

        private final long fadeFrames = 30;
        private final long morphFrames = 90;
        private final long totalFrames, holdFrames;

        private final float sideMargin = 30;
        private final float bandPadding = 24;
        private final Color anchorColor = new Color(1f, 0.85f, 0.2f, 1);
        private final Color otherColor = new Color(Color.WHITE);

        private final BitmapFont font;
        private final GlyphLayout layout = new GlyphLayout();
        private final List<Caption> captions;

        /** One string laid out centred on the canvas: where each letter sits and how much the font is scaled. */
        private record Caption(String text, float[] letterX, float baselineY, float scale, List<Integer> anchors) {
        }

        CaptionMorph(List<String> texts, long totalFrames) {
            this.totalFrames = totalFrames;
            this.holdFrames = (totalFrames - (texts.size() - 1) * morphFrames) / texts.size();

            var characters = FreeTypeFontGenerator.DEFAULT_CHARS + String.join("", texts);
            font = C64Helper.createFont(96, C64Colors.WHITE, "Liberation Sans Regular.ttf", characters);
            font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

            captions = texts.stream().map(this::layOut).toList();
            for (int i = 1; i < captions.size(); i++)
                if (captions.get(i).anchors().size() != captions.get(i - 1).anchors().size())
                    throw new IllegalArgumentException("Different number of anchor letters in: " + texts);
        }

        void draw(SpriteBatch batch, long animationFrame) {
            if (animationFrame < 0 || animationFrame >= totalFrames)
                return;
            float shown = Math.min(progress(animationFrame, 0, fadeFrames),
                    1 - progress(animationFrame, totalFrames - fadeFrames, fadeFrames));
            int current = (int) Math.min(captions.size() - 1, animationFrame / (holdFrames + morphFrames));
            var from = captions.get(current);
            var to = captions.get(Math.min(current + 1, captions.size() - 1));
            float morphed = progress(animationFrame, current * (holdFrames + morphFrames) + holdFrames, morphFrames);
            float moved = Interpolation.smooth.apply(morphed);

            drawBand(lerp(from.scale(), to.scale(), moved), shown);

            batch.begin();
            // letters that are not anchors: the old ones leave during the first half, the new ones come in the second
            drawOthers(batch, from, shown * Math.max(0, 1 - 2 * morphed));
            drawOthers(batch, to, shown * Math.max(0, 2 * morphed - 1));
            for (int i = 0; i < from.anchors().size(); i++) {
                int fromLetter = from.anchors().get(i), toLetter = to.anchors().get(i);
                float x = lerp(from.letterX()[fromLetter], to.letterX()[toLetter], moved);
                float y = lerp(from.baselineY(), to.baselineY(), moved);
                float scale = lerp(from.scale(), to.scale(), moved);
                char oldLetter = from.text().charAt(fromLetter), newLetter = to.text().charAt(toLetter);
                if (oldLetter == newLetter) {
                    drawLetter(batch, oldLetter, x, y, scale, anchorColor, shown);
                } else {
                    drawLetter(batch, oldLetter, x, y, scale, anchorColor, shown * (1 - morphed));
                    drawLetter(batch, newLetter, x, y, scale, anchorColor, shown * morphed);
                }
            }
            font.getData().setScale(1);
            batch.end();
        }

        /** Darkens a strip across the photo behind the caption, so it stays readable. */
        private void drawBand(float scale, float shown) {
            float height = scale * font.getCapHeight() + 2 * bandPadding;
            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, factsBandOpacity * shown);
            shapeRenderer.rect(canvas.x, canvas.y + (canvas.height - height) / 2, canvas.width, height);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }

        private void drawOthers(SpriteBatch batch, Caption caption, float alpha) {
            for (int i = 0; i < caption.text().length(); i++)
                if (!caption.anchors().contains(i))
                    drawLetter(batch, caption.text().charAt(i), caption.letterX()[i], caption.baselineY(),
                            caption.scale(), otherColor, alpha);
        }

        private void drawLetter(SpriteBatch batch, char letter, float x, float y, float scale, Color color, float alpha) {
            if (alpha <= 0 || letter == ' ')
                return;
            font.getData().setScale(scale);
            font.setColor(color.r, color.g, color.b, alpha);
            font.draw(batch, String.valueOf(letter), x, y);
        }

        private Caption layOut(String text) {
            float naturalWidth = textWidth(text);
            float scale = Math.min(1f, (canvas.width - 2 * sideMargin) / naturalWidth);
            float left = canvas.x + (canvas.width - scale * naturalWidth) / 2;
            float[] letterX = new float[text.length()];
            for (int i = 0; i < text.length(); i++)
                // width up to and including this letter, minus its own width
                letterX[i] = left + scale * (textWidth(text.substring(0, i + 1)) - textWidth(text.substring(i, i + 1)));
            float baselineY = canvas.y + canvas.height / 2 + scale * font.getCapHeight() / 2;
            return new Caption(text, letterX, baselineY, scale, anchorsOf(text));
        }

        /** Every letter of a single word, otherwise the first letter of each word and of each hyphenated part. */
        private List<Integer> anchorsOf(String text) {
            boolean singleWord = !text.contains(" ") && !text.contains("-");
            var anchors = new ArrayList<Integer>();
            for (int i = 0; i < text.length(); i++)
                if (singleWord || i == 0 || text.charAt(i - 1) == ' ' || text.charAt(i - 1) == '-')
                    anchors.add(i);
            return anchors;
        }

        private float textWidth(String text) {
            layout.setText(font, text);
            return layout.width;
        }

        private float progress(long animationFrame, long startFrame, long durationFrames) {
            return Math.max(0f, Math.min(1f, (animationFrame - startFrame) / (float) durationFrames));
        }

        private float lerp(float from, float to, float fraction) {
            return from + (to - from) * fraction;
        }
    }

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 1) {
            System.out.print("IBM-701 ");
        } else if (relFrame == besmFrame) {
            System.out.println("BESM");
        }
    }
}
