package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.tricks.AlphabetScroll;

public class AlphabetDisplay extends ApplicationAdapter {
    AlphabetScroll scroll;
    private SpriteBatch batch;

    @Override
    public void create() {
        scroll = new AlphabetScroll("a bcdefghijklmnopqrstuvwxyz");
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        super.render();

        if (Gdx.graphics.getFrameId() % 10 == 0)
            scroll.update();

        batch.begin();
        scroll.render(batch);
        batch.end();
    }
}
