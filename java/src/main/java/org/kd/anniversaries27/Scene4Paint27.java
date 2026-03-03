package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import org.kd.win311.Scene4Paintbrush;

public class Scene4Paint27 extends Scene4Paintbrush {

    private final Music statementWelcome, statementInfo;

    public Scene4Paint27() {
        super();
        statementWelcome = Gdx.audio.newMusic(Gdx.files.internal("anniversaries27/01welcome.mp3"));
        statementInfo = Gdx.audio.newMusic(Gdx.files.internal("anniversaries27/02intro.mp3"));
    }

    @Override
    public void render() {
        super.drawBackground();
        if (this.getRelFrame() > START_WAVE_FRAME + 10) {
            if (this.wavedEdgeTrick != null)
                this.wavedEdgeTrick.drawGradientRectangle();
        }


        if (this.getRelFrame() == START_WAVE_FRAME + 30) {
            statementWelcome.play();
        } else if (this.getRelFrame() == START_WAVE_FRAME + 230) {
            statementInfo.play();
        }

        if (this.getRelFrame() > START_WAVE_FRAME + 30) {
            var Y = Gdx.graphics.getHeight();
            batch.begin();
            font.draw(batch, "Welcome to Anniversaries Demo.", 200, Y - 200);
            batch.end();
        }

        if (this.getRelFrame() > START_WAVE_FRAME + 230) {
            var Y = Gdx.graphics.getHeight() - 170;

            batch.begin();
            int X = 260;
            int diff = 40;
            font.draw(batch, "     It's already 2027!", X, Y - 150);
            font.draw(batch, "Let's look back at what happened", X - 30, Y - 150 - diff);
            font.draw(batch, "  in the world of computers", X, Y - 150 - diff*2);
            font.draw(batch, "    5, 10, 15 years ago...", X, Y - 150 - diff*3);
            font.draw(batch, "        ..and beyond.", X, Y -  150 - diff*4);
            batch.end();
        }

        if (this.getRelFrame() > START_WAVE_FRAME + 10) {
            if (this.wavedEdgeTrick != null)
                this.wavedEdgeTrick.drawEdgeWaves();
        }
    }
}
