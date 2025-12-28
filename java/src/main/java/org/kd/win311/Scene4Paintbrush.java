package org.kd.win311;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public class Scene4Paintbrush extends Scene {
    public final static int START_FRAME = Scene3ProgramMgr.START_FRAME + 1000;
    public final static String ID = "paintbrush";

    private Batch batch;
    private Texture mediaPlayerW;
    private Texture paintbrushW;

    public Scene4Paintbrush() {
        super(ID);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        this.mediaPlayerW = new Texture("win311/MediaPlayer.png");
        this.paintbrushW = new Texture("win311/paintbrush.png");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(this.paintbrushW, 10, 10);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
