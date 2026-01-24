package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.tricks.TravellingLogo;

public class Scene2 implements Screen {

    final static long START_FRAME = 3300;
    private TravellingLogo logo;
    private SpriteBatch batch;

    @Override
    public void show() {
        var texture = new Texture("kickass/ka.png");
        logo = new TravellingLogo(texture, 1700, 200, 721, 50);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        update();

        batch.begin();
        logo.draw(batch, 2 * 1700, 990);
        logo.spriteSpeed = 700f;
        batch.end();
    }

    private void update() {
        var delta = Gdx.graphics.getDeltaTime();
        logo.move(delta, 1700);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
