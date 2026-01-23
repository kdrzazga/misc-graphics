package org.kd.hellogdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.kd.common.C64Helper;
import org.kd.hellogdx.ScreensGame;

public class Screen2 implements Screen {
    private final ScreensGame game;
    private final BitmapFont font;
    private final SpriteBatch batch;

    public Screen2(ScreensGame game) {
        this.game = game;
        font = C64Helper.createFont(50, "DRENA.ttf");
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.justTouched()) {
            game.setScreen(new Screen1(game));
        }

        batch.begin();
        font.draw(batch, "SCREEN 2", 10, 100);
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
}
