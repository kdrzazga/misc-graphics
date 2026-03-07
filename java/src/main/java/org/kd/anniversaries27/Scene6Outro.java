package org.kd.anniversaries27;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Helper;
import org.kd.common.tricks.WavedEdgeTrick;
import org.kd.win311.Scene4Paintbrush;

public class Scene6Outro extends Scene4Paintbrush {

    public final static int START_FRAME = Scene5YearBrowser.START_FRAME + 34180;
    public final static String ID = "outro";

    @Override
    public void create() {
        super.create();
        this.shapeRenderer = new ShapeRenderer();

        int x1 = 104;//ok
        int y1 = 109;
        int x2 = 901;//ok
        int y2 = 657;
        this.wavedEdgeTrick = new WavedEdgeTrick(x1, y1, x2, y2, batch, this.shapeRenderer, 20, 0);
    }

    @Override
    public void render() {
        super.drawBackground();

        var fr = Gdx.graphics.getFrameId();
        if (fr > 38000) {
            System.out.println("\nTHANK YOU FOR WATCHING !\n");
            System.out.println("End demo " + Helper.countElapsedTime() + " [" + Gdx.graphics.getFrameId() + "]");
            Gdx.app.exit();
        }
    }
}
