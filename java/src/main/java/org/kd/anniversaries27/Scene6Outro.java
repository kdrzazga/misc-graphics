package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Helper;
import org.kd.common.tricks.WavedEdgeTrick;
import org.kd.win311.Scene4Paintbrush;

public class Scene6Outro extends Scene4Paintbrush {

    public final static String ID = "outro";

    private final long durationFrames;
    private long startFrame;

    public Scene6Outro(long durationFrames) {
        super(ID);
        this.durationFrames = durationFrames;
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

        if (getRelFrame() > durationFrames) {
            System.out.println("\nTHANK YOU FOR WATCHING !\n");
            System.out.println("End demo " + Helper.countElapsedTime() + " [" + Gdx.graphics.getFrameId() + "]");
            Gdx.app.exit();
        }
    }

    @Override
    protected long getRelFrame() {
        return Gdx.graphics.getFrameId() - startFrame;
    }
}
