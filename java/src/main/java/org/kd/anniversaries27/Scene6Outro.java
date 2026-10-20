package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.C64Helper;
import org.kd.common.Helper;
import org.kd.common.tricks.WavedEdgeTrick;
import org.kd.win311.Scene4Paintbrush;

import java.util.Arrays;
import java.util.List;

public class Scene6Outro extends Scene4Paintbrush {

    public final static String ID = "outro";

    // Credits are typed once the waves have covered the canvas, then stay before the demo ends
    private final long creditsTypingFrames = 300; // 5 s
    private final long creditsHoldFrames = 240;   // 4 s
    private final float creditsMargin = 15;
    private final float creditsLineSpacing = 22;
    private final int creditsFontSize = 16;

    private long startFrame;
    private long creditsStartFrame = -1; // not started yet
    private TypedCode credits;

    public Scene6Outro() {
        super(ID);
    }

    @Override
    public void create() {
        super.create();
        this.startFrame = Gdx.graphics.getFrameId();
        this.shapeRenderer = new ShapeRenderer();

        int x1 = 104;//ok
        int y1 = 109;
        int x2 = 901;//ok
        int y2 = 657;
        this.wavedEdgeTrick = new WavedEdgeTrick(x1, y1, x2, y2, batch, this.shapeRenderer, 20, 0);
        this.wavedEdgeTrick.setInitialFrame(startFrame);

        credits = new TypedCode(Credits.lines, createCreditsFont(x2 - x1 - 2 * creditsMargin),
                creditsLineSpacing, Color.WHITE, creditsTypingFrames);
    }

    /** Helvetica, scaled down if needed so that the longest credits line fits the width. */
    private BitmapFont createCreditsFont(float availableWidth) {
        var creditsFont = C64Helper.createFont(creditsFontSize, "Helvetica Regular.otf");
        var layout = new GlyphLayout();
        float longestLine = 0;
        for (String line : Credits.lines) {
            layout.setText(creditsFont, line);
            longestLine = Math.max(longestLine, layout.width);
        }
        if (longestLine > availableWidth)
            creditsFont.getData().setScale(availableWidth / longestLine);
        return creditsFont;
    }

    @Override
    public void update(float delta) {
        this.wavedEdgeTrick.update();
    }

    @Override
    public void render() {
        super.drawBackground();
        this.wavedEdgeTrick.drawGradientRectangle();

        var Y = Gdx.graphics.getHeight() - 320;
        int X = 300;
        int diff = 40;
        batch.begin();
        font.draw(batch, "  Thank you for watching!", X, Y);
        font.draw(batch, "Anniversaries Demo 2027", X + 10, Y - diff * 2);
        batch.end();

        this.wavedEdgeTrick.drawEdgeWaves();

        if (creditsStartFrame < 0 && wavedEdgeTrick.coversArea())
            creditsStartFrame = getRelFrame();
        if (creditsStartFrame >= 0)
            credits.draw(batch, 104 + creditsMargin, 657 - creditsMargin, getRelFrame() - creditsStartFrame);

        if (creditsStartFrame >= 0 && getRelFrame() > creditsStartFrame + creditsTypingFrames + creditsHoldFrames) {
            System.out.println("\nTHANK YOU FOR WATCHING !\n");
            System.out.println("End demo " + Helper.countElapsedTime() + " [" + Gdx.graphics.getFrameId() + "]");
            Gdx.app.exit();
        }
    }

    @Override
    protected long getRelFrame() {
        return Gdx.graphics.getFrameId() - startFrame;
    }


    class Credits{
        final static List<String> lines = Arrays.asList(
                "CODE: KD of K&A+",
                "MUSIC: RAMOS of K&A+",
                "",
                "The sole purpose of this demo is to present/teach the history of IT in an engaging, entertaining way.",
                "",
                "Media in this demo come from Wikimedia Commons",
                " and are used under their respective licences.",
                "A few animations related to the year 2022 were created with AI (https://deepai.org/).",
                "Gameplay footage from Mortal Kombat, GTA, Dizzy and Tibia is shown",
                " as short excerpts for the purpose of presenting the history of gaming.",
                "Short excerpts of articles from Bloomberg, The New York Times and Forbes",
                " are quoted to illustrate historical events.",
                "All rights belong to their respective owners.",
                "This demo is not affiliated with or endorsed by any game publisher or media outlet,",
                " including Codemasters, Midway, CipSoft, DMA Design, Bloomberg,",
                " The New York Times, Forbes, or their current rights holders.",
                "",
                "The whole demo was written in Java 24.",
                "Spoken phrases were generated with https://read-aloud.com/ free tool.",
                "",
                "Hope you liked it.....",
                "...and promise to return next year with another portion of anniversary events."
        );
    }
}
