package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public final class Scene3ProgramMgr extends Scene {
    public final static int START_FRAME = Scene2WinLoad.START_FRAME + 150;
    public final static String ID = "prgmgr";

    private Texture programMgrW;
    private Texture mainW;
    private Texture accesoriesW;
    private Batch batch;

    public Scene3ProgramMgr() {
        super(ID);
    }

    @Override
    public void create() {
        this.programMgrW = new Texture("win311/prgMgrWindow.png");
        this.mainW = new Texture("win311/WindowMain.png");
        this.accesoriesW = new Texture("win311/accessoriesWindow.png");
        batch = new SpriteBatch();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (this.getRelFrame() > 50)
            batch.draw(programMgrW, 60, 90);
        if (this.getRelFrame() > 120)
            batch.draw(mainW, 62, 80);
        if (this.getRelFrame() > 220)
            batch.draw(accesoriesW, 82, 70);
        batch.end();
    }

    @Override
    public void dispose() {

    }

    private long getRelFrame() {
        var frame = Gdx.graphics.getFrameId();
        return frame - Scene3ProgramMgr.START_FRAME;
    }
}
