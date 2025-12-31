package org.kd.common;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kd.common.tricks.BeepSound;

public class TestBeep {

    @BeforeClass
    public static void init() {
        var config = new LwjglApplicationConfiguration(); //headless mode for testing
        config.forceExit = false; // disable window creation

        new LwjglApplication(new ApplicationListener() {
            @Override
            public void create() {
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        }, config);
    }

    @Test
    public void t1() {

        var b1 = new BeepSound();
        long frame = Gdx.graphics.getFrameId();

        System.out.println(frame);
        for (var i = 0; i < 30; i++) {
            var f = 1000 + (i % 3) * 1000;
            if (i % 3 != 2) b1.playBeep(f);
        }
    }

}
