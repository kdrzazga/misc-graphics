package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.kd.common.Scene;
import org.kd.common.tricks.WavedEdgeTrick;

public class Scene4Paintbrush extends Scene {
    public final static int START_FRAME = Scene3ProgramMgr.START_FRAME + 1000;
    public final static String ID = "paintbrush";
    public final static long START_WAVE_FRAME = 125;
    private SpriteBatch batch;
    private Texture mediaPlayerW;
    private Texture paintbrushW;
    private ShapeRenderer shapeRenderer;
    private WavedEdgeTrick wavedEdgeTrick;

    public Scene4Paintbrush() {
        super(ID);
    }

    public Scene4Paintbrush(String newId) {
        super(newId);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.mediaPlayerW = new Texture("win311/MediaPlayer.png");
        this.paintbrushW = new Texture("win311/paintbrush.png");
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float delta) {
        System.out.println(this.getRelFrame());
        if (this.getRelFrame() == START_WAVE_FRAME) {
            int x1 = 104;//ok
            int y1 = 109;
            int x2 = 901;//ok
            int y2 = 657;
            this.wavedEdgeTrick = new WavedEdgeTrick(x1, y1, x2, y2, batch, this.shapeRenderer, 20, 0);
            this.wavedEdgeTrick.setInitialFrame(Gdx.graphics.getFrameId());

            System.out.println("wavedEdgeTrick created");
        }

        if (this.getRelFrame() == 1) {
            Gdx.input.setCursorPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else if (this.getRelFrame() > START_WAVE_FRAME + 10) {
            this.wavedEdgeTrick.update();
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(this.mediaPlayerW, 5, 570);
        batch.draw(this.paintbrushW, 10, 10);
        batch.end();

        if (this.getRelFrame() > START_WAVE_FRAME + 10) {
            if (this.wavedEdgeTrick != null)
                this.wavedEdgeTrick.render();
        }
    }

    private long getRelFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - Scene4Paintbrush.START_FRAME;
    }

    @Override
    public void dispose() {

    }
}
