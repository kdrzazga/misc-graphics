package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.kd.common.C64Helper;

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

    private void log(long relFrame) {
        //System.out.println(relFrame);

        if (relFrame == 1) {
            System.out.print("IBM-701 ");
        } else if (relFrame == besmFrame) {
            System.out.println("BESM");
        }
    }
}
