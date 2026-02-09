package org.kd.tricks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
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

        var f = Gdx.graphics.getFrameId();
        scroll = new AlphabetScroll("after returning to apple  steve jobs spearheaded the launch of the " +
                "imac  revitalizing the company design and technology  he then led the development of " +
                "groundbreaking products like the ipod  iphone   and the ipad   transforming " +
                "multiple industries   jobs continued to innovate until his death  leaving a lasting legacy as a " +
                "visionary tech pioneer", f);
        scroll.scale(0.7f);
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


    public static void main(String[] args) {
        var config = new LwjglApplicationConfiguration();
        config.title = "Alphabet Scroll";
        config.width = 1000;
        config.height = 480;
        config.fullscreen = false;

        var display = new AlphabetDisplay();
        new LwjglApplication(display, config);
    }
}
