package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Vector2;
import org.kd.common.Helper;
import org.kd.common.Scene;

public final class Scene3ProgramMgr extends Scene {
    public final static int START_FRAME = Scene2WinLoad.START_FRAME + 150;
    public final static int PLAY_MUSIC_FRAME = START_FRAME + 300;
    public final static String ID = "prgmgr";

    private Texture programMgrW;
    private Texture mainW;
    private Texture accesoriesW;
    private Texture mediaPlayerW;
    private Texture paintbrushW;
    private Texture musicFileW;
    private Batch batch;

    public Scene3ProgramMgr() {
        super(ID);
    }

    @Override
    public void create() {
        this.programMgrW = new Texture("win311/prgMgrWindow.png");
        this.mainW = new Texture("win311/WindowMain.png");
        this.accesoriesW = new Texture("win311/accessoriesWindow.png");
        this.mediaPlayerW = new Texture("win311/MediaPlayer.png");
        this.paintbrushW = new Texture("win311/paintbrush.png");
        this.musicFileW = new Texture("win311/musicFile.png");
        batch = new SpriteBatch();
    }

    @Override
    public void update(float delta) {
        if (this.getRelFrame() == 1) {
            Gdx.input.setCursorPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Ibeam);
        }

        if (this.getRelFrame() == 10 ){
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
        }

        if (this.getRelFrame() > 50 && this.getRelFrame() <198){
            var x1 = Gdx.graphics.getWidth() / 2;
            var y1 = Gdx.graphics.getHeight() / 2;
            var y2 = Gdx.graphics.getHeight() - 80;
            Helper.moveCursor(new Vector2(x1, y1), new Vector2(10,y2),50, getRelFrame());
        }
        if (this.getRelFrame() > 300 && this.getRelFrame() < 450){
            var y1 = Gdx.graphics.getHeight() - 80;
            Helper.moveCursor(new Vector2(10,y1), new Vector2(5,570), 305, getRelFrame());
        }



    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        if (this.getRelFrame() > 50)
            batch.draw(programMgrW, 60, 90);
        if (this.getRelFrame() > 220)
            batch.draw(accesoriesW, 82, 70);
        if (this.getRelFrame() > 250)
            batch.draw(this.mediaPlayerW, 5, 570);
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
