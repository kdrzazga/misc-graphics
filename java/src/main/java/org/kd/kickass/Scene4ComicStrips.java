package org.kd.kickass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

public class Scene4ComicStrips implements Screen {

    final static long START_FRAME = Scene3Counting.START_FRAME + 300;
    private Sprite comic1, comic2, comic3;
    private SpriteBatch batch;

    @Override
    public void show() {
        comic1 = new Sprite(new Texture("kickass/comic1.png"));
        comic2 = new Sprite(new Texture("kickass/comic2.png"));
        comic3 = new Sprite(new Texture("kickass/comic3.png"));

        List.of(comic1, comic2, comic3).forEach(c -> c.setScale(0.3f));

        batch = new SpriteBatch();
    }

    @Override
    public void render(float v) {
        var fr = Long.valueOf(getCurrentFrame()).shortValue();

        var sprite = comic1;

        if (300 < fr && fr < 600)
            sprite = comic2;
        else if (599 < fr && fr < 900)
            sprite = comic2;

        batch.begin();
        sprite.draw(batch);
        batch.end();
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

    private long getCurrentFrame() {
        return Gdx.graphics.getFrameId() - START_FRAME;
    }
}
