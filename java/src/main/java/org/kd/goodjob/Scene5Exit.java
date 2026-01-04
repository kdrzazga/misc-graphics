package org.kd.goodjob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.Scene;

public final class Scene5Exit extends Scene {

    public static final long START_FRAME = Scene4JobsReturn.START_FRAME + 1629;
    static final long DEMO_END_FRAME = START_FRAME + 1629;
    private SpriteBatch batch5;
    private Texture cake;

    public Scene5Exit() {
        super("exit");
    }

    @Override
    public void create() {
        batch5 = new SpriteBatch();
        this.cake = new Texture("good-job/cake.jpg");
    }

    @Override
    public void update(float delta) {




    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch5.begin();
        batch5.draw(cake, 0, 0);


        batch5.end();
    }

    @Override
    public void dispose() {

    }
}