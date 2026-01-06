package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.tricks.AlphabetScroll;

public class AlphabetDisplay extends ApplicationAdapter {
    AlphabetScroll scroll;
    private SpriteBatch batch;

    @Override
    public void create() {
        //scroll = new AlphabetScroll("abcde fghijklmnopqrstuvwxyz");
        scroll = new AlphabetScroll("abcde fghijklmnopqrstuvwxyz");
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        long fr = Gdx.graphics.getFrameId();
        System.out.println(fr);
        if (fr % 2 == 0)
            scroll.update();

        if (fr % 200 == 0)
            scroll.colorize(new Color(0.9f, 0.3f, 0.2f, 1f));
        if (fr % 200 == 100)
            scroll.colorize(new Color(0.8f, 0.9f, 0.8f, 1f));

        batch.begin();
        scroll.render(batch);
        batch.end();
    }
}
